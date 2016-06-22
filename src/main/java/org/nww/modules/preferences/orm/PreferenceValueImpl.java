/**
 * 
 */
package org.nww.modules.preferences.orm;

import org.nww.core.data.AbstractPersistentObject;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author mga
 *
 */
@Document(collection = "preferences")
@CompoundIndex(def = "{objectRef : 1, prefDefID : 1}")
public class PreferenceValueImpl extends AbstractPersistentObject implements PreferenceValue {
	private String objectRef;
	private String prefDefID;
	private Object value;
	
	/* (non-Javadoc)
	 * @see org.nww.modules.preferences.orm.PreferenceValue#getObjectRef()
	 */
	@Override
	public String getObjectRef() {
		return this.objectRef;
	}

	/* (non-Javadoc)
	 * @see org.nww.modules.preferences.orm.PreferenceValue#setObjectRef(java.lang.String)
	 */
	@Override
	public void setObjectRef(String ref) {
		this.objectRef = ref;
	}
	
	@Override
	public String getPreferenceDefinitionID() {
		return this.prefDefID;
	}
	
	@Override
	public void setPreferenceDefinitionID(String id) {
		this.prefDefID = id;
	}

	/* (non-Javadoc)
	 * @see org.nww.modules.preferences.orm.PreferenceValue#getValue()
	 */
	@Override
	public Object getValue() {
		return this.value;
	}

	/* (non-Javadoc)
	 * @see org.nww.modules.preferences.orm.PreferenceValue#setValue(java.lang.Object)
	 */
	@Override
	public void setValue(Object value) {
		this.value = value;
	}
}
