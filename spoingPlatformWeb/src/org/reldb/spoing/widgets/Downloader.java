package org.reldb.spoing.widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.FormLayout;
import org.reldb.spoing.utilities.EventHandler;

public class Downloader extends Composite {
	
	public final EventHandler<String> savePressed = new EventHandler<>();
	
	public void setFilterPath(String path) {
	}
	
	public void setFilterExtensions(String[] filterExtensions) {
	}
	
	public void setFilterNames(String[] filterNames) {
	}
	
	public void setFileName(String fileName) {
	}

	public String getFileName() {
		return "";
	}
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public Downloader(Composite parent, int style) {
		super(parent, style);
		setLayout(new FormLayout());
		
	}
}
