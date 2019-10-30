package org.reldb.spoing.demo.about;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.reldb.spoing.demo.version.Version;
import org.reldb.spoing.platform.IconLoader;

public class AboutDialogPanel extends Composite {

	public Button btnClose;
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public AboutDialogPanel(Composite parent, int style) {
		super(parent, style);
		
		setLayout(new FormLayout());
		
		Label lblNewLabel = new Label(this, SWT.NONE);
		FormData fd_lblNewLabel = new FormData();
		fd_lblNewLabel.top = new FormAttachment(0, 0);
		fd_lblNewLabel.left = new FormAttachment(0, 0);
		lblNewLabel.setLayoutData(fd_lblNewLabel);
		lblNewLabel.setImage(IconLoader.loadIcon("Candle32"));
		
		Label lblNewTitle = new Label(this, SWT.NONE);
		FormData fd_lblNewTitle = new FormData();
		fd_lblNewTitle.top = new FormAttachment(0, 0);
		fd_lblNewTitle.left = new FormAttachment(lblNewLabel, 5);
		lblNewTitle.setLayoutData(fd_lblNewTitle);
		lblNewTitle.setFont(SWTResourceManager.getFont(".AppleSystemUIFont", 16, SWT.NORMAL));
		lblNewTitle.setText(Version.getAppName() + " " + Version.getAppSubtitle());
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
