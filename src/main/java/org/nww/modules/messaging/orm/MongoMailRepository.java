/**
 * 
 */
package org.nww.modules.messaging.orm;

import org.nww.core.data.mongo.AbstractMongoPersistentObjectRepository;

/**
 * A {@link MailRepository} implementation for the MongoDB.
 * @author mga
 *
 */
public class MongoMailRepository extends AbstractMongoPersistentObjectRepository<MailImpl>
		implements MailRepository<MailImpl> {

	/*
	 * (non-Javadoc)
	 * @see org.nww.core.data.PersistentObjectRepository#createNew()
	 */
	@Override
	public MailImpl createNew() {
		return new MailImpl();
	}

	/*
	 * (non-Javadoc)
	 * @see org.nww.core.data.mongo.AbstractMongoPersistentObjectRepository#getEntityClass()
	 */
	@Override
	public Class<MailImpl> getEntityClass() {
		return MailImpl.class;
	}
}
