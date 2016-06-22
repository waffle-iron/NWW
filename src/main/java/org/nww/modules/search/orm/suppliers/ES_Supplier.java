/**
 * 
 */
package org.nww.modules.search.orm.suppliers;

import org.nww.modules.search.orm.AbstractESEntity;
import org.nww.modules.search.orm.ESEntity;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author mga
 *
 */
@Document(indexName = ES_Supplier.INDEX_NAME)
public class ES_Supplier extends AbstractESEntity implements ESEntity {

	private String name;
	
	public static final String INDEX_NAME = "suppliers";
	public static final String INDEX_TYPE = "es_supplier";
	
	/**
	 * The field names to be boosted for the search result.
	 */
	public static String[] fieldNamesBoosted = {
				"name"
			};
	
	/**
	 * The field names to be handled with regular weight during search.
	 */
	public static String[] fieldNamesRegular = {
				"description", "users"
			};
	
	
	/* (non-Javadoc)
	 * @see org.nww.modules.search.orm.ESEntity#getIndexName()
	 */
	@Override
	public String getIndexName() {
		return ES_Supplier.INDEX_NAME;
	}

	/* (non-Javadoc)
	 * @see org.nww.modules.search.orm.ESEntity#getIndexType()
	 */
	@Override
	public String getIndexType() {
		return ES_Supplier.INDEX_TYPE;
	}
	
	/**
	 * @return get the name of the suppler search entry
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Set the name of the supplier search entry.
	 * @param name the name
	 */
	public void setName(String name) {
		this.name = name;
	}
}
