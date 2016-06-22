/**
 * 
 */
package org.nww.modules.search.orm.users;

import org.nww.modules.profiles.orm.Profile;
import org.nww.modules.search.orm.AbstractESEntity;
import org.nww.modules.search.orm.ESEntity;
import org.nww.modules.users.orm.User;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * Implementation of an ElasticSearch entity for a {@link User} object and its {@link Profile}.
 * @author mga
 *
 */
@Document(indexName = ES_User.INDEX_NAME)
public class ES_User extends AbstractESEntity implements ESEntity {
	
	public static final String INDEX_NAME = "user";
	public static final String INDEX_TYPE = "es_user";
	
	/**
	 * The field names to be boosted for the search result.
	 */
	public static String[] fieldNamesBoosted = {
				"firstName", "lastName", "companyName", "email", "specials", "suppliers"
			};
	
	/**
	 * The field names to be handled with regular weight during search.
	 */
	public static String[] fieldNamesRegular = {
				"companyDescription", "fax", "telephone", "mobile", "homepage", "street1", "street2", "city", "postalcode", "abilities"
			};
	
	/*
	 * (non-Javadoc)
	 * @see org.nww.modules.search.orm.ESEntity#getIndexName()
	 */
	@Override
	public String getIndexName() {
		return ES_User.INDEX_NAME;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.nww.modules.search.orm.ESEntity#getIndexType()
	 */
	@Override
	public String getIndexType() {
		return ES_User.INDEX_TYPE;
	}
}
