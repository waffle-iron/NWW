/**
 * 
 */
package org.nww.modules.pinboard.orm;

import java.util.List;

import org.nww.core.data.mongo.AbstractMongoPersistentObjectRepository;
import org.nww.modules.users.orm.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

/**
 * @author mga
 *
 */
public class MongoPinboardEntryRepository extends AbstractMongoPersistentObjectRepository<PinboardEntryImpl>
		implements PinboardEntryRepository<PinboardEntryImpl> {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	/*
	 * (non-Javadoc)
	 * @see org.nww.core.data.PersistentObjectRepository#createNew()
	 */
	@Override
	public PinboardEntryImpl createNew() {
		PinboardEntryImpl p = new PinboardEntryImpl();
		if(save(p) == null) {
			log.error("Could not save new PinboardEntry!");
		}
		
		return p;
	}

	/*
	 * (non-Javadoc)
	 * @see org.nww.modules.pinboard.orm.PinboardEntryRepository#findAllByTypeAndStatus(org.nww.modules.pinboard.PinboardEntryType, org.nww.modules.pinboard.PinboardEntryStatus)
	 */
	@Override
	public List<PinboardEntryImpl> findAllByTypeAndStatus(Integer type, Integer status) {
		Query q = new Query(Criteria.where("type").is(type).and("status").is(status));
		
		return getMongoOperations().find(q, getEntityClass());
	}

	/*
	 * (non-Javadoc)
	 * @see org.nww.modules.pinboard.orm.PinboardEntryRepository#findAllByTypeAndStatus(org.nww.modules.pinboard.PinboardEntryType, org.nww.modules.pinboard.PinboardEntryStatus, org.springframework.data.domain.Pageable)
	 */
	@Override
	public Page<PinboardEntryImpl> findAllByTypeAndStatus(Integer type, Integer status, Pageable p) {
		Query q = new Query(Criteria.where("type").is(type).and("status").is(status));
		
		long total = getMongoOperations().count(q, getEntityClass());
		
		return new PageImpl<>(getMongoOperations().find(q, getEntityClass()), p, total);
	}

	/*
	 * (non-Javadoc)
	 * @see org.nww.modules.pinboard.orm.PinboardEntryRepository#findAllByUser(org.nww.modules.users.orm.User)
	 */
	@Override
	public List<PinboardEntryImpl> findAllByUser(User u) {
		Query q = new Query(Criteria.where("owningUserUUID").is(u.getUUID()));
		
		return getMongoOperations().find(q, getEntityClass());
	}

	/*
	 * (non-Javadoc)
	 * @see org.nww.modules.pinboard.orm.PinboardEntryRepository#findAllByUser(org.nww.modules.users.orm.User, org.springframework.data.domain.Pageable)
	 */
	@Override
	public Page<PinboardEntryImpl> findAllByUser(User u, Pageable p) {
		Query q = new Query(Criteria.where("owningUserUUID").is(u.getUUID()));
		
		long total = getMongoOperations().count(q, getEntityClass());
		
		return new PageImpl<>(getMongoOperations().find(q, getEntityClass()), p, total);
	}

	/*
	 * (non-Javadoc)
	 * @see org.nww.core.data.mongo.AbstractMongoPersistentObjectRepository#getEntityClass()
	 */
	@Override
	public Class<PinboardEntryImpl> getEntityClass() {
		return PinboardEntryImpl.class;
	}

}
