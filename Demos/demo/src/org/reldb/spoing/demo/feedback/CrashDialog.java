package org.reldb.spoing.demo.feedback;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class CrashDialog extends TypicalFeedbackDialog {
	private Throwable exception;

	/** Launch the dialog. */
	public static void launch(Throwable exception) {
		try {
			exception.printStackTrace();
			var display = Display.getCurrent();
			var shell = display.getActiveShell();
			display.syncExec(() -> (new CrashDialog(shell, exception)).open());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public CrashDialog(Shell parent, Throwable exception) {
		super(parent, 
			"Crash Report", 
			"CrashReport",
			"explosion",
			"Unfortunately, something went wrong. We'd like to send the developers a message about it, so they can fix it in a future update.\n\n" + 
					"If you'd rather not send anything, that's ok. Press the Cancel button and nothing will be sent.\n\n" + 
					"Otherwise, please answer the following questions as best you can and remove any information that you don't want to send. " + 
					"Then press the Send button to transmit it to the developers.",
			"1. What were you doing when this happened?"
		);
		this.exception = exception;
	}
	
	@Override
	protected void populateTree() {
		super.populateTree();
		putExceptionInTree(exception);		
	}
	
}
