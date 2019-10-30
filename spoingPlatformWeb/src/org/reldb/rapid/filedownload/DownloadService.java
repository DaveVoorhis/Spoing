package org.reldb.rapid.filedownload;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.rap.rwt.RWT;
import org.eclipse.rap.rwt.client.service.UrlLauncher;
import org.eclipse.rap.rwt.service.ServiceHandler;

public class DownloadService implements ServiceHandler {
	private String serviceHandlerName;
	
	private static void sendBytes(byte[] download, String fileName, HttpServletResponse response) throws IOException {
		response.setContentType("application/octet-stream");
		response.setContentLength(download.length);
		String contentDisposition = "attachment; filename=\"" + fileName + "\"";
		response.setHeader("Content-Disposition", contentDisposition);
		response.getOutputStream().write(download);
	}
	
	private static abstract class DownloadInformation {
		private LocalDateTime timestamp;
		
		DownloadInformation() {
			timestamp = LocalDateTime.now();
		}

		boolean isExpired() {
			return LocalDateTime.now().minusSeconds(5).isAfter(timestamp);		
		}
		
		abstract void send(HttpServletResponse response) throws IOException;
	}
	
	private static class DownloadFile extends DownloadInformation {
		private Path file;
		
		DownloadFile(Path file) {
			this.file = file;
		}

		@Override
		void send(HttpServletResponse response) throws IOException {
			byte[] download = Files.readAllBytes(file);
			sendBytes(download, file.getFileName().toString(), response);
		}
	}
	
	private static class DownloadZip extends DownloadInformation {
		private Path[] files;
		private String zipFileName;
		
		DownloadZip(Path[] files, String zipFileName) {
			this.files = files;
			this.zipFileName = zipFileName;
		}

		private byte[] getZip() throws IOException {
			ByteArrayOutputStream download = new ByteArrayOutputStream();
			try (ZipOutputStream zipStream = new ZipOutputStream(download)) {
				for (Path file: files) {
					ZipEntry zipEntry = new ZipEntry(file.getFileName().toString());
					zipStream.putNextEntry(zipEntry);
					byte[] fileContent = Files.readAllBytes(file);
					zipStream.write(fileContent);
				}
			}
			return download.toByteArray();
		}
		
		@Override
		void send(HttpServletResponse response) throws IOException {
			byte[] download = getZip();
			sendBytes(download, zipFileName, response);
		}
	}
	
	private static ConcurrentHashMap<String, DownloadInformation> authDownloads = new ConcurrentHashMap<>();

	private void sendFail(HttpServletResponse response) throws IOException {
		byte[] download = "Sorry, your download authorisation has expired. Please return to the application to request a new download.".getBytes();
		sendBytes(download, "download.txt", response);
	}
	
	private String obtainAuthCode(DownloadInformation downloadInformation) {
		String newAuthCode = UUID.randomUUID().toString();
		authDownloads.put(newAuthCode, downloadInformation);
		return newAuthCode;
	}
	
	private String createFileDownloadUrl(DownloadInformation downloadInformation) {
		StringBuilder url = new StringBuilder();
		url.append(RWT.getServiceManager().getServiceHandlerUrl(serviceHandlerName));
		try {
			url.append('&').append("authCode").append('=').append(URLEncoder.encode(obtainAuthCode(downloadInformation), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			System.out.println("DownloadServiceHandler: Invalid encoding");
			e.printStackTrace();
		}
		return url.toString();
	}
	
	/** Create and register a download service handler. */
	private DownloadService() {
		serviceHandlerName = "downloader" + UUID.randomUUID().toString();
		RWT.getServiceManager().registerServiceHandler(serviceHandlerName, this);
	}

	/** Initiate a download of a file. */
	private void instanceDownloadFile(Path file) {
		UrlLauncher launcher = RWT.getClient().getService(UrlLauncher.class);
		launcher.openURL(createFileDownloadUrl(new DownloadFile(file)));
	}
	
	/** Initiate a download of a zip of a collection of files. */
	private void instanceDownloadZipFile(Path[] files, String zipFileName) {
		UrlLauncher launcher = RWT.getClient().getService(UrlLauncher.class);
		launcher.openURL(createFileDownloadUrl(new DownloadZip(files, zipFileName)));
	}
	
	/** Not intended to be called directly. */
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String authCode = request.getParameter("authCode");
		DownloadInformation downloadInformation = authDownloads.get(authCode);
		if (downloadInformation == null) {
			sendFail(response);
			return;
		}
		authDownloads.remove(authCode);
		if (downloadInformation.isExpired()) {
			sendFail(response);
			return;
		}
		downloadInformation.send(response);
	}
	
	private static DownloadService instance = null;
	
	private static DownloadService getInstance() {
		if (instance == null)
			instance = new DownloadService();
		return instance;
	}
	
	/** Initiate a download of a file. */
	public static void downloadFile(Path file) {
		getInstance().instanceDownloadFile(file);
	}
	
	/** Initiate a download of a zip of a collection of files. */
	public static void downloadZipFile(Path[] files, String zipFileName) {
		getInstance().instanceDownloadZipFile(files, zipFileName);
	}
}