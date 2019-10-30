package org.reldb.spoing.resources;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.rap.rwt.RWT;
import org.eclipse.rap.rwt.application.Application;
import org.eclipse.rap.rwt.service.ResourceLoader;
import org.eclipse.rap.rwt.service.ResourceManager;

public class RegisterResource {

	/*
	 * Statically registered resource.
	 */
	public static void register(Application application, String resource) {
        application.addResource(resource, new ResourceLoader() {
			@Override
			public InputStream getResourceAsStream(String resourceName) throws IOException {
				return this.getClass().getClassLoader().getResourceAsStream(resource);
			}
        });
	}

	// Give us something to load, so we can access its class loader.
	private static class Loadable {}
	
	/*
	 * Dynamically registered resource.
	 * 
	 * From http://www.eclipse.org/rap/developers-guide/devguide.php?topic=resources.html&version=3.2
	 */
	public static String register(String resourceLocation) {
		ResourceManager resourceManager = RWT.getResourceManager();
		if (!resourceManager.isRegistered(resourceLocation)) {
			InputStream inputStream = (new Loadable()).getClass().getClassLoader().getResourceAsStream(resourceLocation);
			try {
				resourceManager.register(resourceLocation, inputStream);
			} finally {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return resourceLocation;
	}

}
