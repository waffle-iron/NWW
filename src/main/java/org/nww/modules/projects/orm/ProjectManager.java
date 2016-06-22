/**
 * 
 */
package org.nww.modules.projects.orm;

import java.util.List;

import org.nww.core.system.PersistentObjectManager;
import org.nww.modules.suppliers.orm.Supplier;
import org.nww.modules.users.orm.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Defines methods for a project manager.
 * @author mga
 */
public interface ProjectManager extends PersistentObjectManager<Project, ProjectRepository<Project>> {
	
	/**
	 * Find a project using its unique name and ownerUUID combination.
	 * @param name the project name
	 * @param ownerUUID the owning users UUID
	 * @return the project or null if none was found
	 */
	public Project findByNameAndOwnerUUID(String name, String ownerUUID);
	
	/**
	 * Find a project using its unique name and owner combination.
	 * @param name the project name
	 * @param owner the owning user
	 * @return the project or null if none was found
	 */
	public Project findByNameAndOwner(String name, User owner);
	
	/**
	 * Find a page of projects by their owning user.
	 * @param u the user
	 * @return list of projects
	 */
	public List<? extends Project> findByOwner(User owner);

	/**
	 * Find a page of projects by their owning user.
	 * @param u the owning user
	 * @param p the paging and sorting information
	 * @return page of projects
	 */
	public Page<? extends Project> findByOwner(User owner, Pageable p);
	
	/**
	 * Find all projects by their ownerUUID.
	 * @param ownerUUID the owning users UUID
	 * @return list of projects
	 */
	public List<? extends Project> findByOwnerUUID(String ownerUUID);
	
	/**
	 * Find a page of projects by their ownerUUID.
	 * @param ownerUUID the owning users UUID
	 * @param p the paging and sorting information
	 * @return page of projects
	 */
	public Page<? extends Project> findByOwnerUUID(String ownerUUID, Pageable p);
	
	/**
	 * Find all projects by one of their suppliers.
	 * @param s the supplier
	 * @return list of projects
	 */
	public List<? extends Project> findBySupplier(Supplier s);
	
	/**
	 * Find a page of projects by one of their suppliers.
	 * @param s the supplier
	 * @param p the paging and sorting information
	 * @return page of projects
	 */
	public Page<? extends Project> findBySupplier(Supplier s, Pageable p);
	
	/**
	 * Find all projects by one the their supplier UUIDs.
	 * @param supplierUUID the supplier UUID to be searched within the project.
	 * @return list of projects
	 */
	public List<? extends Project> findBySupplierUUID(String supplierUUID);
	
	/**
	 * Find a page of projects by one of their supplier UUIDs.
	 * @param supplierUUID the supplier UUID to be searched within the projects.
	 * @param p the paging and sorting information
	 * @return page of projects
	 */
	public Page<? extends Project> findBySupplierUUID(String supplierUUID, Pageable p);	
}
