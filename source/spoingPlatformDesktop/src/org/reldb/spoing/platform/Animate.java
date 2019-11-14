package org.reldb.spoing.platform;

/**
 * Abstraction of mechanism to dynamically update the display even if push isn't supported. 
 * 
 * Necessary on the Web, which does not support push.
 * 
 * @author dave
 *
 */
public class Animate {
	
	public void start() {}
	
	public void stop() {}
	
}
