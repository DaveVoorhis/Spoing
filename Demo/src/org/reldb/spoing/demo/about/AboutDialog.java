package org.reldb.spoing.demo.about;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.reldb.spoing.utilities.DialogOk;
import org.eclipse.swt.SWT;

public class AboutDialog extends DialogOk {
	
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public AboutDialog(Shell parent) {
		super(parent);
		setText("About");
	}

	@Override
	protected void createContent(Composite content) {
		new AboutDialogPanel(content, SWT.NONE);
		shell.setSize(450, 300);
	}
	
}
