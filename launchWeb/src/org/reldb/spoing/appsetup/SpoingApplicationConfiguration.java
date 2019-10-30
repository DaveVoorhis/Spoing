package org.reldb.spoing.appsetup;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.eclipse.rap.rwt.application.Application;
import org.eclipse.rap.rwt.application.ApplicationConfiguration;
import org.eclipse.rap.rwt.client.WebClient;
import org.reldb.spoing.demo.feedback.CrashDialog;
import org.reldb.spoing.demo.version.Version;
import org.reldb.spoing.resources.RegisteredImageResource;

/**
 * This is the servlet configuration for a Web application.
 * 
 * You may wish to change configure(Application) to change some application behaviour, such as what happens if it crashes.
 * 
 * Note that it makes reference to the servlet entry point in getEntryPoint().
 * 
 * *** IMPORTANT: This class must be referenced in WebContent/WEB-INF/web.xml via the &lt;context-param&gt; &lt;param-name&gt; tag. ***
 * 
 * @author dave
 *
 */
public class SpoingApplicationConfiguration implements ApplicationConfiguration {

	/** Override to register application resources and entry points. */
	protected void register(Application application) {}
	
	/** Override to obtain the application entry point class or subclass. */
	protected Class<? extends SpoingEntryPoint> getEntryPoint() {
		return SpoingEntryPoint.class;
	}

	/**
	 * Register a frequently-used image file.
	 * 
	 * @param application - Application context
	 * @param imageFileName - image file name, with resource path
	 * @return - RegisteredImageResource
	 * 
	 * NOTE: No user-serviceable parts inside. 
	 */
	protected RegisteredImageResource registerImage(Application application, String imageFileName) {
		// This rather laborious machinery is necessary because Display.getCurrent() -- normally
		// needed to instantiate an Image so that we can obtain ImageData -- will return null
		// during app configuration. So this is the workaround. Note that it will not work on
		// .ico files, but .png files work as favicon images.
		var resourceStream = getClass().getClassLoader().getResourceAsStream(imageFileName);
		if (resourceStream == null) {
			System.out.println("SpoingApplicationConfiguration: Unable to obtain stream for " + imageFileName);
			return null;
		}
		BufferedImage imageData;
		try {
			imageData = ImageIO.read(resourceStream);
		} catch (IOException e) {
			System.out.println("SpoingApplicationConfiguration: Unable to read image file " + imageFileName);
			return null;
		}
		if (imageData == null) {
			System.out.println("SpoingApplicationConfiguration: BufferedImage is null for " + imageFileName);
			return null;
		}
	    return new RegisteredImageResource(application, imageFileName, imageData.getWidth(), imageData.getHeight());
	}

	/**
	 * Register specified commonly-used application resources.
	 * 
	 * @param application - Application context.
	 */
	private void registerResources(Application application) {
		if (Version.getFavIconImage() != null)
			registerImage(application, Version.getFavIconImage());
        register(application);
	}

	/**
	 * Configure this servlet. Determines what happens if the application crashes; sets up page attributes including
	 * title and favicon; and defines the application entry point, i.e., whether it's https://domain/ or https://domain/something. 
	 * 
	 * @param application - Application context.
	 */
    public void configure(Application application) {
		// Set up exception handler per http://www.eclipse.org/rap/developers-guide/devguide.php?topic=application-configuration.html&version=3.2
		application.setExceptionHandler(exception -> CrashDialog.launch(exception));
		// Set up page attributes.
        Map<String, String> properties = new HashMap<String, String>();
		properties.put(WebClient.PAGE_TITLE, Version.getAppName());
		properties.put(WebClient.PAGE_OVERFLOW, "scrollY" );
		properties.put(WebClient.BODY_HTML, "<br/>&nbsp;&nbsp;Loading...");
		if (Version.getFavIconImage() != null)
			properties.put(WebClient.FAVICON, Version.getFavIconImage());
		// properties.put(WebClient.THEME_ID, "MyCustomTheme");
		// Set up entry point.
        application.addEntryPoint("/", getEntryPoint(), properties);
        registerResources(application);
    }

}
