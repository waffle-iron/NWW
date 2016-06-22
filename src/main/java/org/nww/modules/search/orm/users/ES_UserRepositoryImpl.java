package org.nww.modules.search.orm.users;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

public class ES_UserRepositoryImpl implements ES_UserRepositoryCustom {

	@Autowired
	private ElasticsearchTemplate esTemplate;
	
	@Override
	public Page<ES_User> findUsers(String q, Pageable p) {
		QueryBuilder queryBuilder = QueryBuilders.boolQuery()
				.should(QueryBuilders.fuzzyLikeThisQuery(ES_User.fieldNamesBoosted).likeText(q).boost(2f))
				.should(QueryBuilders.fuzzyLikeThisQuery(ES_User.fieldNamesRegular).likeText(q));
		
		SearchQuery query = new NativeSearchQuery(queryBuilder).setPageable(p);
		query.addIndices(ES_User.INDEX_NAME);
		
		return esTemplate.queryForPage(query, ES_User.class);
	}
	
}
