package org.reldb.spoing.platform;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * Base class for all dialog boxes. Abstracts opening a dialog box -- which is different in SWT vs RAP/RWT -- into a launch()
 * method, so that dialog box code can be unified across Web and desktop platforms.
 * 
 * @author dave
 *
 */
public class DialogBase extends Dialog {
	
	protected Shell shell;
	
	public DialogBase(Shell parent) {
		super(parent);
	}
	
	public DialogBase(Shell parent, int style) {
		super(parent, style);
	}

	/** Open the dialog box. */
	protected void launch() {
		shell.open();
		Display display = shell.getDisplay();
		while (!shell.isDisposed())
			if (!display.readAndDispatch())
				display.sleep();
	}
	
}
