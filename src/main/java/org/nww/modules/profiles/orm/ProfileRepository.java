/**
 * 
 */
package org.nww.modules.profiles.orm;

import org.nww.core.data.PersistentObjectRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author mga
 *
 */
@NoRepositoryBean
public interface ProfileRepository <T extends Profile> extends PersistentObjectRepository<T> {

}
