package org.reldb.spoing.platform;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Display;

public class ImageHelper {
	
	public static Image getMissingImage(Color color, int xSize, int ySize) {
		Image image = new Image(Display.getCurrent(), xSize, ySize);
		return image;
	}
	
	/**
	 * Returns an {@link Image} encoded by the specified {@link InputStream}.
	 * 
	 * @param stream
	 *            the {@link InputStream} encoding the image data
	 * @return the {@link Image} encoded by the specified input stream
	 */
	public static Image getImage(InputStream stream) throws IOException {
		try {
			Display display = Display.getCurrent();
			ImageData data = new ImageData(stream);
			return new Image(display, data);
		} finally {
			stream.close();
		}
	}

}
