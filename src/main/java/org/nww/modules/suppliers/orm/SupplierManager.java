/**
 * 
 */
package org.nww.modules.suppliers.orm;

import java.util.List;

import org.nww.core.data.ExtensiblePersistentObject;
import org.nww.core.system.PersistentObjectManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Defines methods to manage suppliers.
 * 
 * @author mga
 *
 */
public interface SupplierManager extends PersistentObjectManager<Supplier, SupplierRepository<Supplier>> {
	/**
	 * Find all suppliers having the passed approval state.
	 * @param state the state
	 * @return list of suppliers
	 */
	public List<? extends Supplier> findAllByApprovalState(Integer state);
	
	/**
	 * Find a page of suppliers having the passed approval state.
	 * @param state the state
	 * @param paging the paging information
	 * @return page of suppliers
	 */
	public Page<? extends Supplier> findByApprovalState(Integer state, Pageable paging);
	
	/**
	 * Tries to find suppliers that are equal or at least very similar to the passed one.
	 * @param ref the referencing supplier object
	 * @return list of equal or similar supplier objects
	 */
	public List<? extends Supplier> findPossibleDuplicates(Supplier ref);
	
	/**
	 * Deletes duplicates and updates referenced user objects. 
	 * @param ref the referencing supplier object
	 * @param dups list of duplicate objects to be merged and removed
	 */
	public void mergeDuplicates(Supplier ref, List<? extends Supplier> dups);
	
	/**
	 * Approve the passed suppliers data.
	 * @param toApprove the supplier to approve
	 * @return the approved supplier
	 */
	public Supplier approve(Supplier toApprove);
	
	/**
	 * Decline the passed suppliers data.
	 * @param toDecline the supplier to decline
	 * @return the declined supplier
	 */
	public Supplier decline(Supplier toDecline);
	
	/**
	 * Approve the passed suppliers.
	 * @param toApprove list of suppliers to approve
	 * @return list of approved suppliers
	 */
	public List<? extends Supplier> approve(List<? extends Supplier> toApprove);
	
	/**
	 * Decline the passed suppliers.
	 * @param toDecline list of supplierss to decline
	 * @return list of declined suppliers
	 */
	public List<? extends Supplier> decline(List<? extends Supplier> toDecline);

	/**
	 * Find a supplier using its unique name.
	 * @param name the supplier name
	 * @return the supplier or null if none is found
	 */
	public Supplier findByName(String name);
	
	/**
	 * Creates a list of all suppliers for to the passed user.
	 * @param o the object suppliers are stored for
	 * @return list of suppliers, return value is guaranteed
	 */
	public List<Supplier> getSuppliersFromExtensibleObject(ExtensiblePersistentObject o);
	
	/**
	 * Save a list of suppliers for the passed extensible object.
	 * @param o the extensible persistent object
	 * @param suppliers the suppliers to be saved
	 * @return the extended object
	 */
	public <T extends ExtensiblePersistentObject> T setSuppliersForExtensibleObject(T o, List<Supplier> suppliers); 
	
	/**
	 * Filter suppliers by their approval state an return a page of the resulting list.
	 * @param unfilteredSuppliers the supplier list to be filtered
	 * @param approvalState the approval state to filter for
	 * @param page the page to find
	 * @param pageSize the pagesize for one single page
	 * @return page of filtered suppliers
	 */
	public Page<? extends Supplier> createFilteredList(Iterable<? extends Supplier> unfilteredSuppliers, Integer approvalState, Integer page, Integer pageSize);
	
	/**
	 * Create a new saved supplier instance with the passed values. Name is required, others are optional.
	 * @param name the required name of the supplier
	 * @param approvalStatus the approval status will be TO_APPROVE if null is passed
	 * @param description the supplier description, optional
	 * @param url the supplier homepage url, optional
	 * @param imageUUID the supplier image UUID, optional
	 * @return the created, saved supplier object
	 */
	public Supplier createNew(String name, Integer approvalStatus, String description, String url, String imageUUID);
}
