/**
 * 
 */
package org.nww.core.system;

import java.util.List;

import org.nww.core.data.PersistentObject;
import org.nww.core.data.PersistentObjectRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;

/**
 * @author MGA
 *
 */
public abstract class AbstractPersistentObjectManager<T extends PersistentObject, K extends PersistentObjectRepository<T>>
		implements PersistentObjectManager<T, K> {
	
	/* (non-Javadoc)
	 * @see org.nww.core.system.PersistentObjectManager#createNew()
	 */
	@Override
	public T createNew() {
		return getRepository().createNew();
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.nww.core.system.PersistentObjectManager#count()
	 */
	@Override
	public long count() {
		return getRepository().count();
	}

	/*
	 * (non-Javadoc)
	 * @see org.nww.core.system.PersistentObjectManager#delete(org.nww.core.data.PersistentObject)
	 */
	@Override
	public OperationResult delete(T entity) {
		try {
			getRepository().delete(entity);
		} catch (IllegalArgumentException e) {
			return new OperationResult(OperationResult.State.FAILED, 1L, null,
					"t.messages.deleteError");
		}

		return new OperationResult(OperationResult.State.SUCCESSFULL, 1L, null,
				"t.messages.deleteSuccess");
	}

	/*
	 * (non-Javadoc)
	 * @see org.nww.core.system.PersistentObjectManager#delete(java.lang.Iterable)
	 */
	@Override
	public OperationResult delete(Iterable<T> entities) {
		Long count = 0L;
		for (@SuppressWarnings("unused") T t : entities) {
			count++;
		}

		try {
			getRepository().delete(entities);
		} catch (IllegalArgumentException e) {
			return new OperationResult(OperationResult.State.FAILED,
					(String) null /* force constructor overload selection */,
					"t.messages.deleteErrorMultiple");
		}

		return new OperationResult(OperationResult.State.SUCCESSFULL, count,
				null,
				"t.messages.deleteSuccessMultiple");
	}

	/*
	 * (non-Javadoc)
	 * @see org.nww.core.system.PersistentObjectManager#delete(java.lang.String)
	 */
	@Override
	public OperationResult delete(String entityID) {
		try {
			getRepository().delete(entityID);
		} catch (IllegalArgumentException e) {
			return new OperationResult(OperationResult.State.FAILED, 1L, null,
					"t.messages.deleteError");
		}

		return new OperationResult(OperationResult.State.SUCCESSFULL, 1L, null,
				"t.messages.deleteSuccess");
	}

	/*
	 * (non-Javadoc)
	 * @see org.nww.core.system.PersistentObjectManager#deleteAll()
	 */
	@Override
	public OperationResult deleteAll() {
		Long count = this.count();

		try {
			getRepository().deleteAll();
		} catch (IllegalArgumentException e) {
			return new OperationResult(OperationResult.State.FAILED,
					(String) null /* force constructor overload selection */,
					"t.messages.deleteErrorAll");
		}

		return new OperationResult(OperationResult.State.SUCCESSFULL, count,
				null,
				"t.messages.deleteSuccessAll");
	}

	/*
	 * (non-Javadoc)
	 * @see org.nww.core.system.PersistentObjectManager#exists(java.lang.String)
	 */
	@Override
	public boolean exists(String id) {
		return getRepository().exists(id);
	}

	/*
	 * (non-Javadoc)
	 * @see org.nww.core.system.PersistentObjectManager#findOne(java.lang.String)
	 */
	@Override
	public T findOne(String id) {
		return getRepository().findOne(id);
	}

	/*
	 * (non-Javadoc)
	 * @see org.nww.core.system.PersistentObjectManager#findAll()
	 */
	@Override
	public List<T> findAll() {
		return (List<T>) getRepository().findAll();
	}

	/*
	 * (non-Javadoc)
	 * @see org.nww.core.system.PersistentObjectManager#findAll(java.lang.Iterable)
	 */
	@Override
	public Iterable<T> findAll(Iterable<String> IDs) {
		return getRepository().findAll(IDs);
	}

	/*
	 * (non-Javadoc)
	 * @see org.nww.core.system.PersistentObjectManager#findAll(org.springframework.data.domain.Pageable)
	 */
	@Override
	public Page<T> findAll(Pageable pageable) {
		return getRepository().findAll(pageable);
	}

	/*
	 * (non-Javadoc)
	 * @see org.nww.core.system.PersistentObjectManager#findAll(org.springframework.data.domain.Sort)
	 */
	@Override
	public List<T> findAll(Sort sort) {
		return (List<T>) getRepository().findAll(sort);
	}

	/*
	 * (non-Javadoc)
	 * @see org.nww.core.system.PersistentObjectManager#save(org.nww.core.data.PersistentObject)
	 */
	@Override
	public OperationResult save(T entity) {
		T savedEntity = getRepository().save(entity);
		if (null != savedEntity) {
			return new OperationResult(OperationResult.State.SUCCESSFULL, 1l,
					null, "t.forms.actions.save.successMessage", savedEntity);
		}

		return new OperationResult(OperationResult.State.FAILED, 1l, null,
				"t.forms.actions.save.couldNotSaveEntityMessage", entity);
	}

	/*
	 * (non-Javadoc)
	 * @see org.nww.core.system.PersistentObjectManager#save(java.lang.Iterable)
	 */
	@Override
	public OperationResult save(Iterable<T> entities) {
		Long count = 0l;
		
		for (@SuppressWarnings("unused") T t : entities) {
			count++;
		}

		if (null != getRepository().save(entities)) {
			return new OperationResult(OperationResult.State.SUCCESSFULL,
					count, null, "t.forms.actions.save.successMultiple");
		}

		return new OperationResult(OperationResult.State.FAILED, count, null,
				"t.forms.actions.save.couldNotSaveMultipleEntities");
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.nww.core.system.PersistentObjectManager#findByQuery(org.springframework.data.mongodb.core.query.Query)
	 */
	@Override
	public T findByQuery(Query q) {
		return getRepository().executeQuery(q);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.nww.core.system.PersistentObjectManager#findAllByQuery(org.springframework.data.mongodb.core.query.Query)
	 */
	@Override
	public List<? extends T> findAllByQuery(Query q) {
		return getRepository().executeListQuery(q);
	}

	/*
	 * (non-Javadoc)
	 * @see org.nww.core.system.PersistentObjectManager#findAllByQuery(org.springframework.data.mongodb.core.query.Query, org.springframework.data.domain.Pageable)
	 */
	@Override
	public Page<? extends T> findAllByQuery(Query q, Pageable pageable) {
		return getRepository().executePageQuery(q, pageable);
	}
}
