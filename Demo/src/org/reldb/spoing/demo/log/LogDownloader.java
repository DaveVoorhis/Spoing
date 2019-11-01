package org.reldb.spoing.demo.log;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Shell;
import org.reldb.spoing.utilities.DialogAbstract;
import org.reldb.spoing.widgets.Downloader;

public class LogDownloader extends DialogAbstract {

	private Downloader downloader;
	private byte[] bytes;
	
	public LogDownloader(Shell parent) {
		super(parent);
	}

	@Override
	protected void createContents() {
		shell.setLayout(new FillLayout());
		downloader = new Downloader(shell, SWT.NONE);
		downloader.setContents(bytes);
		downloader.DownloadResult.addListener(result -> {
			close();
		});
		shell.pack();
		var packedSize = shell.getSize();
		shell.setSize(300, packedSize.y);
	}

	public void setContents(byte[] bytes) {
		this.bytes = bytes;
	}

}
