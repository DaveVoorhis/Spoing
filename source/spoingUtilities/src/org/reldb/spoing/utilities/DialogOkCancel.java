package org.reldb.spoing.utilities;

import org.eclipse.swt.widgets.Shell;
import org.reldb.spoing.utilities.EventHandler;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;

public abstract class DialogOkCancel extends DialogAbstract {
	
	public final EventHandler<Void> cancelListener = new EventHandler<>();
	public final EventHandler<Void> okListener = new EventHandler<>();
	
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public DialogOkCancel(Shell parent) {
		super(parent);
	}
	
	/** Create contents of the content panel.
	 * 
	 * @param content - Composite - parent panel.
	 */
	protected abstract void createContent(Composite content);
	
	/**
	 * Create contents of the dialog.
	 */
	protected void createContents() {
		shell.setLayout(new FormLayout());
		
		var btnCancel = new Button(shell, SWT.NONE);
		var fd_btnCancel = new FormData();
		fd_btnCancel.bottom = new FormAttachment(100, -10);
		fd_btnCancel.right = new FormAttachment(100, -10);
		btnCancel.setLayoutData(fd_btnCancel);
		btnCancel.setText("Cancel");
		btnCancel.addListener(SWT.Selection, evt -> {
			cancelListener.fire(null);
			close();
		});
		
		var btnOk = new Button(shell, SWT.NONE);
		var fd_btnOk = new FormData();
		fd_btnOk.bottom = new FormAttachment(btnCancel, 0, SWT.BOTTOM);
		fd_btnOk.right = new FormAttachment(btnCancel, -6);
		btnOk.setLayoutData(fd_btnOk);
		btnOk.setText("Ok");
		btnOk.addListener(SWT.Selection, evt -> okListener.fire(null));
		
		var contentPanel = new Composite(shell, SWT.NONE);
		var fd_contentPanel = new FormData();
		fd_contentPanel.top = new FormAttachment(0, 10);
		fd_contentPanel.left = new FormAttachment(0, 10);
		fd_contentPanel.right = new FormAttachment(100, -10);
		fd_contentPanel.bottom = new FormAttachment(btnCancel, -10, SWT.TOP);
		contentPanel.setLayoutData(fd_contentPanel);
		contentPanel.setLayout(new FillLayout());
		createContent(contentPanel);
		
		shell.setDefaultButton(btnOk);
	}
	
}
