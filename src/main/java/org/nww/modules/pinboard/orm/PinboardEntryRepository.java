/**
 * 
 */
package org.nww.modules.pinboard.orm;

import java.util.List;

import org.nww.core.data.PersistentObjectRepository;
import org.nww.modules.users.orm.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author mga
 *
 */
@NoRepositoryBean
public interface PinboardEntryRepository<T extends PinboardEntry> extends PersistentObjectRepository<T> {
	/**
	 * Find all pinboard entries with a certain state value.
	 * @param type the type
	 * @param status the state
	 * @return a list of all entries found
	 */
	public List<T> findAllByTypeAndStatus(Integer type, Integer status);
	
	/**
	 * Find a page of pinboard entries with a certain state value.
	 * @param type the type
	 * @param status the state
	 * @param p the paging information
	 * @return a page of entries found
	 */
	public Page<T> findAllByTypeAndStatus(Integer type, Integer status, Pageable p);
	
	/**
	 * Find all pinboard entries owned by a certain user.
	 * @param u the user
	 * @return a list of all entries found
	 */
	public List<T> findAllByUser(User u);
	
	/**
	 * Find a page of pinboard entries owned by a certain user.
	 * @param u the user
	 * @param p the paging information
	 * @return a page of entries found
	 */
	public Page<T> findAllByUser(User u, Pageable p);
}
