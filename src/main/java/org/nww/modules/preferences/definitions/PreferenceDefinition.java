/**
 * 
 */
package org.nww.modules.preferences.definitions;

import java.util.List;

/**
 * Defines methods used to declare a preference.
 * @author mga
 *
 */
public interface PreferenceDefinition<T> {
	/**
	 * @return the ID of the preference definition
	 */
	public String getID();
	
	/**
	 * @return the type of the preference value
	 */
	public Class<T> getValueType();
	
	/**
	 * Check whether the users input is a valid preference value.
	 * @param value the users input
	 * @param errMsgs adds error messages if the input is not valid
	 * @return true if the input is valid, false instead
	 */
	public boolean isUserValueValid(T value, List<String> errMsgs);
	
	/**
	 * @return the preferences default value
	 */
	public T getDefaultValue();
}
