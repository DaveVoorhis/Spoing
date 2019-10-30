package org.reldb.spoing.utilities;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.reldb.spoing.platform.MessageBoxLauncher;
import org.reldb.spoing.utilities.Action;

/**
 * A dialog for showing messages to the user.
 * <p>Based on JFace MessageDialog interface, but considerably simplified.
 * </p>
 */
public class MessageDialog {

	/**
	 * Create a MessageBox.
	 * 
	 * @param parent  the parent shell of the dialog or <code>null</code> if none
	 * @param title  the dialog's title, or <code>null</code> if none
	 * @param message  the message
	 * @param style  Style codes, e.g., SWT.ICON_QUESTION, SWT.ICON_WARNING, SWT.ICON_ERROR | SWT.YES, SWT.NO, SWT.OK, SWT.CANCEL, etc.
	 * @return New MessageBox
	 */
	public static MessageBox createMessageBox(Shell parent, String title, String message, int style) {
		var msgBox = new MessageBox(parent, style);
		if (title != null)
			msgBox.setText(title);
		if (message != null)
			msgBox.setMessage(message);
		return msgBox;
	}

	/**
	 * Create and open a MessageBox that invokes a lambda if SWT.YES or SWT.OK is pressed.
	 * 
	 * @param parent  the parent shell of the dialog or <code>null</code> if none
	 * @param title  the dialog's title, or <code>null</code> if none
	 * @param message  the message
	 * @param style  Style codes, e.g., SWT.ICON_QUESTION, SWT.ICON_WARNING, SWT.ICON_ERROR | SWT.YES, SWT.NO, SWT.OK, SWT.CANCEL, etc.
	 * @param act  a lambda to be invoked if SWT.YES or SWT.OK is pressed
	 */
	public static void openMessageBox(Shell parent, String title, String message, int style, Action act) {
		MessageBoxLauncher.open(createMessageBox(parent, title, message, style), act);
	}

	/**
	 * Create and open a MessageBox.
	 * 
	 * @param parent  the parent shell of the dialog or <code>null</code> if none
	 * @param title  the dialog's title, or <code>null</code> if none
	 * @param message  the message
	 * @param style  Style codes, e.g., SWT.ICON_QUESTION, SWT.ICON_WARNING, SWT.ICON_ERROR | SWT.YES, SWT.NO, SWT.OK, SWT.CANCEL, etc.
	 */
	public static void openMessageBox(Shell parent, String title, String message, int style) {
		MessageBoxLauncher.open(createMessageBox(parent, title, message, style), null);
	}
	
	/**
	 * Convenience method to open a simple confirm (OK/Cancel) dialog.
	 *
	 * @param parent  the parent shell of the dialog, or <code>null</code> if none
	 * @param title   the dialog's title, or <code>null</code> if none
	 * @param message the message
	 * @param act  a lambda to be invoked if SWT.OK is pressed
	 */
	public static void openConfirm(Shell parent, String title, String message, Action act) {
		openMessageBox(parent, title, message, SWT.OK | SWT.CANCEL | SWT.ICON_QUESTION, act);
	}

	/**
	 * Convenience method to open a standard error dialog.
	 *
	 * @param parent  the parent shell of the dialog, or <code>null</code> if none
	 * @param title   the dialog's title, or <code>null</code> if none
	 * @param message the message
	 */
	public static void openError(Shell parent, String title, String message) {
		openMessageBox(parent, title, message, SWT.OK | SWT.ICON_ERROR);
	}

	/**
	 * Convenience method to open a standard information dialog.
	 *
	 * @param parent  the parent shell of the dialog, or <code>null</code> if none
	 * @param title   the dialog's title, or <code>null</code> if none
	 * @param message the message
	 */
	public static void openInformation(Shell parent, String title, String message) {
		openMessageBox(parent, title, message, SWT.OK | SWT.ICON_INFORMATION);
	}

	/**
	 * Convenience method to open a simple Yes/No question dialog.
	 *
	 * @param parent  the parent shell of the dialog, or <code>null</code> if none
	 * @param title   the dialog's title, or <code>null</code> if none
	 * @param message the message
	 * @param act  a lambda to be invoked if SWT.YES is pressed
	 */
	public static void openQuestion(Shell parent, String title, String message, Action act) {
		openMessageBox(parent, title, message, SWT.YES | SWT.NO | SWT.ICON_QUESTION, act);
	}

	/**
	 * Convenience method to open a standard warning dialog.
	 *
	 * @param parent  the parent shell of the dialog, or <code>null</code> if none
	 * @param title   the dialog's title, or <code>null</code> if none
	 * @param message the message
	 */
	public static void openWarning(Shell parent, String title, String message) {
		openMessageBox(parent, title, message, SWT.OK | SWT.ICON_WARNING);
	}

}