package org.reldb.spoing.widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.reldb.spoing.platform.DialogBase;
import org.reldb.spoing.utilities.Action;
import org.eclipse.swt.layout.FillLayout;

class DownloaderDialog extends DialogBase {

	private static final long serialVersionUID = 1L;
	
	private DownloaderPanel downloaderPanel;
	
	void setFileName(String fileName) {
		downloaderPanel.setFileName(fileName);
	}

	String getFileName() {
		return downloaderPanel.getFileName();
	}
	
	DownloaderDialog(Shell parent, int shellStyle, Action download) {
		super(parent, shellStyle);
		shell = new Shell(getParent(), getStyle());
		shell.setText(getText());
		
		var fillLayout = new FillLayout();
		fillLayout.marginHeight = 10;
		fillLayout.marginWidth = 10;
		shell.setLayout(fillLayout);
		
		downloaderPanel = new DownloaderPanel(shell, () -> download.go());
		
		shell.pack();
	}
	
	DownloaderDialog(Shell parent, Action download) {
		this(parent, SWT.DIALOG_TRIM | SWT.RESIZE | SWT.APPLICATION_MODAL, download);
	}

	void open() {
		launch();
	}
	
}
