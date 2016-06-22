/**
 * 
 */
package org.nww.modules.preferences.orm;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.nww.core.system.AbstractPersistentObjectManager;
import org.nww.core.utils.ApplicationContextHelper;
import org.nww.modules.preferences.definitions.PreferenceDefinition;

/**
 * @author mga
 *
 */
public class PreferenceManagerImpl 
		extends AbstractPersistentObjectManager<PreferenceValue, PreferenceValueRepository<PreferenceValue>> 
		implements PreferenceManager {

	@Resource(name = "PreferenceValueRepository")
	private PreferenceValueRepository<PreferenceValue> repo;
	
	@SuppressWarnings("rawtypes")
	private Collection<PreferenceDefinition> prefDefs = ApplicationContextHelper.getBeansByClass(PreferenceDefinition.class).values();
	private Map<String, PreferenceDefinition<?>> sortedPrefDefs = new HashMap<>();
	
	@PostConstruct
	public void postConstruct() {
		prefDefs.forEach(def -> {
			sortedPrefDefs.put(def.getID(), def);
		});
	}
	
	/* (non-Javadoc)
	 * @see org.nww.core.system.PersistentObjectManager#getRepository()
	 */
	@Override
	public PreferenceValueRepository<PreferenceValue> getRepository() {
		return this.repo;
	}

	/* (non-Javadoc)
	 * @see org.nww.core.system.PersistentObjectManager#setRepository(org.nww.core.data.PersistentObjectRepository)
	 */
	@Override
	public void setRepository(PreferenceValueRepository<PreferenceValue> repository) {
		this.repo = repository;
	}

	/* (non-Javadoc)
	 * @see org.nww.core.system.PersistentObjectManager#createNew()
	 */
	@Override
	public PreferenceValue createNew() {
		return this.repo.createNew();
	}

	/* (non-Javadoc)
	 * @see org.nww.modules.preferences.orm.PreferenceManager#getValue(java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T getValue(String prefDefID, String objectRef) {
		// try to find a stored individual value for the preference
		PreferenceValue storedValue = getRepository().findByPrefDefAndObjectRef(prefDefID, objectRef);
		
		if(null == storedValue) {
			return (T) this.sortedPrefDefs.get(prefDefID).getDefaultValue();
		}
		
		return (T) storedValue.getValue();
	}

}
