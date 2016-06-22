/**
 * 
 */
package org.nww.services.files;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import org.nww.modules.files.orm.FileInformation;

/**
 * @author mga
 *
 */
public interface ImageFileService {
	
	/**
	 * Returns true if the passed file is a loadable image.
	 * @param f the file to check
	 * @return true if the file is an image
	 */
	public boolean isImage(File f);
	
	/**
	 * Get the image file type as returned by {@link BufferedImage}.getType()
	 * @param f the file to get the image type for
	 * @return -1 if this is no image or the image type instead
	 */
	public int getImageType(File f);
	
	/**
	 * Try to load the file as an image.
	 * @param f the file to load the image from
	 * @return the image
	 * @throws IOException if the file could not be found or parsed
	 */
	public BufferedImage getImageFromFile(File f) throws IOException;
	
	/**
	 * Tries to find out whether the image is in landscape mode or not.
	 * @param i the image
	 * @return true if the image is in landscape mode, false in every other case
	 */
	public boolean isLandscapeImage(BufferedImage i); 
	
	/**
	 * Resize the passed image file to the passed width and height. Will possibly break the image scaling.
	 * @param i the image to resize
	 * @param width the desired width
	 * @param height the desired height
	 * @return the resized image
	 */
	public BufferedImage resizeImage(BufferedImage i, int width, int height);
	
	/**
	 * Resize the passed image file by keeping the width-height scale and having the bigger one beeing as big as the dimension parameter.
	 * @param i the image to resize
	 * @param dimension the max width or height depending on the image beeing landscape or portrait scaled.
	 * @return the resized image
	 */
	public BufferedImage resizeImage(BufferedImage i, int dimension);
	
	/**
	 * Checks the passed {@link FileInformation} content type for beeing a supported image type.
	 * @param fi the file information object
	 * @return true if the file is an image and is supported
	 */
	public boolean isImageFile(FileInformation fi);
	
}
