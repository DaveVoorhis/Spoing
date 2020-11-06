package org.reldb.spoing.utilities;

/**
 * A generic listener lambda for use with EventHandler.
 * 
 * @author dave
 *
 * @param <Event>
 */
@FunctionalInterface
public interface EventListener<Event> {
	public void notify(Event event);
}
