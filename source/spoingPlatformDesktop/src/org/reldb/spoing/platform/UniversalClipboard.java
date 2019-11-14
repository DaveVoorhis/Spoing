package org.reldb.spoing.platform;

import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.HTMLTransfer;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.widgets.Display;

/**
 * A wrapper around desktop clipboard functionality, to provide compatibility with
 * the Web equivalent.
 * 
 * @author dave
 *
 */
public class UniversalClipboard {

	private Clipboard clipboard;
	
	public UniversalClipboard(Display display) {
		clipboard = new Clipboard(display);
	}

	public Object getContents(TextTransfer textTransfer) {
		return clipboard.getContents(textTransfer);
	}

	public Object getContents(HTMLTransfer htmlTransfer) {
		return clipboard.getContents(htmlTransfer);
	}

	public void dispose() {
		clipboard.dispose();
	}

	public static boolean isClipboardAvailable() {
		return true;
	}

}
