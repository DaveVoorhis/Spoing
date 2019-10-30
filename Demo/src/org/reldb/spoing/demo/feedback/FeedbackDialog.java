package org.reldb.spoing.demo.feedback;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TreeItem;
import org.reldb.spoing.demo.version.Version;
import org.reldb.spoing.platform.IconLoader;
import org.reldb.spoing.utilities.DialogAbstract;
import org.reldb.spoing.utilities.MessageDialog;

public abstract class FeedbackDialog extends DialogAbstract {	
	private TreeItem report;	
	private Feedback phoneHome;
	private String feedbackType;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	protected FeedbackDialog(Shell parent, String title, String feedbackType) {
		super(parent);
		setText(title);
		this.feedbackType = feedbackType;
	}
	
	private static String getCurrentTimeStamp() {
	    return (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS Z")).format(new Date());
	}	

	private void setupTreeItem(TreeItem item, String text) {
		item.setText(text);
		item.setChecked(true);
		item.setExpanded(true);
	}
	
	private TreeItem newTreeItem(TreeItem parent, String text) {
		TreeItem item = new TreeItem(parent, SWT.None);
		setupTreeItem(item, text);
		return item;
	}
	
	private TreeItem newTreeItem(Tree parent, String text) {
		TreeItem item = new TreeItem(parent, SWT.None);
		setupTreeItem(item, text);
		return item;
	}
	
	private void putStacktraceInTree(TreeItem root, StackTraceElement[] trace) {
		TreeItem stackTraceTree = newTreeItem(root, "StackTrace");
		for (StackTraceElement element: trace)
			newTreeItem(stackTraceTree, element.toString());
	}
	
	private void checkPath(TreeItem item, boolean checked, boolean grayed) {
	    if (item == null) 
	    	return;
	    if (grayed) {
	        checked = true;
	    } else {
	        int index = 0;
	        TreeItem[] items = item.getItems();
	        while (index < items.length) {
	            TreeItem child = items[index];
	            if (child.getGrayed() || checked != child.getChecked()) {
	                checked = grayed = true;
	                break;
	            }
	            index++;
	        }
	    }
	    item.setChecked(checked);
	    item.setGrayed(grayed);
	    checkPath(item.getParentItem(), checked, grayed);
	}

	private void checkItems(TreeItem item, boolean checked) {
	    item.setGrayed(false);
	    item.setChecked(checked);
	    TreeItem[] items = item.getItems();
	    for (int i = 0; i < items.length; i++) {
	        checkItems(items[i], checked);
	    }
	}
	
	private void putExceptionInTree(TreeItem root, Throwable t) {
		if (t != null) {
			newTreeItem(root, "ErrorClass: " + t.getClass().toString());
			putStacktraceInTree(root, t.getStackTrace());
			newTreeItem(root, "Message: " + t.toString());
			if (t.getCause() != null) {
				TreeItem cause = newTreeItem(root, "Cause");
				putExceptionInTree(cause, t.getCause());
			}
		} else {
			newTreeItem(root, "Error details unavailable.");
		}		
	}
	
	private void putClientInfoInTree(String clientVersion) {
		newTreeItem(report, "Timestamp: " + getCurrentTimeStamp().toString());
		newTreeItem(report, "Version: " + clientVersion);
		newTreeItem(report, "Java version: " + System.getProperty("java.version"));
		newTreeItem(report, "Java vendor: " + System.getProperty("java.vendor"));
		newTreeItem(report, "Java URL: " + System.getProperty("java.vendor.url"));
		newTreeItem(report, "Java home: " + System.getProperty("java.home"));
		newTreeItem(report, "OS Name: " + System.getProperty("os.name"));
		newTreeItem(report, "OS Version: " + System.getProperty("os.version"));
		newTreeItem(report, "OS Architecture: " + System.getProperty("os.arch"));
	}
	
	private void completed(Feedback.SendStatus sendStatus) {
		String failHeading = "Feedback Failed";
		try {
			if (sendStatus.getResponse() != null && sendStatus.getResponse().startsWith("Success")) {
				Shell parent = getParent();
				quit();
	    		MessageDialog.openInformation(parent, "Feedback Sent", sendStatus.getResponse());
	    		return;
	        } else
	        	if (sendStatus.getException() != null) {
        			sendStatus.getException().printStackTrace();
	        		MessageDialog.openError(getParent(), failHeading, "Unable to send feedback: " + sendStatus.getException().toString());
	        	} else
	        		MessageDialog.openError(getParent(), failHeading, "Unable to send feedback: " + sendStatus.getResponse());
		} catch (Exception e1) {
    		String exceptionName = e1.getClass().getName().toString(); 
    		if (exceptionName.equals("java.lang.InterruptedException"))
    			MessageDialog.openError(getParent(), failHeading, "Send Cancelled");
    		else {
    			e1.printStackTrace();
    			MessageDialog.openError(getParent(), failHeading, "Unable to send feedback: " + e1.toString());
    		}
		}
	}

	private FeedbackInfo getFeedbackInfo(String feedback, String email, Tree treeDetails) {
		FeedbackInfo report = new FeedbackInfo(feedbackType);
		report.addString("Feedback", feedback);
		report.addString("Email", email);
		report.addTree(treeDetails.getItems()[0]);
		return report;
	}
	
	private void doSend(String feedback, String email, Tree treeDetails) {
		phoneHome.doSend(getFeedbackInfo(feedback, email, treeDetails).toString());
	}
	
	private void doCancel() {
		phoneHome.doCancel();
	}
	
	private void quit() {
		shell.dispose();
	}
	
	protected void populateTree() {
		putClientInfoInTree(Version.getVersion());		
	}
	
	protected void putExceptionInTree(Throwable t) {
		TreeItem root = newTreeItem(report, "JavaException");
		putExceptionInTree(root, t);
	}
	
	protected void buildContents(String iconName, String instructions, String step1) {
		shell.setSize(750, 650);
		shell.setLayout(new FormLayout());

		Composite panelIntro = new Composite(shell, SWT.NONE);
		panelIntro.setLayout(new GridLayout(2, false));
		FormData fd_panelIntro = new FormData();
		fd_panelIntro.top = new FormAttachment(0);
		fd_panelIntro.right = new FormAttachment(100);
		fd_panelIntro.left = new FormAttachment(0);
		panelIntro.setLayoutData(fd_panelIntro);

		Label lblIcon = new Label(panelIntro, SWT.NONE);
		lblIcon.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));
		lblIcon.setImage(IconLoader.loadIcon(iconName));

