package org.reldb.spoing.messageloop;

import org.eclipse.swt.widgets.Display;
import org.reldb.spoing.tasks.Tasks;
import org.reldb.spoing.utilities.EventListener;

public class MessageLoop {

	private static boolean backgroundRunnerRunning = false;

	/**
	 * Start up the message pump and hand control over to the application user.
	 * 
	 * This should be the <b>last</b> thing called after setting up the UI.
	 * 
	 * In the Web implementation, this only handles background tasks.
	 * 
	 * @param display - current Display.
	 * @param exceptionHandler - general exception handler. For the Web it's handled elsewhere, so can be null.
	 */
	public static void start(Display display, EventListener<Throwable> exceptionHandler) {
		// Background task processor.
		var background = new Thread(() -> {
			 backgroundRunnerRunning = true;
			 while (backgroundRunnerRunning) {
				 Tasks.doWaitingTasks(display);
				 try {
					 Thread.sleep(500);
				 } catch (InterruptedException e1) {}
			 }
		});
		background.setDaemon(true);
		background.start();		
	}
	
	/**
	 * Shut down background task processor.
	 */
	public static void stop() {
		backgroundRunnerRunning = false;
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {}		
	}
}
