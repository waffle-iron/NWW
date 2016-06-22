/**
 * 
 */
package org.nww.modules.suppliers.orm;

import java.util.List;

import org.nww.core.data.PersistentObjectRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Defines methods for a supplier repository.
 * @author mga
 *
 */
@NoRepositoryBean
public interface SupplierRepository<T extends Supplier> extends PersistentObjectRepository<T> {
	/**
	 * Find all suppliers having the passed approval state.
	 * @param state the state
	 * @return list of suppliers
	 */
	public List<T> findAllByApprovalState(Integer state);
	
	/**
	 * Find a page of suppliers having the passed approval state.
	 * @param state the state
	 * @param paging the paging information
	 * @return page of suppliers
	 */
	public Page<T> findByApprovalState(Integer state, Pageable paging);

	/**
	 * Find a supplier using its unique name.
	 * @param name the name of the supplier
	 * @return the supplier or null if none is found
	 */
	public T findByName(String name);
}
