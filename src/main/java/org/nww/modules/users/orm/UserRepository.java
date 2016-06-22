/**
 * 
 */
package org.nww.modules.users.orm;

import org.nww.core.data.PersistentObjectRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author mga
 *
 */
@NoRepositoryBean
public interface UserRepository<T extends User> extends PersistentObjectRepository<T> {
	/**
	 * Finds a user using its username.
	 * @param username the username
	 * @return the user or null
	 */
	public T findByUsername(String username);
}
