package org.reldb.spoing.tasks;

import java.util.LinkedList;
import java.util.Queue;

import org.eclipse.swt.widgets.Display;

public class Tasks {
	
	private static Queue<Runnable> tasks = new LinkedList<Runnable>();
	
	public static void addTask(Runnable task) {
		tasks.add(task);
	}
	
	public static void doWaitingTasks(Display display) {
		if (display == null || display.isDisposed())
			return;
		display.syncExec(() -> {
			while (!tasks.isEmpty())
				tasks.remove().run();
		});
	}
}
