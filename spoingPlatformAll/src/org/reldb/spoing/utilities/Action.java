package org.reldb.spoing.utilities;

/**
 * A lambda definition mainly used by dialog boxes, to define responses to pressing buttons.
 * 
 * @author dave
 *
 */
@FunctionalInterface
public interface Action {
	public void go();
}

