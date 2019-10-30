package org.reldb.spoing.platform;

import org.eclipse.rap.rwt.service.ServerPushSession;

/**
 * Abstraction of mechanism to dynamically update the display even if push isn't supported. 
 * 
 * Necessary on the Web, which does not support push.
 * 
 * @author dave
 *
 */
public class Animate {

	private ServerPushSession pushSession = null;
	
	public void start() {
		if (pushSession != null)
			return;
		pushSession = new ServerPushSession();
		pushSession.start();
	}
	
	public void stop() {
		if (pushSession == null)
			return;
		pushSession.stop();
		pushSession = null;
	}
	
}
