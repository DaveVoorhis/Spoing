package org.reldb.spoing.widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.reldb.spoing.utilities.Action;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;

class DownloaderPanel extends Composite {
	
	private static final long serialVersionUID = 1L;
	
	private Text textFileName;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	DownloaderPanel(Composite parent, Action action) {
		super(parent, SWT.NONE);
		setLayout(new GridLayout(1, true));
		
		var lblPopupNote = new Label(this, SWT.NONE);
		lblPopupNote.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		lblPopupNote.setText("You may need to enable browser popups.");
		
		var composite = new Composite(this, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		composite.setLayout(new GridLayout(2, false));
		
		var lblFileName = new Label(composite, SWT.NONE);
		lblFileName.setText("File name:");
		
		textFileName = new Text(composite, SWT.BORDER);
		textFileName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		var btnDownload = new Button(this, SWT.NONE);
		btnDownload.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		btnDownload.setText("Download");
		btnDownload.addListener(SWT.Selection, evt -> action.go());
	}
	
	void setFileName(String name) {
		textFileName.setText(name);
	}
	
	String getFileName() {
		return textFileName.getText();
	}

}
