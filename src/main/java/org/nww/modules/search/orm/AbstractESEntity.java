/**
 * 
 */
package org.nww.modules.search.orm;

import org.springframework.data.annotation.Id;

/**
 * Defines the basic attributes for ElasticSearch entities.
 * @author mga
 *
 */
public abstract class AbstractESEntity implements ESEntity {
	
	@Id
	private String uuid;
	
	/*
	 * (non-Javadoc)
	 * @see org.nww.modules.search.orm.ESEntity#getUUID()
	 */
	@Override
	public String getUUID() {
		return this.uuid;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.nww.modules.search.orm.ESEntity#setUUID(java.lang.String)
	 */
	@Override
	public void setUUID(String uuid) {
		this.uuid = uuid;
	}
}
