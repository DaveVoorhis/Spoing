package org.reldb.spoing.tomcat;

import org.apache.catalina.webresources.StandardRoot;

/**
 * A lambda type for use by Launcher.
 * 
 * @author dave
 *
 */

@FunctionalInterface
public interface Configuration {
	void configure(StandardRoot root);
}
