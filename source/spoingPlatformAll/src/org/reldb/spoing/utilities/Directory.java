package org.reldb.spoing.utilities;

import java.io.File;

/**
 * Directory manipulation utilities.
 * 
 * @author dave
 *
 */
public class Directory {

	/**
	 * Return true if specified directory exists. Otherwise, attempt to create it and return true if successful. Return false if unable to create the directory.
	 * 
	 * @param dir - specified directory
	 */
	public static boolean chkmkdir(String dir) {
		File dirf = new File(dir);
		if (!dirf.exists())
			return dirf.mkdirs();
		return true;
	}

	/**
	 * Remove the specified file or directory. If it's a directory, remove all files in the specified directory and the directory itself.
	 * 
	 * @param dataDir
	 */
	public static void rmAll(String dir) {
		File dirf = new File(dir);
		if (dirf.isDirectory())
			for (File file: dirf.listFiles())
		    	rmAll(file.getAbsolutePath());
	    else
	        dirf.delete();
	}

	/**
	 * Return true if a specified file or directory exists.
	 * 
	 * @param fspec - path to file or directory
	 * @return - true if the specified file or directory exists; false otherwise.
	 */
	public static boolean exists(String fspec) {
		return (new File(fspec)).exists();
	}

}
