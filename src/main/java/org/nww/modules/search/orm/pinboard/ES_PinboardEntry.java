/**
 * 
 */
package org.nww.modules.search.orm.pinboard;

import org.nww.modules.search.orm.AbstractESEntity;
import org.nww.modules.search.orm.ESEntity;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author mga
 *
 */
@Document(indexName = ES_PinboardEntry.INDEX_NAME)
public class ES_PinboardEntry extends AbstractESEntity implements ESEntity {
	
	public static final String INDEX_NAME = "pinboard_entries";
	public static final String INDEX_TYPE = "es_pinboardentry";
	
	/**
	 * The field names to be boosted for the search result.
	 */
	public static String[] fieldNamesBoosted = {
				"subject"
			};
	
	/**
	 * The field names to be handled with regular weight during search.
	 */
	public static String[] fieldNamesRegular = {
				"description", "userName", "userDisplayName" // userName and userDisplayName do not get mapped by the resulting pojo -> these are search only fields
			};
	
	/*
	 * (non-Javadoc)
	 * @see org.nww.modules.search.orm.ESEntity#getIndexName()
	 */
	@Override
	public String getIndexName() {
		return ES_PinboardEntry.INDEX_NAME;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.nww.modules.search.orm.ESEntity#getIndexType()
	 */
	@Override
	public String getIndexType() {
		return ES_PinboardEntry.INDEX_TYPE;
	}
}
