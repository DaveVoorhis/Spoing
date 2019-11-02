package org.reldb.spoing.widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.reldb.spoing.filedownload.DownloadService;
import org.reldb.spoing.platform.DialogBase;
import org.reldb.spoing.utilities.EventHandler;
import org.eclipse.swt.layout.FillLayout;

public class Downloader extends DialogBase {

	private static final long serialVersionUID = 1L;

	public final EventHandler<String> DownloadResult = new EventHandler<>();
	
	private byte[] contents = null;
	
	private DownloaderPanel downloaderPanel;
	
	public void setFilterPath(String path) {
	}
	
	public void setFilterExtensions(String[] filterExtensions) {
	}
	
	public void setFilterNames(String[] filterNames) {
	}
	
	public void setFileName(String fileName) {
		downloaderPanel.setFileName(fileName);
	}

	public String getFileName() {
		return downloaderPanel.getFileName();
	}

	public void setContents(byte[] bytes) {
		contents = bytes;
	}
	
	public Downloader(Shell parent, int shellStyle) {
		super(parent, shellStyle);
		shell = new Shell(getParent(), getStyle());
		shell.setText(getText());
		
		var fillLayout = new FillLayout();
		fillLayout.marginHeight = 10;
		fillLayout.marginWidth = 10;
		shell.setLayout(fillLayout);
		
		downloaderPanel = new DownloaderPanel(shell, () -> DownloadService.downloadFileData(contents, getFileName()));
	}
	
	public Downloader(Shell parent) {
		this(parent, SWT.DIALOG_TRIM | SWT.RESIZE | SWT.APPLICATION_MODAL);
	}
	
	public void close() {
		shell.close();
		shell.dispose();
	}
	
	public void open() {
		launch();
	}
}
