/**
 * 
 */
package org.nww.modules.search.orm.users;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Definition of extension methods for the default spring managed elastic search user repository.
 * @author mga
 *
 */
public interface ES_UserRepositoryCustom {
	/**
	 * Find a page of users that fields match the search query.
	 * @param q the query term to search for
	 * @param p the paging and sorting information
	 * @return a page of {@link ES_User} objects
	 */
	public Page<ES_User> findUsers(String q, Pageable p);
}
