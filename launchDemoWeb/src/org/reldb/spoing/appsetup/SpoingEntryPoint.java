package org.reldb.spoing.appsetup;

import org.eclipse.rap.rwt.RWT;
import org.eclipse.rap.rwt.application.AbstractEntryPoint;
import org.eclipse.rap.rwt.client.service.ExitConfirmation;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.reldb.spoing.demo.launcher.Launcher;

/**
 * Sets up a browser session, determines what happens if you try to exit it, and jumps into the application.
 * 
 * Change the line below the comment "Jump into application here" to launch your application code.
 *  
 * Note the Composite 'browser' which represents the browser client area.
 * 
 * @author dave
 *
 */
public class SpoingEntryPoint extends AbstractEntryPoint {
	private static final long serialVersionUID = 1L;
	
	private Composite browser = null;
	
	@Override
    protected void createContents(Composite browser) {
		this.browser = browser;
		
        ExitConfirmation service = RWT.getClient().getService(ExitConfirmation.class);
        service.setMessage("Are you sure you wish to exit this application?");
		
        browser.setLayout(new FillLayout());
        
		clear();	
		RWT.getUISession().addUISessionListener(sessionEvent -> System.out.println("Session " + sessionEvent.toString() + " closing."));
		
		// Jump into application here.
		Launcher.launch(browser);
		
		browser.layout();
    }

	private void clear() {
		while (browser.getChildren().length > 0)
			browser.getChildren()[0].dispose();
	}
}
