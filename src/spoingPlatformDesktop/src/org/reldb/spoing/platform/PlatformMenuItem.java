package org.reldb.spoing.platform;

import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

/**
 * Inherited from the standard SWT MenuItem, for compatibility
 * with the Web version of the same.
 * 
 * @author dave
 *
 */
public class PlatformMenuItem extends MenuItem {

	public PlatformMenuItem(Menu parent, int style) {
		super(parent, style);
	}
	
	public void checkSubclass() {}
}
