package org.reldb.spoing.demo.log;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Shell;
import org.reldb.spoing.utilities.DialogAbstract;
import org.reldb.spoing.widgets.Downloader;

public class LogDownloader extends DialogAbstract {

	private Downloader downloader;
	
	public LogDownloader(Shell parent) {
		super(parent);
		downloader = new Downloader(shell, SWT.NONE);
	}

	@Override
	protected void createContents() {
		shell.setLayout(new FillLayout());
		downloader.DownloadResult.addListener(result -> {
			close();
		});
		shell.pack();
		var packedSize = shell.getSize();
		shell.setSize(300, packedSize.y);
	}

	public void setContents(byte[] bytes) {
		downloader.setContents(bytes);
	}

}
