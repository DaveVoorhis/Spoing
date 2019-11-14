package org.reldb.spoing.utilities;

import java.util.Vector;

/**
 * A generic event handler. Typically declared like the following:
 * 
 *    public final EventHandler<MyEvent> myEvent = new EventHandler<>();
 *    
 * To fire a message to listeners:
 * 
 *    myEvent.fire(new MyEvent());
 *    
 * To add a listener:
 * 
 *    myEvent.addListener(event -> { do something here });
 * 
 * @author dave
 *
 * @param <Event>
 */
public class EventHandler<Event> {

	private Vector<EventListener<Event>> listeners = new Vector<>();
	
	/** 
	 * Add a listener.
	 * 
	 * @param listener - EventListener<Event>
	 */
	public void addListener(EventListener<Event> listener) {
		listeners.add(listener);
	}
	
	/**
	 * Remove a listener.
	 * 
	 * @param listener - EventListener<Event>
	 */
	public void removeListener(EventListener<Event> listener) {
		listeners.remove(listener);
	}

	/**
	 * Fire an event to all listeners.
	 * 
	 * @param event - Event
	 */
	public void fire(Event event) {
		listeners.forEach(listener -> listener.notify(event));
	}

}