		Label lblInstructions = new Label(panelIntro, SWT.WRAP);
		lblInstructions.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		lblInstructions.setText(instructions);

		Label lblStep1 = new Label(shell, SWT.NONE);
		FormData fd_lblStep1 = new FormData();
		fd_lblStep1.top = new FormAttachment(panelIntro, 10);
		fd_lblStep1.left = new FormAttachment(0, 10);
		lblStep1.setLayoutData(fd_lblStep1);
		lblStep1.setText(step1);

		var textFeedback = new Text(shell, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		FormData fd_textWhatHappened = new FormData();
		fd_textWhatHappened.top = new FormAttachment(lblStep1, 6);
		fd_textWhatHappened.left = new FormAttachment(0, 10);
		fd_textWhatHappened.right = new FormAttachment(100, -10);
		textFeedback.setLayoutData(fd_textWhatHappened);

		Label lblStep2 = new Label(shell, SWT.NONE);
		FormData fd_lblStep2 = new FormData();
		lblStep2.setLayoutData(fd_lblStep2);
		lblStep2.setText(
				"2. What is your email address?  (optional - we'll only use it if we need to ask you further questions)");

		var textEmailAddress = new Text(shell, SWT.BORDER);
		FormData fd_textEmailAddress = new FormData();
		textEmailAddress.setLayoutData(fd_textEmailAddress);

		Label lblStep3 = new Label(shell, SWT.NONE);
		FormData fd_lblStep3 = new FormData();
		lblStep3.setLayoutData(fd_lblStep3);
		lblStep3.setText("3. Examine these further details and un-check anything you don't want to send.");

		Tree treeDetails = new Tree(shell, SWT.BORDER | SWT.CHECK);
		FormData fd_treeDetails = new FormData();
		treeDetails.setLayoutData(fd_treeDetails);
		fd_treeDetails.height = 75;
		treeDetails.addListener(SWT.Selection, event -> {
			if (event.detail == SWT.CHECK) {
				TreeItem item = (TreeItem) event.item;
				boolean checked = item.getChecked();
				checkItems(item, checked);
				checkPath(item.getParentItem(), checked, false);
			}
		});

		var lblProgress = new Label(shell, SWT.NONE);
		fd_treeDetails.bottom = new FormAttachment(lblProgress, -10);
		FormData fd_lblProgress = new FormData();
		fd_lblProgress.right = new FormAttachment(textFeedback, 0, SWT.RIGHT);
		lblProgress.setLayoutData(fd_lblProgress);
		lblProgress.setText("Progress...");

		var progressBar = new ProgressBar(shell, SWT.NONE);
		FormData fd_progressBar = new FormData();
		progressBar.setLayoutData(fd_progressBar);

		var btnCancel = new Button(shell, SWT.NONE);
		FormData fd_btnCancel = new FormData();
		btnCancel.setLayoutData(fd_btnCancel);
		btnCancel.setText("Cancel");
		btnCancel.addListener(SWT.Selection, e -> doCancel());

		var btnSend = new Button(shell, SWT.NONE);
		fd_btnCancel.right = new FormAttachment(btnSend, -6);
		FormData fd_btnSend = new FormData();
		fd_btnSend.bottom = new FormAttachment(100, -10);
		fd_btnSend.right = new FormAttachment(100, -10);
		btnSend.setLayoutData(fd_btnSend);
		btnSend.setText("Send");
		btnSend.addListener(SWT.Selection, e -> doSend(textFeedback.getText().trim(), textEmailAddress.getText().trim(), treeDetails));

		fd_btnCancel.bottom = new FormAttachment(100, -10);
		fd_btnCancel.right = new FormAttachment(btnSend, -10);

		fd_progressBar.bottom = new FormAttachment(btnCancel, -10);
		fd_progressBar.left = new FormAttachment(0, 10);
		fd_progressBar.right = new FormAttachment(100, -10);

		fd_lblProgress.bottom = new FormAttachment(progressBar, -6);
		fd_lblProgress.left = new FormAttachment(0, 10);
		fd_treeDetails.left = new FormAttachment(0, 10);
		fd_treeDetails.right = new FormAttachment(100, -10);

		fd_lblStep3.bottom = new FormAttachment(treeDetails, -6);
		fd_lblStep3.left = new FormAttachment(0, 10);

		fd_textEmailAddress.bottom = new FormAttachment(lblStep3, -10);
		fd_textEmailAddress.left = new FormAttachment(0, 10);
		fd_textEmailAddress.right = new FormAttachment(100, -10);

		fd_lblStep2.bottom = new FormAttachment(textEmailAddress, -6);
		fd_lblStep2.left = new FormAttachment(0, 10);

		fd_textWhatHappened.bottom = new FormAttachment(lblStep2, -10);

		lblProgress.setEnabled(false);
		progressBar.setEnabled(false);
		
		report = newTreeItem(treeDetails, "Details");
		phoneHome = new Feedback(btnSend, lblProgress, progressBar) {
		    public void completed(SendStatus sendStatus) {	
		    	FeedbackDialog.this.completed(sendStatus);
		    }
			public void quit() {
				FeedbackDialog.this.quit();
			}
		};
		phoneHome.resetProgress();

		populateTree();
		
		report.setExpanded(true);		
	}
}
