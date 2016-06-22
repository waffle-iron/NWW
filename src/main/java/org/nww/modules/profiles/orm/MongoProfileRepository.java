/**
 * 
 */
package org.nww.modules.profiles.orm;

import org.nww.core.data.mongo.AbstractMongoPersistentObjectRepository;

/**
 * @author mga
 *
 */
public class MongoProfileRepository extends AbstractMongoPersistentObjectRepository<ProfileImpl>
		implements ProfileRepository<ProfileImpl> {

	/* (non-Javadoc)
	 * @see org.nww.core.data.PersistentObjectRepository#createNew()
	 */
	@Override
	public ProfileImpl createNew() {
		return new ProfileImpl();
	}

	@Override
	public Class<ProfileImpl> getEntityClass() {
		return ProfileImpl.class;
	}
}
