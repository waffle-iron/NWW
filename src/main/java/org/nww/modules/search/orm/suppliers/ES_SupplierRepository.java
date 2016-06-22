/**
 * 
 */
package org.nww.modules.search.orm.suppliers;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;

/**
 * Spring managed ElasticSearch supplier search repository.
 * 
 * @author mga
 */
public interface ES_SupplierRepository extends ElasticsearchCrudRepository<ES_Supplier, String>, ES_SupplierRepositoryCustom {
	
	/**
	 * Find a supplier using its name.
	 * @param name the name of the supplier
	 * @return a supplier or null if none is found
	 */
	public ES_Supplier findByName(String name);
	
	/**
	 * Find suppliers with a name like the passed parameter.
	 * @param name the name
	 * @return list of possible suppliers
	 */
	public List<ES_Supplier> findByNameLike(String name);
	
	/**
	 * Find suppliers with a name starting with the passed parameter.
	 * @param name the name part to start with
	 * @return list of possible suppliers
	 */
	public List<ES_Supplier> findByNameStartsWith(String name);
}
