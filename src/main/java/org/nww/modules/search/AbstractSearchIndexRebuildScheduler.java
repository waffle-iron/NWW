/**
 * 
 */
package org.nww.modules.search;

import java.util.List;

import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.client.Client;
import org.nww.core.data.PersistentObject;
import org.nww.modules.search.orm.ESEntity;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author mga
 *
 */
public abstract class AbstractSearchIndexRebuildScheduler<T extends ESEntity, K extends PersistentObject> 
		implements SearchIndexRebuildScheduler<T, K> {

	@Autowired
	private Client elClient;
	
	private BulkRequestBuilder bulker;
	
	/*
	 * (non-Javadoc)
	 * @see org.nww.modules.search.SearchIndexRebuildScheduler#rebuildIndex()
	 */
	@Override
	public void rebuildIndex() {
		List<? extends K> allPersisted = getPersistenceManager().findAll();
		
		allPersisted.stream().forEach(p -> {
			if(isIndexingPossible(p)) {
				if(getRepository().exists(p.getUUID())) {
					// bulk update
					prepareUpdateIndex(p.getUUID(), getRepository().findOne(p.getUUID()), p);
				}
				else {
					// bulk insert
					prepareAddToIndex(getDummyInstance(), p);
				}
			}
		});
		
		if(null != this.bulker) {
			this.elClient.bulk(bulker.request());
			// cleanup bulker
			this.bulker = null;
		}
		else {
			// TODO: add logging here
		}
	}
	
	/**
	 * @return a newly created bulker if none is created or an already existing one 
	 */
	protected BulkRequestBuilder getOrCreateBulkRequestBuilder() {
		if(null == this.bulker) {
			this.bulker = elClient.prepareBulk();
		}
		
		return this.bulker;
	}
	
	/**
	 * Prepare adding a new entry to the search index.
	 * @param e the entry mapping object
	 * @param p the persistent object to be indexed
	 * @param data the data to be added
	 */
	protected void prepareAddToIndex(T e, K p) {
		getOrCreateBulkRequestBuilder().add(
				elClient.prepareIndex(e.getIndexName(), e.getIndexType())
				.setSource(createDataMap(p))
				.setId(p.getUUID()));
	}
	
	/**
	 * Prepare updating an existing entry inside the search index.
	 * @param docID the document ID taken from the search engine
	 * @param e the entry mapping object
	 * @param p the persistent object to be indexed
	 * @param data the data to be added
	 */
	protected void prepareUpdateIndex(String docID, T e, K p) {
		getOrCreateBulkRequestBuilder().add(
				elClient.prepareUpdate(e.getIndexName(), e.getIndexType(), docID)
				.setDoc(createDataMap(p)));
	}
	
	/**
	 * Prepare deleting an existing entry inside the search index.
	 * @param docID the document ID taken from the search engine
	 * @param e the entry mapping object
	 */
	protected void prepareDeleteFromIndex(String docID, T e) {
		getOrCreateBulkRequestBuilder().add(elClient.prepareDelete(e.getIndexName(), e.getIndexType(), docID));
	}
	
	/**
	 * Checks whether it is possible to index the passed object or not.
	 * @param p to object whose indexability to check
	 * @return true if the object could be indexed
	 */
	protected abstract boolean isIndexingPossible(K p);
}
