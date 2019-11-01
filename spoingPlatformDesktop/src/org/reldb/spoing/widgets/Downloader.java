package org.reldb.spoing.widgets;

import java.io.FileWriter;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Text;
import org.reldb.spoing.platform.PlatformFileDialog;
import org.reldb.spoing.utilities.EventHandler;
import org.eclipse.swt.widgets.Button;

public class Downloader extends Composite {
	
	public final EventHandler<String> DownloadResult = new EventHandler<>();
	
	private byte[] contents = null;
	
	protected PlatformFileDialog saveDialog;	
	private Text textFileName;
	
	public void setFilterPath(String path) {
		saveDialog.setFilterPath(path);
	}
	
	public void setFilterExtensions(String[] filterExtensions) {
		saveDialog.setFilterExtensions(filterExtensions);
	}
	
	public void setFilterNames(String[] filterNames) {
		saveDialog.setFilterNames(filterNames);
	}
	
	public void setFileName(String fileName) {
		saveDialog.setFileName(fileName);
		textFileName.setText(fileName);
	}

	public String getFileName() {
		return textFileName.getText().trim();
	}

	public void setContents(byte[] bytes) {
		contents = bytes;
	}
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public Downloader(Composite parent, int style) {
		super(parent, style);
		setLayout(new FormLayout());
		
		saveDialog = new PlatformFileDialog(parent.getShell(), SWT.SAVE);
		saveDialog.setText("Save");
		saveDialog.setOverwrite(true);

		setFilterExtensions(new String[] {"*.txt", "*.*"});
		setFilterNames(new String[] {"Text", "All Files"});
		setFilterPath(System.getProperty("user.home"));
		
		var lblFileName = new Label(this, SWT.NONE);
		var fd_lblFileName = new FormData();
		fd_lblFileName.top = new FormAttachment(0, 10);
		fd_lblFileName.left = new FormAttachment(0, 10);
		lblFileName.setLayoutData(fd_lblFileName);
		lblFileName.setText("File:");
		
		textFileName = new Text(this, SWT.BORDER);
		var fd_textFileName = new FormData();
		fd_textFileName.top = new FormAttachment(0, 10);
		fd_textFileName.left = new FormAttachment(lblFileName, 6);
		textFileName.setLayoutData(fd_textFileName);
		textFileName.addListener(SWT.CHANGED, evt -> saveDialog.setFileName(textFileName.getText()));
		
		var btnPickFile = new Button(this, SWT.NONE);
		var fd_btnPickFile = new FormData();
		fd_btnPickFile.top = new FormAttachment(lblFileName, 0, SWT.TOP);
		btnPickFile.setLayoutData(fd_btnPickFile);
		btnPickFile.setText("?");
		btnPickFile.addListener(SWT.Selection, evt -> saveDialog.open(fileName -> textFileName.setText(fileName)));
		
		var btnSave = new Button(this, SWT.NONE);		
		var fd_btnSave = new FormData();
		fd_btnSave.top = new FormAttachment(0, 10);
		fd_btnSave.right = new FormAttachment(100, -10);
		btnSave.setLayoutData(fd_btnSave);
		btnSave.setText("Save");
		btnSave.addListener(SWT.Selection, evt -> {
			try (var f = new FileWriter(getFileName())) {
				if (contents != null)
					for (int ch: contents)
						f.write(ch);
			} catch (Throwable t) {
				// TODO send back as DownloadResult
				System.out.println("Downloader: Error writing file: " + t);
			}
			DownloadResult.fire("");
		});

		fd_btnPickFile.right = new FormAttachment(btnSave, -6);		
		
		fd_textFileName.right = new FormAttachment(btnPickFile, 0);
	}
}
