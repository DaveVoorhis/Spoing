package org.reldb.spoing.demo.updates;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ProgressBar;
import org.reldb.spoing.platform.Animate;

/** Check for updates. */
public class UpdatesCheck {

	private Display display;

	private Button btnGo;
	private Label lblProgress;
	private ProgressBar progressBar;

	private SendWorker sendWorker = null;
	private Object sendWorkerMutex = Integer.valueOf(0);
	
	private String updateURL;
	private String versionString;

	public UpdatesCheck(Button btnSend, Label lblProgress, ProgressBar progressBar, String updateURL, String versionString) {
		this.display = lblProgress.getDisplay();
		this.btnGo = btnSend;
		this.lblProgress = lblProgress;
		this.progressBar = progressBar;
		this.updateURL = updateURL;
		this.versionString = versionString;
	}

	public UpdatesCheck(Display display, String updateURL, String versionString) {
		this.display = display;
		this.updateURL = updateURL;
		this.versionString = versionString;
	}

	protected void initialiseProgress(String msg, int steps) {
		if (lblProgress == null)
			return;
		btnGo.setEnabled(false);
		lblProgress.setEnabled(true);
		progressBar.setEnabled(true);
		progressBar.setMaximum(steps);
		updateProgress(msg, 0);
	}

	protected void updateProgress(String msg, int step) {
		if (lblProgress == null)
			return;
		lblProgress.setText(msg);
		progressBar.setSelection(step);
	}

	protected void resetProgress() {
		if (lblProgress == null)
			return;
		updateProgress("Progress...", 0);
		lblProgress.setEnabled(false);
		progressBar.setEnabled(false);
		btnGo.setEnabled(true);
	}

	private static class SendProgress {
		String msg;
		int progress;

		public SendProgress(String msg, int progress) {
			this.msg = msg;
			this.progress = progress;
		}
	}

	public static class SendStatus {
		private Exception exception;
		private String response;

		public SendStatus(Exception e) {
			this.exception = e;
			this.response = null;
		}

		public SendStatus(String response) {
			this.exception = null;
			this.response = response;
		}

		public boolean isOk() {
			return this.exception == null;
		}

		public Exception getException() {
			return exception;
		}

		public String getResponse() {
			return response;
		}
	}

	private class SendWorker extends Thread {

		private Animate animate;
		
		public SendWorker() {
			animate = new Animate();
			animate.start();
		}

		public void publish(SendProgress progressMessage) {
			display.asyncExec(() -> updateProgress(progressMessage.msg, progressMessage.progress));
		}

		private SendStatus status = null;

		public synchronized void run() {
			try {
				status = doInBackground();
			} catch (Exception e) {
				status = new SendStatus(e);
			}
			animate.stop();
			if (display.isDisposed())
				return;
			display.asyncExec(() -> {
				if (display.isDisposed())
					return;
				done(status);
			});
		}

		protected SendStatus doInBackground() throws Exception {			
			publish(new SendProgress("Generating message...", 10));

			HttpClient client = HttpClientBuilder.create().build();
			try {
				HttpPost httppost = new HttpPost(updateURL);

				List<NameValuePair> formparams = new ArrayList<NameValuePair>();
				formparams.add(new BasicNameValuePair("SpoingVersion", versionString));
				formparams.add(new BasicNameValuePair("OSName", System.getProperty("os.name")));
				formparams.add(new BasicNameValuePair("OSVersion", System.getProperty("os.version")));
				formparams.add(new BasicNameValuePair("OSArch", System.getProperty("os.arch")));

				HttpEntity entity = new UrlEncodedFormEntity(formparams, "UTF-8");
				httppost.setEntity(entity);

				publish(new SendProgress("Sending message...", 50));
				
				HttpResponse response = client.execute(httppost);
				entity = response.getEntity();

				publish(new SendProgress("Getting response...", 75));
				
				String result = "";
				try (BufferedReader is = new BufferedReader(new InputStreamReader(entity.getContent()))) {
					String input;
					while ((input = is.readLine()) != null)
						result += input + "\n";
				}

				publish(new SendProgress("Done", 100));
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
				
				return new SendStatus(result);
			} catch (Exception e) {
				return new SendStatus(e);
			}
		}

		protected void done(SendStatus sendStatus) {
			synchronized (sendWorkerMutex) {
				sendWorker = null;
			}
			resetProgress();
			completed(sendStatus);
		}
	}

	public void completed(SendStatus sendStatus) {
	}

	public void quit() {
	}

	public static String getUpdateURL(SendStatus sendStatus) {
		String updateURL = sendStatus.getResponse().substring("Success".length() + 1).trim();
		if (updateURL.startsWith("http"))
			return updateURL;
		return null;
	}
	
	public void doSend() {
		initialiseProgress("Sending...", 100);
		synchronized (sendWorkerMutex) {
			sendWorker = new SendWorker();
		}
		sendWorker.start();
	}

	public void doCancel() {
		synchronized (sendWorkerMutex) {
			if (sendWorker != null)
				sendWorker.interrupt();
			else
				quit();
		}
	}

}
