/**
 * 
 */
package org.nww.modules.search.orm;

/**
 * Defines basic methods needed for any ElasticSearch entity.
 * @author mga
 *
 */
public interface ESEntity {

	/**
	 * @return the UUID of the references entity
	 */
	public String getUUID();

	/**
	 * Set the UUID of the referenced entity.
	 * @param uuid the UUID
	 */
	public void setUUID(String uuid);

	/**
	 * @return the index name of this entity
	 */
	public String getIndexName();
	
	/**
	 * @return the index type name of this entity
	 */
	public String getIndexType();
}
