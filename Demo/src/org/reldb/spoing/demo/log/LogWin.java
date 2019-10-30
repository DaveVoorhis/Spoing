package org.reldb.spoing.demo.log;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.ToolItem;
import org.reldb.spoing.platform.IconLoader;
import org.eclipse.swt.graphics.Color;

public class LogWin {

	private static final int threadLoadMax = 10;

	private static LogWin window;
	protected static Shell shell;

	private Text textLog;

	private Color red;
	private Color black;
	private Color blue;

	private static class Message {
		String msg;
		Color color;

		public Message(String msg, Color color) {
			this.msg = msg;
			this.color = color;
		}

		public Message() {
			this.msg = null;
			this.color = null;
		}

		public boolean isNull() {
			return this.msg == null && this.color == null;
		}
	}

	private BlockingQueue<Message> messageQueue;
	private boolean running = true;

	private FileDialog saveTextDialog;

	protected LogWin() {
		messageQueue = new LinkedBlockingQueue<Message>();
		
		shell.setText(shell.getText() + " System Log");
		shell.setLayout(new FormLayout());
		shell.addListener(SWT.Close, evt -> {
			// allow application to shut down if number of visible shells is 1 or less. (1 because this shell is visible)
			if (Arrays.stream(Display.getCurrent().getShells()).filter(sh -> sh.getVisible()).count() <= 1)
				return;
			// otherwise, just make this shell invisible until we need it again
			evt.doit = false;
			shell.setVisible(false);
		});

		red = new Color(shell.getDisplay(), 200, 0, 0);
		black = new Color(shell.getDisplay(), 0, 0, 0);
		blue = new Color(shell.getDisplay(), 0, 0, 128);

		ToolBar toolBar = new ToolBar(shell, SWT.FLAT | SWT.RIGHT);
		FormData fd_toolBar = new FormData();
		fd_toolBar.top = new FormAttachment(0);
		fd_toolBar.left = new FormAttachment(0);
		toolBar.setLayoutData(fd_toolBar);

		ToolItem tltmClear = new ToolItem(toolBar, SWT.NONE);
		tltmClear.setToolTipText("Clear");
		tltmClear.setImage(IconLoader.loadIcon("dustbin"));
		tltmClear.addListener(SWT.Selection, e -> textLog.setText(""));

		ToolItem tltmSave = new ToolItem(toolBar, SWT.NONE);
		tltmSave.setToolTipText("Save");
		tltmSave.setImage(IconLoader.loadIcon("save"));
		tltmSave.addListener(SWT.Selection, e -> {
			if (saveTextDialog == null) {
				saveTextDialog = new FileDialog(shell, SWT.SAVE);
			//	saveTextDialog.setFilterPath(System.getProperty("user.home"));
				saveTextDialog.setFilterExtensions(new String[] { "*.txt", "*.*" });
			//	saveTextDialog.setFilterNames(new String[] { "Text", "All Files" });
				saveTextDialog.setText("Save Output");
			//	saveTextDialog.setOverwrite(true);
			}
			String fname = saveTextDialog.open();
			if (fname == null)
				return;
			try {
				BufferedWriter f = new BufferedWriter(new FileWriter(fname));
				f.write(textLog.getText());
				f.close();
				output("Saved " + fname, blue);
			} catch (IOException ioe) {
				output(ioe.toString(), red);
			}
		});

		textLog = new Text(shell, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI | SWT.H_SCROLL);
		textLog.setEditable(false);
		FormData fd_textLog = new FormData();
		fd_textLog.bottom = new FormAttachment(100);
		fd_textLog.right = new FormAttachment(100);
		fd_textLog.top = new FormAttachment(toolBar);
		fd_textLog.left = new FormAttachment(0);
		textLog.setLayoutData(fd_textLog);

		new Thread(() -> {
			while (running) {
				// wait for data to show up
				Message awaitedEntry;
				try {
					awaitedEntry = messageQueue.take();
				} catch (InterruptedException e1) {
					continue;
				}
				if (shell.isDisposed() || shell.getDisplay().isDisposed()) {
					running = false;
					return;
				}
				shell.getDisplay().syncExec(() -> {
					if (!textLog.isDisposed()) {
						try {
							Message message = awaitedEntry;
							int threadLoadCount = 0;
							do {
								if (message.isNull()) {
									running = false;
									return;
								} else {
									if (running == false)
										return;
									cull();
									/*
									StyleRange styleRange = new StyleRange();
									styleRange.start = textLog.getCharCount();
									styleRange.length = message.msg.length();
									styleRange.fontStyle = SWT.NORMAL;
									styleRange.foreground = message.color;
									*/
									textLog.append(message.msg);
							//		textLog.setStyleRange(styleRange);
								}
								if (++threadLoadCount > threadLoadMax) {
									// exit every so often, because staying in syncExec too long causes UI lag
									return;
								}
							} while ((message = messageQueue.poll(100, TimeUnit.MILLISECONDS)) != null);
			//				textLog.setCaretOffset(textLog.getCharCount());
			//				textLog.setSelection(textLog.getCaretOffset(), textLog.getCaretOffset());
						} catch (InterruptedException e) {
							return;
						}
					}
				});
			}
		}).start();
	}

	/**
	 * Open the window.
	 * 
	 * @param parent
	 */
	public static void open() {
		if (shell.isVisible()) {
			shell.forceActive();
			return;
		} else {
			shell.setVisible(true);
		}
		shell.open();
		shell.layout();
	}

	/**
	 * Close the window.
	 */
	private void close() {
		if (!shell.isDisposed())
			shell.close();
	}

	public void dispose() {
		close();
		red.dispose();
		black.dispose();
		blue.dispose();
	}

	private void cull() {
		if (textLog.getText().length() > 1000000)
			textLog.setText("[...]\n" + textLog.getText().substring(10000));
	}

	private void output(String s, Color color) {
		messageQueue.add(new Message(s, color));
	}

	private static Interceptor outInterceptor;
	private static Interceptor errInterceptor;

	public static void install(Shell shell) {
		LogWin.shell = shell;
		window = new LogWin();

		class LogMessages implements Logger {
			public void log(String s) {
				window.output(s, window.black);
			}
		};

		class LogErrors implements Logger {
			public void log(String s) {
				window.output(s, window.red);
			}
		};

		outInterceptor = new Interceptor(System.out, new LogMessages());
		outInterceptor.attachOut();
		errInterceptor = new Interceptor(System.err, new LogErrors());
		errInterceptor.attachErr();
	}

	public static void remove() {
		if (window == null)
			return;
		window.running = false;
		outInterceptor.detachOut();
		errInterceptor.detachErr();
		window.messageQueue.add(new Message());
		window.messageQueue.clear();
		window.messageQueue.add(new Message());
		window.dispose();
		window = null;
	}

}
