/**
 * 
 */
package org.nww.modules.preferences.orm;

import org.nww.core.data.mongo.AbstractMongoPersistentObjectRepository;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

/**
 * MongoDB specific implementation of the {@link PreferenceValueRepository} interface.
 * @author mga
 *
 */
public class MongoPreferenceValueRepository extends AbstractMongoPersistentObjectRepository<PreferenceValueImpl>
		implements PreferenceValueRepository<PreferenceValueImpl> {

	/* (non-Javadoc)
	 * @see org.nww.core.data.PersistentObjectRepository#createNew()
	 */
	@Override
	public PreferenceValueImpl createNew() {
		return new PreferenceValueImpl();
	}

	/* (non-Javadoc)
	 * @see org.nww.core.data.mongo.AbstractMongoPersistentObjectRepository#getEntityClass()
	 */
	@Override
	public Class<PreferenceValueImpl> getEntityClass() {
		return PreferenceValueImpl.class;
	}

	/*
	 * (non-Javadoc)
	 * @see org.nww.modules.preferences.orm.PreferenceValueRepository#findByPrefDefAndObjectRef(java.lang.String, java.lang.String)
	 */
	@Override
	public PreferenceValueImpl findByPrefDefAndObjectRef(String prefDef, String objectRef) {
		Query q = new Query(Criteria.where("prefDefID").is(prefDef).and("objectRef").is(objectRef)); 
		
		return getMongoOperations().findOne(q, getEntityClass());
	}

}
