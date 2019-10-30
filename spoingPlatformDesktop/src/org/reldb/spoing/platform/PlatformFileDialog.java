package org.reldb.spoing.platform;

import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.reldb.spoing.utilities.Result;

public class PlatformFileDialog extends FileDialog {

	public PlatformFileDialog(Shell parent) {
		super(parent);
	}
	
	public PlatformFileDialog(Shell parent, int style) {
		super(parent, style);
	}
	
	public void open(Result<String> action) {
		String result = open();
		if (result != null)
			action.go(result);
	}
	
}
