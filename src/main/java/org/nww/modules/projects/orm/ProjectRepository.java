/**
 * 
 */
package org.nww.modules.projects.orm;

import java.util.List;

import org.nww.core.data.PersistentObjectRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Defines the project persistent object repository and add additional methods.
 * @author mga
 */
@NoRepositoryBean
public interface ProjectRepository<T extends Project> extends PersistentObjectRepository<T> {
	/**
	 * Find a project using its unique name and owner.
	 * @param name the project name
	 * @param ownerUUID the owning users UUID
	 * @return the project or null if none is found
	 */
	public T findByNameAndOwner(String name, String ownerUUID);
	
	/**
	 * Find all projects by their owner UUID.
	 * @param ownerUUID the owner UUID
	 * @return list of projects
	 */
	public List<T> findAllByOwnerUUID(String ownerUUID);
	
	/**
	 * Find a page of projects by their owner UUID.
	 * @param ownerUUID the owner UUID
	 * @param p paging and sorting information
	 * @return page of projects
	 */
	public Page<T> findAllByOwnerUUID(String ownerUUID, Pageable p);
	
	/**
	 * Find all projects by one of their supplier UUIDs.
	 * @param supplierUUID the supplier UUID
	 * @return list of projects
	 */
	public List<T> findAllBySupplierUUID(String supplierUUID);
	
	/**
	 * Find a page of projects by one of their supplier UUIDs.
	 * @param supplierUUID the supplier UUID
	 * @param p the paging and sorting information
	 * @return page of projects
	 */
	public Page<T> findAllBySupplierUUID(String supplierUUID, Pageable p);
}
