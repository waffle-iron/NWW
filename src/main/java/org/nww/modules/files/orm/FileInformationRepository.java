/**
 * 
 */
package org.nww.modules.files.orm;

import java.util.List;

import org.nww.core.data.PersistentObjectRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author mga
 *
 */
@NoRepositoryBean
public interface FileInformationRepository<T extends FileInformation> extends PersistentObjectRepository<T> {
	
	/**
	 * Find file informations by their local path.
	 * @param path the local path to the files
	 * @return a list of file information objects
	 */
	public List<T> findByLocalPath(String path);
	
	/**
	 * Find a file information by its local path and its file name.
	 * @param path the local path to the file
	 * @param name the file name
	 * @return the file information object or null if none is found
	 */
	public FileInformation findByLocalPathAndName(String path, String name);
}
