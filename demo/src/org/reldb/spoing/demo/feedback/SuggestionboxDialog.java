package org.reldb.spoing.demo.feedback;

import org.eclipse.swt.widgets.Shell;

public class SuggestionboxDialog extends TypicalFeedbackDialog {
	
	public SuggestionboxDialog(Shell parent) {
		super(parent, 
			"Submit Feedback", 
			"Feedback",
			"light-bulb", 
			"Please complete the following and remove any information that you don't want to send. Then press the Send button to transmit it to the developers.",
			"1. What would you like to tell us or suggest as a feature?"
		);
	}
}
