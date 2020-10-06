package org.reldb.spoing.demo.version;

import java.net.URI;

public class Version {

	public static String getAppName() {
		return "Spoing";
	}

	public static String getAppSubtitle() {
		return "DemoApp";
	}

	public static String getUpdateURL() {
		// TODO Auto-generated method stub
		return null;
	}

	public static String getSpoingVersionString() {
		return org.reldb.spoing.version.Version.getVersionString();
	}
	
	public static String getVersion() {
		return getSpoingVersionString();
	}

	public static URI getReportLogURL() {
		// TODO Auto-generated method stub
		return null;
	}

	public static String getAppID() {
		return getAppName() + " " + getSpoingVersionString();
	}

	public static String getCopyrightNotice() {
		return getAppName() + " copyright © 2019 - 2020\nDave Voorhis and reldb.org\nAll Rights Reserved"; 
	}
	
	public static String getProductPackage() {
		return "org.reldb.spoing.demo";
	}
	
	public static String getResourcePath() {
		return getProductPackage().replace('.', '/') + "/resources/";	
	}
	
	public static String getResourceDirectory() {
		return "/" + getResourcePath();
	}
	
	public static String[] getIconsPaths() {
		return new String[] {
			getResourcePath() + "Candle16.png",
			getResourcePath() + "Candle32.png",
			getResourcePath() + "Candle64.png",
			getResourcePath() + "Candle128.png",
			getResourcePath() + "Candle256.png",
			getResourcePath() + "Candle512.png",
			getResourcePath() + "Candle1024.png",
		};
	}

	public static String getFavIconImage() {
		return getIconsPaths()[3];
	}

}
