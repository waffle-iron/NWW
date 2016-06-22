/**
 * 
 */
package org.nww.modules.search.orm.pinboard;

import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;

/**
 * Default Spring managed elastic search pinboard entry repository interface providing all default methods
 * for repository handling.
 * @author mga
 *
 */
public interface ES_PinboardEntryRepository 
		extends ElasticsearchCrudRepository<ES_PinboardEntry, String>, ES_PinboardEntryRepositoryCustom {

}
