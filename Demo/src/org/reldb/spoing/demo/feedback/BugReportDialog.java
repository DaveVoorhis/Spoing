package org.reldb.spoing.demo.feedback;

import org.eclipse.swt.widgets.Shell;

public class BugReportDialog extends TypicalFeedbackDialog {
	
	public BugReportDialog(Shell parent) {
		super(parent, 
			"Bug Report", 
			"BugReport",
			"bug",
			"Please complete the following and remove any information that you don't want to send. Then press the Send button to transmit it to the developers.",
			"1. Please describe the problem."
		);
	}
}
