/**
 * 
 */
package org.nww.modules.preferences.definitions.demo;

import java.util.List;

import org.nww.modules.preferences.definitions.PreferenceDefinition;
import org.springframework.stereotype.Component;

/**
 * Only a demo implementation of a prefernece definition class.
 * @author mga
 *
 */
@Component
public class DemoPrefDef implements PreferenceDefinition<String> {

	/* (non-Javadoc)
	 * @see org.nww.modules.preferences.definitions.PreferenceDefinition#getID()
	 */
	@Override
	public String getID() {
		return getValueType().getName();
	}

	/* (non-Javadoc)
	 * @see org.nww.modules.preferences.definitions.PreferenceDefinition#getValueType()
	 */
	@Override
	public Class<String> getValueType() {
		return String.class;
	}

	/* (non-Javadoc)
	 * @see org.nww.modules.preferences.definitions.PreferenceDefinition#isUserValueValid(java.lang.Object, java.util.List)
	 */
	@Override
	public boolean isUserValueValid(String value, List<String> errMsgs) {
		
		return false;
	}

	/* (non-Javadoc)
	 * @see org.nww.modules.preferences.definitions.PreferenceDefinition#getDefaultValue()
	 */
	@Override
	public String getDefaultValue() {
		return "demo";
	}

}
