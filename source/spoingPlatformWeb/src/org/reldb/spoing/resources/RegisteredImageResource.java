package org.reldb.spoing.resources;

import org.eclipse.rap.rwt.RWT;
import org.eclipse.rap.rwt.application.Application;

public class RegisteredImageResource {

	private int width;
	private int height;
	private String resourceIdentifier;

	public RegisteredImageResource(Application application, String resourceIdentifier, int width, int height) {
		this.resourceIdentifier = resourceIdentifier;
		this.width = width;
		this.height = height;
		RegisterResource.register(application, resourceIdentifier);
	}
	
	public RegisteredImageResource(String resourceIdentifier, int width, int height) {
		this.resourceIdentifier = resourceIdentifier;
		this.width = width;
		this.height = height;
		RegisterResource.register(resourceIdentifier);
	}
	
	public String getHTML() {
		String src = RWT.getResourceManager().getLocation(resourceIdentifier);
		return "<img width='" + width + "' height='" + height + "' src='" + src + "'/>";
	}

}
