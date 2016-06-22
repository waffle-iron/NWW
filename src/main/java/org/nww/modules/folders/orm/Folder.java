/**
 * 
 */
package org.nww.modules.folders.orm;

import java.util.List;

import org.nww.core.data.ExtensiblePersistentObject;

/**
 * @author mga
 *
 */
public interface Folder extends ExtensiblePersistentObject {
	/**
	 * @return the name of this folder
	 */
	public String getName();
	
	/**
	 * Set the name of this folder
	 * @param name
	 */
	public void setName(String name);
	
	/**
	 * @return true if the folder has a parent folder UUID
	 */
	public boolean hasParentFolderUUID();
	
	/**
	 * @return the parent folder UUID
	 */
	public String getParentFolderUUID();
	
	/**
	 * Set the parent folder UUID.
	 * @param parentUUID the parent folder UUID
	 */
	public void setParentFolderUUID(String parentUUID);
	
	/**
	 * @return true if this folder contains subfolder UUIDs
	 */
	public boolean hasSubFolderUUIDs();
	
	/**
	 * @return the list of subfolder UUIDs
	 */
	public List<String> getSubFolderUUIDs();
	
	/**
	 * Set the list of subfolder UUIDs.
	 * @param subFolderUUIDs the list of subfolders
	 */
	public void setSubFolderUUIDs(List<String> subFolderUUIDs);
	
	/**
	 * @return true if this folder contains content UUIDs
	 */
	public boolean hasContentUUIDs();
	
	/**
	 * @return the list of content UUIDs this folder groups
	 */
	public List<String> getContentUUIDs();
	
	/**
	 * Set the list of content UUIDs this folders groups.
	 * @param contentUUIDs the list of content UUIDs
	 */
	public void setContentUUIDs(List<String> contentUUIDs);
}
