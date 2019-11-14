package org.reldb.spoing.platform;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.reldb.spoing.utilities.Action;

/**
 * Launch a specified MessageBox dialog. The Action lambda may be used to specify an action to take if the dialog
 * result is SWT.OK or SWT.YES.  If action is null, it will be ignored.
 * 
 * @author dave
 *
 */
public abstract class MessageBoxLauncher {
	public static void open(MessageBox dialog, Action action) {
		int result = dialog.open();
		if (action != null && (result == SWT.OK || result == SWT.YES))
			action.go();
	}
}
