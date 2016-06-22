/**
 * 
 */
package org.nww.modules.folders.orm;

import java.util.List;

import org.nww.core.data.mongo.AbstractMongoPersistentObjectRepository;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

/**
 * The mongo implementation for the folder repository.
 * @author mga
 *
 */
public class MongoFolderRepository extends AbstractMongoPersistentObjectRepository<FolderImpl> implements FolderRepository<FolderImpl> {

	/*
	 * (non-Javadoc)
	 * @see org.nww.core.data.PersistentObjectRepository#createNew()
	 */
	@Override
	public FolderImpl createNew() {
		return new FolderImpl();
	}

	/*
	 * (non-Javadoc)
	 * @see org.nww.modules.folders.orm.FolderRepository#findByName(java.lang.String)
	 */
	@Override
	public FolderImpl findByName(String rootName) {
		Query q = new Query(Criteria.where("name").is(rootName).and("parentFolderUUID").is(null));
		
		return getMongoOperations().findOne(q, getEntityClass());
	}

	/*
	 * (non-Javadoc)
	 * @see org.nww.modules.folders.orm.FolderRepository#findAllByParentUUID(java.lang.String)
	 */
	@Override
	public List<FolderImpl> findAllByParentUUID(String parentUUID) {
		Query q = new Query(Criteria.where("parentFolderUUID").is(parentUUID));
		return getMongoOperations().find(q, getEntityClass());
	}

	/*
	 * (non-Javadoc)
	 * @see org.nww.modules.folders.orm.FolderRepository#findSubfolderByParentUUID(java.lang.String, java.lang.String)
	 */
	@Override
	public Folder findSubfolderByParentUUID(String parentUUID, String folder) {
		Query q = new Query(Criteria.where("parentFolderUUID").is(parentUUID).and("name").is(folder));
		
		return getMongoOperations().findOne(q, getEntityClass());
	}

	/*
	 * (non-Javadoc)
	 * @see org.nww.core.data.mongo.AbstractMongoPersistentObjectRepository#getEntityClass()
	 */
	@Override
	public Class<FolderImpl> getEntityClass() {
		return FolderImpl.class;
	}
}
