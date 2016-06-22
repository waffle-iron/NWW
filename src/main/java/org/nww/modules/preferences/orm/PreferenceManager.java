/**
 * 
 */
package org.nww.modules.preferences.orm;

import org.nww.core.system.PersistentObjectManager;

/**
 * @author mga
 *
 */
public interface PreferenceManager extends PersistentObjectManager<PreferenceValue, PreferenceValueRepository<PreferenceValue>> {
	/**
	 * Get the value of the preference either specifically configured for the passed object reference or the default value.
	 * @param prefDefID the preference definition id
	 * @param objectRef the object reference
	 * @return the preference value
	 */
	public <T> T getValue(String prefDefID, String objectRef);
}
