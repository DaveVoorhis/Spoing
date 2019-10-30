package org.reldb.spoing.demo;

import org.reldb.spoing.demo.launcher.Launcher;
import org.reldb.spoing.utilities.MessageDialog;

/**
 * This is the class to run.
 * 
 * It launches a desktop version of the application.
 * 
 * @author dave
 *
 */
public class Main {
	public static void main(String args[]) {
		try {
			Launcher.launch(args);
		} catch (Throwable t) {
			t.printStackTrace();
			MessageDialog.openError(null, "Launch Failure", "Check the system log for details about:\n\n" + t.toString());
		} finally {
			System.exit(0);
		}
	}
}
