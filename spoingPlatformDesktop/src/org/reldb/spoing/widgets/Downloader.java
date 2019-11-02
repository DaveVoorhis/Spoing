package org.reldb.spoing.widgets;

import java.io.FileWriter;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
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

	public Downloader(Composite parent) {
		saveDialog = new PlatformFileDialog(parent.getShell(), SWT.SAVE);
		saveDialog.setText("Save");
		saveDialog.setOverwrite(true);

		setFilterExtensions(new String[] {"*.txt", "*.*"});
		setFilterNames(new String[] {"Text", "All Files"});
		setFilterPath(System.getProperty("user.home"));
	}
	
	public void open() {
		saveDialog.open(result -> {
			System.out.println("Downloader: write file to " + getFileName());
			try (var f = new FileWriter(getFileName())) {
				if (contents != null)
					for (int ch: contents)
						f.write(ch);
			} catch (Throwable t) {
				// TODO send back as DownloadResult
				System.out.println("Downloader: Error writing file: " + t);
				t.printStackTrace();
			}
			DownloadResult.fire("");			
		});
	}
}
