/**
 * 
 */
package org.nww.modules.preferences.orm;

import org.nww.core.data.PersistentObject;

/**
 * @author mga
 *
 */
public interface PreferenceValue extends PersistentObject {
	/**
	 * @return the object reference that this preference value belongs to
	 */
	public String getObjectRef();
	
	/**
	 * Set the preference value for the passed object reference.
	 * @param ref the object reference
	 */
	public void setObjectRef(String ref);
	
	/**
	 * @return the preference definition ID
	 */
	public String getPreferenceDefinitionID();
	
	/**
	 * Set the preference definition ID.
	 * @param id the preference definition ID
	 */
	public void setPreferenceDefinitionID(String id);
	
	/**
	 * @return the uncast preference value
	 */
	public Object getValue();
	
	/**
	 * Set the preference value.
	 * @param value the value
	 */
	public void setValue(Object value);
}
