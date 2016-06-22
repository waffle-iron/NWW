/**
 * 
 */
package org.nww.modules.files.orm;

import java.util.List;

import org.nww.core.data.mongo.AbstractMongoPersistentObjectRepository;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

/**
 * A {@link FileInformationRepository} implementation for the MongoDB.
 * @author mga
 *
 */
public class MongoFileInformationRepository extends AbstractMongoPersistentObjectRepository<FileInformationImpl>
		implements FileInformationRepository<FileInformationImpl> {

	/* (non-Javadoc)
	 * @see org.nww.core.data.PersistentObjectRepository#createNew()
	 */
	@Override
	public FileInformationImpl createNew() {
		return new FileInformationImpl();
	}

	/* (non-Javadoc)
	 * @see org.nww.core.data.mongo.AbstractMongoPersistentObjectRepository#getEntityClass()
	 */
	@Override
	public Class<FileInformationImpl> getEntityClass() {
		return FileInformationImpl.class;
	}

	/* (non-Javadoc)
	 * @see org.nww.orm.files.FileRepository#findByLocalPath(java.lang.String)
	 */
	@Override
	public List<FileInformationImpl> findByLocalPath(String path) {
		Query q = new Query(Criteria.where("localPath").is(path));
		
		return getMongoOperations().find(q, getEntityClass());
	}

	/*
	 * (non-Javadoc)
	 * @see org.nww.orm.files.FileInformationRepository#findByLocalPathAndName(java.lang.String, java.lang.String)
	 */
	@Override
	public FileInformation findByLocalPathAndName(String path, String name) {
		Query q = new Query(Criteria.where("localPath").is(path).and("name").is(name));
		
		return getMongoOperations().findOne(q, getEntityClass());
	}
}
