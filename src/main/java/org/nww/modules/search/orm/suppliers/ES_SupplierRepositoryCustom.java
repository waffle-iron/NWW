/**
 * 
 */
package org.nww.modules.search.orm.suppliers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Defines extension methods for the supplier elastic search repository.
 * @author mga
 *
 */
public interface ES_SupplierRepositoryCustom {
	/**
	 * Find a page of suppliers that fields match the query term.
	 * @param q the query term to look up
	 * @param p the paging and sorting information
	 * @return page of {@link ES_Supplier} objects
	 */
	public Page<ES_Supplier> findSuppliers(String q, Pageable p);
}
