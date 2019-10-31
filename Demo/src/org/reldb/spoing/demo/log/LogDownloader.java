package org.reldb.spoing.demo.log;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Shell;
import org.reldb.spoing.utilities.DialogAbstract;
import org.reldb.spoing.widgets.Downloader;

public class LogDownloader extends DialogAbstract {

	public LogDownloader(Shell parent) {
		super(parent);
	}

	@Override
	protected void createContents() {
		shell.setLayout(new FillLayout());
		var downloader = new Downloader(shell, SWT.NONE);
		downloader.savePressed.addListener(fileName -> {
			System.out.println("LogDownloader: save file pressed on " + fileName);
		});
		shell.pack();
		var packedSize = shell.getSize();
		shell.setSize(300, packedSize.y);
	}

}
