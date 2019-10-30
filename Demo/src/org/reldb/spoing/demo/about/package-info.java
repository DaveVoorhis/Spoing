/**
 * About dialog box and the customisable panel within it.
 * 
 * The dialog box and panel are separated, because that makes it easier
 * to change the contents (just replace AboutDialogPanel) and it makes
 * it easy to edit with the WindowBuilder Eclipse plugin. WindowBuilder
 * doesn't work correctly with Spoing dialog boxes (or dialog boxes
 * in general -- you can easily create boxes that can't be easily closed)
 * but works well with Composites. 
 *  
 * @author dave
 *
 */
package org.reldb.spoing.demo.about;