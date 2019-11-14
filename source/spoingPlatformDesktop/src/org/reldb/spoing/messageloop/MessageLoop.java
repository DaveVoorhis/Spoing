package org.reldb.spoing.messageloop;

import org.eclipse.swt.widgets.Display;
import org.reldb.spoing.tasks.Tasks;
import org.reldb.spoing.utilities.EventListener;

public class MessageLoop {
	
	/**
	 * Start up the message pump and hand control over to the application user.
	 * 
	 * This should be the <b>last</b> thing called after setting up the UI.
	 * 
	 * @param display - current Display.
	 * @param exceptionHandler - exception handler for catching exceptions in the message loop.
	 */
	public static void start(Display display, EventListener<Throwable> exceptionHandler) {	
		while (display != null && !display.isDisposed()) {
			try {
				if (display != null && !display.readAndDispatch()) {
					Tasks.doWaitingTasks(display);
					display.sleep();
				}
			} catch (Throwable exception) {
				if (exceptionHandler != null)
					exceptionHandler.notify(exception);
			}
		}
	}
	
	/**
	 * On the desktop, this is a no-op.
	 */
	public static void stop() {
	}
}
