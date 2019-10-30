package org.reldb.spoing.utilities;

/**
 * A lambda definition mainly used by dialog boxes, to return responses of a specified type.
 * 
 * @author dave
 *
 */
@FunctionalInterface
public interface Result<T> {
	public void go(T result);
}

