package org.reldb.spoing.widgets;

import org.eclipse.swt.widgets.Shell;
import org.reldb.spoing.filedownload.DownloadService;
import org.reldb.spoing.utilities.EventHandler;

public class Downloader {
	
	public final EventHandler<String> DownloadResult = new EventHandler<>();
	
	private byte[] contents = null;
	
	protected DownloaderDialog downloaderDialog;
	
	public void setFilterPath(String path) {
	}
	
	public void setFilterExtensions(String[] filterExtensions) {
	}
	
	public void setFilterNames(String[] filterNames) {
	}

	public void setOverwrite(boolean b) {
	}
	
	public void setFileName(String fileName) {
		downloaderDialog.setFileName(fileName);
	}

	public String getFileName() {
		return downloaderDialog.getFileName();
	}

	public void setText(String text) {
		downloaderDialog.setText(text);
	}

	public void setContents(byte[] bytes) {
		contents = bytes;
	}
	
	public Downloader(Shell parent) {
		downloaderDialog = new DownloaderDialog(parent, () -> {
			DownloadService.downloadFileData(contents, getFileName());
			DownloadResult.fire("");
		});
		downloaderDialog.setFileName("Untitled.txt");
	}
	
	public void open() {
		downloaderDialog.open();
	}
}
