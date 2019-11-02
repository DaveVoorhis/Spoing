package org.reldb.spoing.demo.content;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.ToolBar;
import org.reldb.spoing.commands.CommandActivator;
import org.reldb.spoing.demo.launcher.Command;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;

public class Content extends Composite {

	int counter = 0;
	
	private void addTab(CTabFolder tabFolder) {
		var tabItem = new CTabItem(tabFolder, SWT.NONE);
		tabItem.setShowClose(true);		
		tabItem.setText("DemoTab" + (counter++));
		tabItem.setControl(new Widgets(tabFolder));
		tabFolder.setSelection(tabItem);
		tabItem.getControl().setFocus();
	}
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public Content(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(1, false));
		
		var toolBar = new ToolBar(this, SWT.NONE);
		toolBar.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		var tabFolder = new CTabFolder(this, SWT.NONE);
		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		new CommandActivator(Command.NewTab.ordinal(), toolBar, "add-new-document", SWT.PUSH, "Add new tab", evt -> addTab(tabFolder));
		
		addTab(tabFolder);
	}
}
