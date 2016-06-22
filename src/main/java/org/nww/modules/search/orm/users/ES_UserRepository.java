package org.nww.modules.search.orm.users;

import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;

/**
 * Default Spring managed elastic search user repository interface providing all default methods
 * for repository handling.
 * @author mga
 *
 */
public interface ES_UserRepository extends ElasticsearchCrudRepository<ES_User, String>, ES_UserRepositoryCustom {

}
