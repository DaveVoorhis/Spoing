package org.reldb.spoing.widgets;

import java.io.FileWriter;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.reldb.spoing.platform.PlatformFileDialog;
import org.reldb.spoing.utilities.EventHandler;

public class Downloader {
	
	public final EventHandler<String> DownloadResult = new EventHandler<>();
	
	private byte[] contents = null;
	
	protected PlatformFileDialog saveDialog;	
	
	public void setFilterPath(String path) {
		saveDialog.setFilterPath(path);
	}
	
	public void setFilterExtensions(String[] filterExtensions) {
		saveDialog.setFilterExtensions(filterExtensions);
	}
	
	public void setFilterNames(String[] filterNames) {
		saveDialog.setFilterNames(filterNames);
	}

	public void setOverwrite(boolean b) {
		saveDialog.setOverwrite(b);
	}
	
	public void setFileName(String fileName) {
		saveDialog.setFileName(fileName);
	}
	
	public String getFileName() {
		return saveDialog.getFileName();
	}

	public void setText(String text) {
		saveDialog.setText(text);
	}
	
	public void setContents(byte[] bytes) {
		contents = bytes;
	}

	public Downloader(Shell parent) {
		saveDialog = new PlatformFileDialog(parent, SWT.SAVE);
	}
	
	public void open() {
		saveDialog.open(filespec -> {
			try (var f = new FileWriter(filespec)) {
				if (contents != null)
					for (int ch: contents)
						f.write(ch);
				DownloadResult.fire("");			
			} catch (Throwable t) {
				DownloadResult.fire(t.getMessage());
			}
		});
	}
}
