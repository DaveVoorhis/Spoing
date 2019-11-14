package org.reldb.spoing.platform;

import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

/**
 * The RAP/RWT MenuItem doesn't provide setToolTipText(String), but the SWT MenuItem does.
 * 
 * Use this instead of MenuItem, so that you can use setToolTipText(String) in your desktop applications
 * and deploy them to Web without breakage.
 * 
 * @author dave
 *
 */
public class PlatformMenuItem extends MenuItem {
	private static final long serialVersionUID = 1L;

	public PlatformMenuItem(Menu parent, int style) {
		super(parent, style);
	}

	/** No-op under RWT/RAP */
	public void setToolTipText(String text) {}
	
	public void checkSubclass() {}
}
