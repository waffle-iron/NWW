/**
 * 
 */
package org.nww.modules.files.orm;

import org.nww.core.data.ExtensiblePersistentObject;

/**
 * @author mga
 *
 */
public interface FileInformation extends ExtensiblePersistentObject {
	/**
	 * @return the file name
	 */
	public String getName();
	
	/**
	 * Set the file name. Must be the same then the local path file name.
	 * @param name
	 */
	public void setName(String name);

	/**
	 * @return the original file name
	 */
	public String getOriginalName();
	
	/**
	 * Set the original file name before it was saved to server disk.
	 * @param name the original file name
	 */
	public void setOriginalName(String name);
	
	/**
	 * @return the local path where the file is stored
	 */
	public String getLocalPath();
	
	/**
	 * Set the local path where the file is stored.
	 * @param path the local path
	 */
	public void setLocalPath(String path);
	
	/**
	 * @return the size in bytes
	 */
	public long getSize();
	
	/**
	 * Set the size of this file in bytes.
	 * @param sizeInByte the size in bytes
	 */
	public void setSize(long sizeInByte);
	
	/**
	 * @return the description text
	 */
	public String getDescription();
	
	/**
	 * Set the description text for this file. 
	 * @param description the description text
	 */
	public void setDescription(String description);
	
	/**
	 * @return get the files content mime type
	 */
	public String getContentType();
	
	/**
	 * Set the files content mime type.
	 * @param contentType the mime type
	 */
	public void setContentType(String contentType);
	
}
