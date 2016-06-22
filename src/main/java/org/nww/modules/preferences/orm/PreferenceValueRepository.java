/**
 * 
 */
package org.nww.modules.preferences.orm;

import org.nww.core.data.PersistentObjectRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Defines methods to handle preference values.
 * @author mga
 *
 */
@NoRepositoryBean
public interface PreferenceValueRepository<T extends PreferenceValue> extends PersistentObjectRepository<T> {
	/**
	 * Find a preference value entry by its preference definition ID and its object reference.
	 * @param prefDef the preference definition ID
	 * @param objectRef the object reference
	 * @return the preference value or null if none is found
	 */
	public T findByPrefDefAndObjectRef(String prefDef, String objectRef);
}
