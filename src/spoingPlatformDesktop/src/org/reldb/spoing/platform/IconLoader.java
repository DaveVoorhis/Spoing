package org.reldb.spoing.platform;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageDataProvider;
import org.eclipse.swt.widgets.Display;
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
	
	static int scaleFactor;
	static String resourceDirectory = "resources";
	
	private static ImageData grabzoom(int zoom, Image image) {
		scaleFactor = zoom;
		return image.getImageData();
	}
	
	/** 
	 * Set the resource directory path. Normally invoked during startup or configuration.
	 * If not invoked, the system will default to 'resources' as the resource path.
	 * 
	 * @param resources - resource path
	 */
	public static void setResourceDirectory(String resources) {
		resourceDirectory = (resources == null) ? "resources" : resources;
	}
	
	public static int getDPIScaling() {
		Image imgRaw = SWTResourceManager.getMissingImage();
		new Image(Display.getCurrent(), (ImageDataProvider)zoom -> grabzoom(zoom, imgRaw));
		return scaleFactor;
	}

	/**
	 * Load a .png icon given its name (without path or extension.)
	 * 
	 * @param name - File name without path or extension
	 * @return - Image
	 */
	public static Image loadIcon(String name) {
		return new Image(Display.getCurrent(), (ImageDataProvider)zoom -> {
			switch (zoom) {
			case 200:
				Image imgRaw = SWTResourceManager.getImageOrNull(resourceDirectory + "/" + name + "@2x.png");
				if (imgRaw == null) {
					imgRaw = SWTResourceManager.getImageOrNull(resourceDirectory + "/" + name + ".png");
					if (imgRaw == null)
						imgRaw = SWTResourceManager.getImage(resourceDirectory + "/" + "noimage@2x.png");
				}
				return imgRaw.getImageData();
			default:
				imgRaw = SWTResourceManager.getImageOrNull(resourceDirectory + "/" + name + ".png");
				if (imgRaw == null)
					imgRaw = SWTResourceManager.getImage(resourceDirectory + "/" + "noimage.png");
				return imgRaw.getImageData();
			}
		});
	}
	
	public static Image loadIconLarge(String name) {
		Image imgBig = SWTResourceManager.getImageOrNull(resourceDirectory + "/" + name + "@2x.png");
		if (imgBig == null)
			return SWTResourceManager.getImage(resourceDirectory + "/" + name + ".png");
		return imgBig;
	}
	
}
