package org.reldb.spoing.platform;

import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.SWTResourceManager;

/**
 * Load specified icon from resources.
 * 
 * With scaling for desktop HDPI displays.
 * 
 * @author dave
 *
 */
public class IconLoader {	
	
	static String resourceDirectory = "resources";
	
	/** 
	 * Set the resource directory path. Normally invoked during startup or configuration.
	 * If not invoked, the system will default to 'resources' as the resource path.
	 * 
	 * @param resources - resource path
	 */
	public static void setResourceDirectory(String resources) {
		resourceDirectory = (resources == null) ? "resources" : resources;
	}

	/**
	 * Load a .png icon given its name (without path or extension.)
	 * 
	 * @param name - File name without path or extension
	 * @return - Image
	 */
	public static Image loadIcon(String name) {
		return SWTResourceManager.getImage(resourceDirectory + "/" + name + ".png");		
	}
	
	public static Image loadIconLarge(String name) {
		Image imgBig = SWTResourceManager.getImageOrNull(resourceDirectory + "/" + name + "@2x.png");
		if (imgBig == null)
			return SWTResourceManager.getImage(resourceDirectory + "/" + name + ".png");
		return imgBig;
	}
	
}
