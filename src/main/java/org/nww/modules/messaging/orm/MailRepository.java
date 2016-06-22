/**
 * 
 */
package org.nww.modules.messaging.orm;

import org.nww.core.data.PersistentObjectRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author mga
 *
 */
@NoRepositoryBean
public interface MailRepository<T extends Mail> extends PersistentObjectRepository<T> {
}
