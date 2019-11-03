package org.reldb.spoing.demo.content;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.reldb.spoing.utilities.MessageDialog;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Slider;
import org.eclipse.swt.widgets.Scale;
import org.eclipse.swt.widgets.List;

public class Widgets extends Composite {

	public Widgets(Composite parent) {
		super(parent, SWT.NONE);
		setLayout(new FormLayout());
		
		var lblLabel = new Label(this, SWT.NONE);
		var fd_lblLabel = new FormData();
		fd_lblLabel.top = new FormAttachment(0, 10);
		fd_lblLabel.left = new FormAttachment(0, 10);
		lblLabel.setLayoutData(fd_lblLabel);
		lblLabel.setText("Text:");
		
		var text = new Text(this, SWT.BORDER);
		var fd_text = new FormData();
		fd_text.left = new FormAttachment(lblLabel, 6);
		fd_text.right = new FormAttachment(100, -10);
		fd_text.top = new FormAttachment(0, 10);
		text.setLayoutData(fd_text);
		
		var lblCombo = new Label(this, SWT.NONE);
		var fd_lblCombo = new FormData();
		fd_lblCombo.top = new FormAttachment(text, 6);
		fd_lblCombo.left = new FormAttachment(0, 10);
		lblCombo.setLayoutData(fd_lblCombo);
		lblCombo.setText("Combo:");
		
		var combo = new Combo(this, SWT.NONE);
		var items = new String[] {"One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten"};
		for (var item: items)
			combo.add(item);
		var fd_combo = new FormData();
		fd_combo.left = new FormAttachment(lblCombo, 6);
		fd_combo.top = new FormAttachment(text, 6);
		fd_combo.right = new FormAttachment(100, -10);
		combo.setLayoutData(fd_combo);
		
		var btnOne = new Button(this, SWT.NONE);
		var fd_btnOne = new FormData();
		fd_btnOne.top = new FormAttachment(combo, 6);
		fd_btnOne.left = new FormAttachment(lblLabel, 0, SWT.LEFT);
		btnOne.setLayoutData(fd_btnOne);
		btnOne.setText("One");
		btnOne.addListener(SWT.Selection, evt -> MessageDialog.openInformation(getShell(), "Demo MessageDialog", "This is some information."));
		
		var btnTwo = new Button(this, SWT.NONE);
		var fd_btnTwo = new FormData();
		fd_btnTwo.bottom = new FormAttachment(btnOne, 0, SWT.BOTTOM);
		fd_btnTwo.left = new FormAttachment(btnOne, 6);
		btnTwo.setLayoutData(fd_btnTwo);
		btnTwo.setText("Two");
		btnTwo.addListener(SWT.Selection, evt -> MessageDialog.openError(getShell(), "Demo MessageDialog", "This is a demonstration of an error MessageDialog."));
		
		var btnThree = new Button(this, SWT.NONE);
		var fd_btnThree = new FormData();
		fd_btnThree.bottom = new FormAttachment(btnOne, 0, SWT.BOTTOM);
		fd_btnThree.left = new FormAttachment(btnTwo, 6);
		btnThree.setLayoutData(fd_btnThree);
		btnThree.setText("Three");
		btnThree.addListener(SWT.Selection, evt -> MessageDialog.openQuestion(
				getShell(), "Demo MessageDialog", "Is this a demonstration of a question MessageDialog?",
					() -> MessageDialog.openInformation(getShell(), "Demo MessageDialog", "You must have clicked 'Yes'")));
		
		var grpCheckboxes = new Group(this, SWT.NONE);
		grpCheckboxes.setText("Checkboxes");
		grpCheckboxes.setLayout(new RowLayout(SWT.VERTICAL));
		var fd_grpCheckboxes = new FormData();
		fd_grpCheckboxes.top = new FormAttachment(btnOne, 6);
		fd_grpCheckboxes.left = new FormAttachment(0, 10);
		fd_grpCheckboxes.right = new FormAttachment(50, -5);
		grpCheckboxes.setLayoutData(fd_grpCheckboxes);
		
		var btnCheckButton_1 = new Button(grpCheckboxes, SWT.CHECK);
		btnCheckButton_1.setText("Check Button 1");
		
		var btnCheckButton_2 = new Button(grpCheckboxes, SWT.CHECK);
		btnCheckButton_2.setText("Check Button 2");
		
		var btnCheckButton_3 = new Button(grpCheckboxes, SWT.CHECK);
		btnCheckButton_3.setText("Check Button 3");
		
		var grpRadiobuttons = new Group(this, SWT.NONE);
		grpRadiobuttons.setText("Radiobuttons");
		grpRadiobuttons.setLayout(new RowLayout(SWT.VERTICAL));
		var fd_grpRadiobuttons = new FormData();
		fd_grpRadiobuttons.top = new FormAttachment(btnThree, 6);
		fd_grpRadiobuttons.left = new FormAttachment(50, 5);
		fd_grpRadiobuttons.right = new FormAttachment(100, -10);
		grpRadiobuttons.setLayoutData(fd_grpRadiobuttons);
		
		var btnRadioButton_1 = new Button(grpRadiobuttons, SWT.RADIO);
		btnRadioButton_1.setText("Radio Button 1");
		
		var btnRadioButton_2 = new Button(grpRadiobuttons, SWT.RADIO);
		btnRadioButton_2.setText("Radio Button 2");
		
		var btnRadioButton_3 = new Button(grpRadiobuttons, SWT.RADIO);
		btnRadioButton_3.setText("Radio Button 3");
		
		var lblSpinner = new Label(this, SWT.NONE);
		var fd_lblSpinner = new FormData();
		fd_lblSpinner.top = new FormAttachment(grpCheckboxes, 10);
		fd_lblSpinner.left = new FormAttachment(lblLabel, 0, SWT.LEFT);
		lblSpinner.setLayoutData(fd_lblSpinner);
		lblSpinner.setText("Spinner:");
		
		var spinner = new Spinner(this, SWT.BORDER);
		spinner.setValues(0, -100, 100, 0, 1, 10);
		var fd_spinner = new FormData();
		fd_spinner.top = new FormAttachment(grpCheckboxes, 10);
		fd_spinner.left = new FormAttachment(lblSpinner, 6);
		spinner.setLayoutData(fd_spinner);
		
		var lblDateTime = new Label(this, SWT.NONE);
		var fd_lblDateTime = new FormData();
		fd_lblDateTime.top = new FormAttachment(grpCheckboxes, 10);
		fd_lblDateTime.left = new FormAttachment(spinner, 30);
		lblDateTime.setLayoutData(fd_lblDateTime);
		lblDateTime.setText("DateTime:");
		
		var dateTime = new DateTime(this, SWT.BORDER);
		var fd_dateTime = new FormData();
		fd_dateTime.top = new FormAttachment(grpCheckboxes, 10);
		fd_dateTime.left = new FormAttachment(lblDateTime, 6);
		dateTime.setLayoutData(fd_dateTime);
		
		var lblSlider = new Label(this, SWT.NONE);
		var fd_lblSlider = new FormData();
		fd_lblSlider.top = new FormAttachment(spinner, 10);
		fd_lblSlider.left = new FormAttachment(lblLabel, 0, SWT.LEFT);
		lblSlider.setLayoutData(fd_lblSlider);
		lblSlider.setText("Slider:");
		
		var slider = new Slider(this, SWT.NONE);
		var fd_slider = new FormData();
		fd_slider.top = new FormAttachment(spinner, 10);
		fd_slider.left = new FormAttachment(lblSlider, 6);
		fd_slider.right = new FormAttachment(100, -10);
		slider.setLayoutData(fd_slider);
		
		var lblScale = new Label(this, SWT.NONE);
		var fd_lblScale = new FormData();
		fd_lblScale.top = new FormAttachment(slider, 10);
		fd_lblScale.left = new FormAttachment(lblLabel, 0, SWT.LEFT);
		lblScale.setLayoutData(fd_lblScale);
		lblScale.setText("Scale:");
		
		var scale = new Scale(this, SWT.NONE);
		var fd_scale = new FormData();
		fd_scale.top = new FormAttachment(slider, 10);
		fd_scale.left = new FormAttachment(lblScale, 6);
		fd_scale.right = new FormAttachment(100, -10);
		scale.setLayoutData(fd_scale);
		
		var lblList = new Label(this, SWT.NONE);
		var fd_lblList = new FormData();
		fd_lblList.top = new FormAttachment(scale, 10);
		fd_lblList.left = new FormAttachment(lblLabel, 0, SWT.LEFT);
		lblList.setLayoutData(fd_lblList);
		lblList.setText("List:");
		
		var list = new List(this, SWT.BORDER | SWT.V_SCROLL);
		for (var i = 123; i < 244; i++)
			list.add(Integer.toString(i));
		var fd_list = new FormData();
		fd_list.top = new FormAttachment(lblList, 3);
		fd_list.left = new FormAttachment(0, 10);
		fd_list.right = new FormAttachment(33, -5);
		fd_list.bottom = new FormAttachment(100, -10);
		list.setLayoutData(fd_list);
		
		var lblTable = new Label(this, SWT.NONE);
		var fd_lblTable = new FormData();
		fd_lblTable.top = new FormAttachment(scale, 10);
		fd_lblTable.left = new FormAttachment(33, 5);
		lblTable.setLayoutData(fd_lblTable);
		lblTable.setText("Table:");
		
		var table = new Table(this, SWT.BORDER | SWT.V_SCROLL);
		var tc = new TableColumn(table, SWT.LEFT);
		tc.setText("Blah");
		tc.setWidth(200);
		tc = new TableColumn(table, SWT.LEFT);
		tc.setText("Blat");
		tc.setWidth(95);
		tc = new TableColumn(table, SWT.LEFT);
		tc.setText("Zap");
		tc.setWidth(85);
		table.setHeaderVisible(true);
		for (var i = 382; i < 450; i++) {
			var row = new TableItem(table, SWT.NONE);
			for (var column = 0; column < table.getColumnCount(); column++)
				row.setText(column, Double.toHexString(Math.random()));
		}
		var fd_table = new FormData();
		fd_table.top = new FormAttachment(lblTable, 3);
		fd_table.left = new FormAttachment(33, 5);
		fd_table.right = new FormAttachment(66, -5);
		fd_table.bottom = new FormAttachment(100, -10);
		table.setLayoutData(fd_table);
				
		var lblTree = new Label(this, SWT.NONE);
		var fd_lblTree = new FormData();
		fd_lblTree.top = new FormAttachment(scale, 10);
		fd_lblTree.left = new FormAttachment(66, 5);
		lblTree.setLayoutData(fd_lblTree);
		lblTree.setText("Tree:");
		
		var tree = new Tree(this, SWT.CHECK | SWT.BORDER | SWT.V_SCROLL);
		for (var index0 = 0; index0 < 100; index0++) {
			var treeItem0 = new TreeItem(tree, 0);
			treeItem0.setText("Level 0 " + index0);
			for (var index1 = 0; index1 < Math.random() * 10 + 1; index1++) {
				var treeItem1 = new TreeItem(treeItem0, 0);
				treeItem1.setText("Level 1 " + index1);
			}
		}
		var fd_tree = new FormData();
		fd_tree.top = new FormAttachment(lblTree, 3);
		fd_tree.left = new FormAttachment(66, 5);
		fd_tree.right = new FormAttachment(100, -10);
		fd_tree.bottom = new FormAttachment(100, -10);
		tree.setLayoutData(fd_tree);
	}
}
