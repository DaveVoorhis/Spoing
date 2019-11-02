package org.reldb.spoing.demo.content;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Text;
import org.reldb.spoing.utilities.MessageDialog;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class Content extends Composite {

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public Content(Composite parent, int style) {
		super(parent, style);
		setLayout(new FormLayout());
		
		Label lblLabel = new Label(this, SWT.NONE);
		FormData fd_lblLabel = new FormData();
		fd_lblLabel.top = new FormAttachment(0, 10);
		fd_lblLabel.left = new FormAttachment(0, 10);
		lblLabel.setLayoutData(fd_lblLabel);
		lblLabel.setText("Text:");
		
		var text = new Text(this, SWT.BORDER);
		FormData fd_text = new FormData();
		fd_text.left = new FormAttachment(lblLabel, 6);
		fd_text.right = new FormAttachment(100, -10);
		fd_text.top = new FormAttachment(0, 10);
		text.setLayoutData(fd_text);
		
		var lblCombo = new Label(this, SWT.NONE);
		FormData fd_lblCombo = new FormData();
		fd_lblCombo.top = new FormAttachment(text, 6);
		fd_lblCombo.left = new FormAttachment(0, 10);
		lblCombo.setLayoutData(fd_lblCombo);
		lblCombo.setText("Combo:");
		
		Combo combo = new Combo(this, SWT.NONE);
		var items = new String[] {"One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten"};
		for (var item: items)
			combo.add(item);
		FormData fd_combo = new FormData();
		fd_combo.left = new FormAttachment(lblCombo, 6);
		fd_combo.top = new FormAttachment(text, 6);
		fd_combo.right = new FormAttachment(100, -10);
		combo.setLayoutData(fd_combo);
		
		Button btnOne = new Button(this, SWT.NONE);
		FormData fd_btnOne = new FormData();
		fd_btnOne.top = new FormAttachment(combo, 6);
		fd_btnOne.left = new FormAttachment(lblLabel, 0, SWT.LEFT);
		btnOne.setLayoutData(fd_btnOne);
		btnOne.setText("One");
		btnOne.addListener(SWT.Selection, evt -> MessageDialog.openInformation(getShell(), "Demo MessageDialog", "This is some information."));
		
		Button btnTwo = new Button(this, SWT.NONE);
		FormData fd_btnTwo = new FormData();
		fd_btnTwo.bottom = new FormAttachment(btnOne, 0, SWT.BOTTOM);
		fd_btnTwo.left = new FormAttachment(btnOne, 6);
		btnTwo.setLayoutData(fd_btnTwo);
		btnTwo.setText("Two");
		btnTwo.addListener(SWT.Selection, evt -> MessageDialog.openError(getShell(), "Demo MessageDialog", "This is a demonstration of an error MessageDialog."));
		
		Button btnThree = new Button(this, SWT.NONE);
		FormData fd_btnThree = new FormData();
		fd_btnThree.bottom = new FormAttachment(btnOne, 0, SWT.BOTTOM);
		fd_btnThree.left = new FormAttachment(btnTwo, 6);
		btnThree.setLayoutData(fd_btnThree);
		btnThree.setText("Three");
		btnThree.addListener(SWT.Selection, evt -> MessageDialog.openQuestion(
				getShell(), "Demo MessageDialog", "This is a demonstration of a question MessageDialog.",
				() -> MessageDialog.openInformation(getShell(), "Demo MessageDialog", "You must have clicked 'Yes'")));
		
		
	}
}
