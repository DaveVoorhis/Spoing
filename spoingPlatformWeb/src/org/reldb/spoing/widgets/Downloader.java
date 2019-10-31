package org.reldb.spoing.widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.reldb.spoing.filedownload.DownloadService;
import org.reldb.spoing.utilities.EventHandler;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.FillLayout;

public class Downloader extends Composite {
	
	private static final long serialVersionUID = 1L;

	public final EventHandler<String> DownloadResult = new EventHandler<>();
	
	private byte[] contents = null;
	
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

	public void setContents(byte[] bytes) {
		contents = bytes;
	}
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public Downloader(Composite parent, int style) {
		super(parent, style);
		FillLayout fillLayout = new FillLayout(SWT.HORIZONTAL);
		fillLayout.marginWidth = 10;
		fillLayout.marginHeight = 10;
		setLayout(fillLayout);
		
		Button btnDownload = new Button(this, SWT.NONE);
		btnDownload.setToolTipText("Click to download.");
		btnDownload.setText("Download");
		btnDownload.addListener(SWT.Selection, evt -> {
			DownloadService.downloadFileData(contents, "untitled.txt");
			DownloadResult.fire(null);
		});
	}
}
