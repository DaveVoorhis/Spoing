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
		
		Label lblLogo = new Label(this, SWT.NONE);
		FormData fd_lblLogo = new FormData();
		fd_lblLogo.top = new FormAttachment(0, 0);
		fd_lblLogo.left = new FormAttachment(0, 0);
		lblLogo.setLayoutData(fd_lblLogo);
		lblLogo.setImage(IconLoader.loadIcon("Candle32"));
		
		Label lblTitle = new Label(this, SWT.NONE);
		FormData fd_lblTitle = new FormData();
		fd_lblTitle.top = new FormAttachment(0, 0);
		fd_lblTitle.left = new FormAttachment(lblLogo, 5);
		lblTitle.setLayoutData(fd_lblTitle);
		lblTitle.setFont(SWTResourceManager.getFont(".AppleSystemUIFont", 16, SWT.NORMAL));
		lblTitle.setText(Version.getAppName() + " " + Version.getAppSubtitle());
		
		Label lblVersion = new Label(this, SWT.NONE);
		FormData fd_lblVersion = new FormData();
		fd_lblVersion.top = new FormAttachment(lblTitle, 8);
		fd_lblVersion.left = new FormAttachment(lblTitle, 0, SWT.LEFT);
		lblVersion.setLayoutData(fd_lblVersion);
		lblVersion.setText(Version.getVersion());
		
		Label lblCopyright = new Label(this, SWT.WRAP);
		FormData fd_lblCopyright = new FormData();
		fd_lblCopyright.top = new FormAttachment(lblVersion, 8);
		fd_lblCopyright.left = new FormAttachment(lblTitle, 0, SWT.LEFT);
		lblCopyright.setLayoutData(fd_lblCopyright);
		lblCopyright.setText(Version.getCopyrightNotice());
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
