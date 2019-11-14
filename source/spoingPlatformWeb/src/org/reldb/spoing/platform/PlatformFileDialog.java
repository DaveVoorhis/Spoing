package org.reldb.spoing.platform;

import org.eclipse.rap.rwt.widgets.DialogCallback;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.reldb.spoing.utilities.Result;

public class PlatformFileDialog extends FileDialog {
	
	private static final long serialVersionUID = 1L;

	public PlatformFileDialog(Shell parent) {
		super(parent);
	}
	
	public PlatformFileDialog(Shell parent, int style) {
		super(parent, style);
		if ((style & SWT.SAVE) != 0)
			System.out.println("WARNING: RAP/RWT FileDialog/PlatformFileDialog does not support SWT.SAVE mode.");
	}
	
	/** Not supported under RAP/RWT */
	public void setFilterPath(String filterPath) {}
	
	/** Not supported under RAP/RWT */
	public void setFilterNames(String[] filteNames) {}
	
	/** Not supported under RAP/RWT */
	public void setOverwrite(boolean b) {}
	
	public void open(Result<String> action) {
		open((DialogCallback)result -> {
			if (getFileName() != null && action != null && result == SWT.OK)
				action.go(getFileName());
		});
	}
	
}
