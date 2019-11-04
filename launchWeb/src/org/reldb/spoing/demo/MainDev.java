package org.reldb.spoing.demo;

import java.io.File;
import java.util.stream.Stream;

import org.apache.catalina.webresources.DirResourceSet;
import org.reldb.spoing.tomcat.Launcher;

/**
 * This is the class to run.
 * 
 * It launches a Tomcat instance that starts the Demo 
 * application in development mode for the Web.
 * 
 * Development mode dynamically references the bin (.class) directories of
 * your application -- via the classDirs array below -- so that you don't need
 * to repeatedly rebuild .jars and copy them into WebContent/WEB-INF/lib
 * 
 * You will have to do that once before deployment, though.
 * 
 * @author dave
 *
 */
public class MainDev {
    private static final int port = 8080;

    private static String[] classDirs = {
    	"bin",
    	"../spoingPlatformAll/bin",
    	"../spoingPlatformWeb/bin",
    	"../spoingUtilities/bin"
    };
    
	public static void main(String[] args) {
		Stream.generate("=== DEVELOPMENT LAUNCH. NOT FOR PRODUCTION!!! ==="::toString).limit(3).forEach(s -> System.out.println(s));
		System.out.println(
				"\nIf you get the following error, or a \"loader constraint violation\" similar to:\n" +
				"\tjava.lang.LinkageError: loader constraint violation: loader 'app' wants to load class\n" +
				"\torg.eclipse.swt.widgets.Composite. A different class with the same name was previously loaded by\n" +
				"\torg.apache.catalina.loader.ParallelWebappClassLoader ...\n" + 
				"It's because the contents of WebContent/WEB-INF/lib conflicts with IDE-loaded classes.\n" +
				"To correct the problem, delete all files in WebContent/WEB-INF/lib before loading your IDE.\n" +
				"Use the ant build (see build.xml) to repopulate WebContent/WEB-INF/lib prior to deployment.\n");
		(new Launcher(port, true, resources -> {
	        // --- Alternative location for "WEB-INF/classes" during development. --- 
	        
	        // DirResourceSet(WebResourceRoot root, java.lang.String webAppMount, java.lang.String base, java.lang.String internalPath)
			//
	        //  root - The WebResourceRoot this new WebResourceSet will be added to.
			//
	        //  webAppMount - The path within the web application at which this WebResourceSet will be mounted. 
	        //      For example, to add a directory of JARs to a web application, the directory would be mounted at "/WEB-INF/lib/"
			//
	        //  base - The absolute path to the directory on the file system from which the resources will be served.
			//
	        //  internalPath - The path within this new WebResourceSet where resources will be served from.
			//
			for (var classDir: classDirs) {
				File additionWebInfClasses = new File(classDir);
				var absolutePath = additionWebInfClasses.getAbsolutePath();
				System.out.println("Adding to pre resources: " + absolutePath);
		        resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes", absolutePath, "/"));
			}
		})).start();
	}
}
