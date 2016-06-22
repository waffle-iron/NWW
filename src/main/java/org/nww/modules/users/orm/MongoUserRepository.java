/**
 * 
 */
package org.nww.modules.users.orm;

import org.nww.core.data.mongo.AbstractMongoPersistentObjectRepository;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

/**
 * @author mga
 *
 */
public class MongoUserRepository extends AbstractMongoPersistentObjectRepository<UserImpl> implements UserRepository<UserImpl> {

	@Override
	public UserImpl createNew() {
		return new UserImpl();
	}

	@Override
	public UserImpl findByUsername(String username) {
		Query q = new Query();
		q.addCriteria(Criteria.where("username").is(username));
		
		return getMongoOperations().findOne(q, getEntityClass());
	}

	@Override
	public Class<UserImpl> getEntityClass() {
		return UserImpl.class;
	}

}
