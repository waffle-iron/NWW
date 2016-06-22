/**
 * 
 */
package org.nww.modules.search;

import java.util.Map;

import org.nww.core.data.PersistentObject;
import org.nww.core.data.PersistentObjectRepository;
import org.nww.core.system.PersistentObjectManager;
import org.nww.modules.search.orm.ESEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * @author mga
 *
 */
public interface SearchIndexRebuildScheduler<T extends ESEntity, K extends PersistentObject> {
	/**
	 * @return the search index repository to connect to the search engine
	 */
	public CrudRepository<T, String> getRepository();
	
	/**
	 * @return the instance of a {@link PersistentObjectManager} responsible for the handling of the persistet entity to be indexed
	 */
	public PersistentObjectManager<K, ? extends PersistentObjectRepository<K>> getPersistenceManager();
	
	/**
	 * Starts and runs the search index rebuild process.
	 */
	public void rebuildIndex();
	
	/**
	 * Creates a key value mapping of the field data for the indexed entity to be either updated or added.
	 * @param persitentEntity the persistent entity to index
	 * @return a key value mapping for the field names of the search index
	 */
	public Map<String, Object> createDataMap(K persitentEntity);
	
	/**
	 * @return an instance of the indexed entity that is only intented to be used to serve the configuration values of the entity
	 */
	public T getDummyInstance();
}
