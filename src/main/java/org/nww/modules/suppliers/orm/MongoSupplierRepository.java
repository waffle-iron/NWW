/**
 * 
 */
package org.nww.modules.suppliers.orm;

import java.util.List;

import org.nww.core.data.mongo.AbstractMongoPersistentObjectRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

/**
 * Implementation of the {@link SupplierRepository} interface for the Mongo DB database.
 * @author mga
 *
 */
public class MongoSupplierRepository extends AbstractMongoPersistentObjectRepository<SupplierImpl>
		implements SupplierRepository<SupplierImpl> {

	/* (non-Javadoc)
	 * @see org.nww.core.data.PersistentObjectRepository#createNew()
	 */
	@Override
	public SupplierImpl createNew() {
		return new SupplierImpl();
	}

	/* (non-Javadoc)
	 * @see org.nww.core.data.mongo.AbstractMongoPersistentObjectRepository#getEntityClass()
	 */
	@Override
	public Class<SupplierImpl> getEntityClass() {
		return SupplierImpl.class;
	}

	/*
	 * (non-Javadoc)
	 * @see org.nww.modules.suppliers.orm.SupplierRepository#findAllByApprovalState(java.lang.Integer)
	 */
	@Override
	public List<SupplierImpl> findAllByApprovalState(Integer state) {
		Query q = new Query(Criteria.where("approvalState").is(state));
		
		return getMongoOperations().find(q, getEntityClass());
	}

	/*
	 * (non-Javadoc)
	 * @see org.nww.modules.suppliers.orm.SupplierRepository#findByApprovalState(java.lang.Integer, org.springframework.data.domain.Pageable)
	 */
	@Override
	public Page<SupplierImpl> findByApprovalState(Integer state, Pageable p) {
		Query q = new Query(Criteria.where("approvalState").is(state));
		
		long total = getMongoOperations().count(q, getEntityClass());
		
		q.with(p);
		
		return new PageImpl<>(getMongoOperations().find(q, getEntityClass()), p, total);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.nww.modules.suppliers.orm.SupplierRepository#findByName(java.lang.String)
	 */
	@Override
	public SupplierImpl findByName(String name) {
		Query q = new Query(Criteria.where("name").is(name));
		
		return getMongoOperations().findOne(q, getEntityClass());
	}
}
