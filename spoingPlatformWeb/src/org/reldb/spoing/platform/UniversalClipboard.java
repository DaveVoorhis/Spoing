package org.reldb.spoing.platform;

import org.eclipse.swt.dnd.HTMLTransfer;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.widgets.Display;

/**
 * A no-op wrapper around clipboard functionality to prevent breakage when using desktop-oriented code on the Web.
 * 
 * Normally, clipboard functionality is handled entirely by the browser, not the application, so you'll probably
 * want to explicitly run-time disable clipboard functionality in applications running on the Web.
 * 
 * @author dave
 *
 */
public class UniversalClipboard {

	public UniversalClipboard(Display current) {
	}

	public Object getContents(TextTransfer textTransfer) {
		return null;
	}

	public Object getContents(HTMLTransfer htmlTransfer) {
		return null;
	}

	public void dispose() {
	}

	public static boolean isClipboardAvailable() {
		return false;
	}
	
}
