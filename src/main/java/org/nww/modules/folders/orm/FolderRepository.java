/**
 * 
 */
package org.nww.modules.folders.orm;

import java.util.List;

import org.nww.core.data.PersistentObjectRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Define methods for a folder repository.
 * @author mga
 *
 */
@NoRepositoryBean
public interface FolderRepository<T extends Folder> extends PersistentObjectRepository<T> {
	/**
	 * Find a folder using its root name. Does only find a folder if it does not have a parent.
	 * @param rootName the name the folder
	 * @return the folder or null if none is found
	 */
	public T findByName(String rootName);
	
	/**
	 * Find all folders that are a child of the folder with the passed UUID.
	 * @param parentUUID the parent folder UUID
	 * @return a list of sub folders
	 */
	public List<T> findAllByParentUUID(String parentUUID);

	/**
	 * Find a folder using its parent folder and its name.
	 * @param parentUUID the parent folders UUID
	 * @param folder the folder name
	 * @return the folder or null if none is found
	 */
	public Folder findSubfolderByParentUUID(String parentUUID, String folder);
}
