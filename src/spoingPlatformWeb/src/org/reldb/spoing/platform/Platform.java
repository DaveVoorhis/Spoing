package org.reldb.spoing.platform;

import org.eclipse.rap.rwt.RWT;
import org.eclipse.rap.rwt.client.service.UrlLauncher;

/**
 * An abstraction of some platform-specific facilities.
 * 
 * exit(int) exits the application, passing an exit code to its calling context.
 * 
 * launchBrowserWith(String) opens a browser window to display a given URL.
 * 
 * @author dave
 *
 */
public class Platform {
	/**
	 * Exit the application.
	 * 
	 * @param exitCode - for compatibility with desktop application. Does nothing in Web apps.
	 */
	public static void exit(int exitCode) {
	}
	
	/**
	 * Launch browser with specified URL.
	 * 
	 * @param url - String representation of URL.
	 */
	public static void launchBrowserWith(String url) {
		UrlLauncher launcher = RWT.getClient().getService(UrlLauncher.class);
		launcher.openURL(url);		
	}
}
