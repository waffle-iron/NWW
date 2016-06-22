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
public interface PersistentObjectManager<T extends PersistentObject, K extends PersistentObjectRepository<? extends T>> {

    /**
     * Retrieve the persistent object repository.
     *
     * @return the repository object
     */
    public K getRepository();
    
    public void setRepository(K repository);
    
    /**
     * Creates a new instance of the managed objects type.
     * @return a new pojo instance
     */
    public T createNew();

    /**
     * Returns the number of objects available.
     *
     * @return the number of objects.
     */
    public long count();

    /**
     * Removes the passed entity from the persistent storage.
     *
     * @param entity the entity to be removed
     * @return an operation result object
     */
    public OperationResult delete(T entity);

    /**
     * Removes the passed entities from the persistent storage.
     *
     * @param entities the entities to be removed
     * @return an operation result object
     */
    public OperationResult delete(Iterable<T> entities);

    /**
     * Removes the entity with the passed ID.
     *
     * @param entityID the entity id of the entity to remove
     * @return an operation result object
     */
    public OperationResult delete(String entityID);

    /**
     * Removes all entities of this type.
     *
     * @return an operation result object
     */
    public OperationResult deleteAll();

    /**
     * Return whether an entity with the passed ID exists.
     *
     * @param id the id to search its entity for
     * @return boolean true if the entity exists
     */
    public boolean exists(String id);

    /**
     * Retrieves an entity by its id.
     *
     * @param id the id of the entity to return
     * @return the entity object with the passed primary key
     */
    public T findOne(String id);

    /**
     * Retrieves a list of all entities.
     *
     * @return list of entities
     */
    public List<? extends T> findAll();

    /**
     * Retrieves a list of entities for the passed ids.
     *
     * @param IDs the ids to get the entities for
     * @return list of entities
     */
    public Iterable<? extends T> findAll(Iterable<String> IDs);

    /**
     * Retrieves a list of paged entities.
     *
     * @param pageable the pagination information
     * @return list of entities
     */
    public Page<? extends T> findAll(Pageable pageable);

    /**
     * Retrieves a sorted list of entities.
     *
     * @param sort the sorting information
     * @return list of entities
     */
    public List<? extends T> findAll(Sort sort);

    /**
     * Creates or updates the passed entity.
     *
     * @param entity the object to be persisted
     * @return an operation result object
     */
    public OperationResult save(T entity);

    /**
     * Creates or updates the passes entities.
     *
     * @param entities the objects to be persisted
     * @return an operation result object
     */
    public OperationResult save(Iterable<T> entities);
    
    /**
     * Find an entity by executing the passed query.
     * @param q the query to be executed
     * @return the first result entry
     */
    public T findByQuery(Query q);
    
    /**
     * Find all entities by executing the passed query.
     * @param q the query to be executed
     * @return all resulting entries
     */
    public List<? extends T> findAllByQuery(Query q);
    
    /**
     * Find a page of entities by execting the passed query.
     * @param q the query to be executed
     * @param pageable the paging and sorting information
     * @return a page of resulting entries
     */
    public Page<? extends T> findAllByQuery(Query q, Pageable pageable);
}
