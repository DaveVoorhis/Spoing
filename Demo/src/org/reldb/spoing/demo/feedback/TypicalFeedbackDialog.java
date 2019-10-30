package org.reldb.spoing.demo.feedback;

import org.eclipse.swt.widgets.Shell;

public class TypicalFeedbackDialog extends FeedbackDialog {
	
	private String iconName;
	private String instructions;
	private String step1;

	protected TypicalFeedbackDialog(Shell parent, String title, String feedbackType, String iconName, String instructions, String step1) {
		super(parent, title, feedbackType);
		this.iconName = iconName;
		this.instructions = instructions;
		this.step1 = step1;
	}

	@Override
	protected void createContents() {
		buildContents(iconName, instructions, step1);
	}

}
