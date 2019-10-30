package org.reldb.spoing.utilities;

import org.eclipse.swt.SWT;

public class PlatformDetect {
	//
	// Methods for working with the windowing system.
	//
	// Copied from JFace Util class.
	//
	
	/**
	 * Windowing system constant.
	 */
	public static final String WS_WIN32 = "win32";//$NON-NLS-1$
	
	/**
	 * Windowing system constant.
	 */
	@Deprecated
	public static final String WS_MOTIF = "motif";//$NON-NLS-1$
	
	/**
	 * Windowing system constant.
	 */
	public static final String WS_GTK = "gtk";//$NON-NLS-1$
	
	/**
	 * Windowing system constant.
	 */
	@Deprecated
	public static final String WS_PHOTON = "photon";//$NON-NLS-1$
	
	/**
	 * Windowing system constant.
	 */
	@Deprecated
	public static final String WS_CARBON = "carbon";//$NON-NLS-1$
	
	/**
	 * Windowing system constant.
	 */
	public static final String WS_COCOA = "cocoa";//$NON-NLS-1$
	
	/**
	 * Windowing system constant.
	 */
	public static final String WS_WPF = "wpf";//$NON-NLS-1$
	
	/**
	 * Windowing system constant.
	 */
	public static final String WS_UNKNOWN = "unknown";//$NON-NLS-1$
	
	/**
	 * Common WS query helper method.
	 * @return <code>true</code> for windows platforms
	 */
	public static boolean isWindows() {
		final String ws = SWT.getPlatform();
		return WS_WIN32.equals(ws) || WS_WPF.equals(ws);
	}
	
	/**
	 * Common WS query helper method.
	 * @return <code>true</code> for Mac platforms
	 */
	public static boolean isMac() {
		final String ws = SWT.getPlatform();
		return WS_CARBON.equals(ws) || WS_COCOA.equals(ws);
	}
	
	/**
	 * Common WS query helper method.
	 * @return <code>true</code> for Linux platform
	 */
	public static boolean isLinux() {
		final String ws = SWT.getPlatform();
		return WS_GTK.equals(ws);
	}
	
	/**
	 * Common WS query helper method.
	 * @return <code>true</code> for GTK platforms
	 */
	public static boolean isGtk() {
		final String ws = SWT.getPlatform();
		return WS_GTK.equals(ws);
	}
	
	/**
	 * Common WS query helper method.
	 * @return <code>true</code> for the Cocoa platform.
	 */
	public static boolean isCocoa() {
		final String ws = SWT.getPlatform();
		return WS_COCOA.equals(ws);
	}
	
	/**
	 * Common WS query helper method.
	 * @return <code>true</code> for WPF
	 */
	public static boolean isWpf() {
		final String ws = SWT.getPlatform();
		return WS_WPF.equals(ws);
	}
	
	/**
	 * Common WS query helper method.
	 * @return <code>true</code> for win32
	 */
	public static boolean isWin32() {
		final String ws = SWT.getPlatform();
		return WS_WIN32.equals(ws);
	}
	
	/**
	 * Common WS query helper method.
	 * @return the SWT windowing platform string.
	 * @see SWT#getPlatform()
	 */
	public static String getWS() {
		return SWT.getPlatform();
	}
	
	/** True only if running in Web platform. */
	public static boolean isWeb() {
		return SWT.getPlatform().equals("rap");
	}
	
}

