package org.reldb.spoing.tomcat;

import javax.servlet.ServletContext;

import org.apache.tomcat.JarScanFilter;
import org.apache.tomcat.JarScanType;
import org.apache.tomcat.JarScanner;
import org.apache.tomcat.JarScannerCallback;

/**
 * Part of the Tomcat configuration, to prevent unnecessarily scanning .jar files.
 * 
 * @author dave
 *
 */
class NoJarScan implements JarScanner {

	@Override
	public JarScanFilter getJarScanFilter() {
		return null;
	}

	@Override
	public void scan(JarScanType arg0, ServletContext arg1, JarScannerCallback arg2) {
	}

	@Override
	public void setJarScanFilter(JarScanFilter arg0) {
	}
	
}