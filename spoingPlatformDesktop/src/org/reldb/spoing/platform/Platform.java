package org.reldb.spoing.platform;

import org.eclipse.swt.program.Program;

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
	 * @param exitCode - exit code to pass to calling context
	 */
	public static void exit(int exitCode) {
		System.exit(exitCode);		
	}

	/**
	 * Launch browser with specified URL.
	 * 
	 * @param url - String representation of URL.
	 */	
	public static void launchBrowserWith(String url) {
		Program.launch(url);
	}
}
