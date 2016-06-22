/**
 * 
 */
package org.nww.modules.suppliers.orm;

import org.nww.core.data.ExtensiblePersistentObject;
import org.nww.modules.files.orm.FileInformation;

/**
 * Defines basic methods for a supplier POJO.
 * 
 * @author mga
 *
 */
public interface Supplier extends ExtensiblePersistentObject {
	
	public static final Integer STATUS_NOT_APPROVED = 0;
	public static final Integer STATUS_APPROVED = 1; 
	
	/**
	 * @return the name of the supplier
	 */
	public String getName();
	
	/**
	 * Set the name of the supplier
	 * @param name the name
	 */
	public void setName(String name);
	
	/**
	 * @return the description of the supplier
	 */
	public String getDescription();
	
	/**
	 * Set the description of the supplier.
	 * @param description the description
	 */
	public void setDescription(String description);
	
	/**
	 * @return true if the supplier has a description set
	 */
	public boolean hasDescription();
	
	/**
	 * @return the logo file information UUID
	 */
	public String getLogoFileInformationUUID();
	
	/**
	 * Set the logo file information object UUID.
	 * @param fiUUID the file information object UUID
	 */
	public void setLogoFileInformationUUID(String fiUUID);
	
	/**
	 * @return the logo file information object
	 */
	public FileInformation getLogoFileInformation();
	
	/**
	 * Set the logo file information object.
	 * @param fi the file information object
	 */
	public void setLogoFileInformation(FileInformation fi);
	
	/**
	 * @return true if the supplier has a file information object representing a logo attached
	 */
	public boolean hasLogo();
	
	/**
	 * @return the homepage URL
	 */
	public String getHomepage();
	
	/**
	 * Set the homepage URL.
	 * @param url the homepage URL
	 */
	public void setHomepage(String url);
	
	/**
	 * @return true if the supplier has a homepage set
	 */
	public boolean hasHomepage();
	
	/**
	 * @return the approval status
	 */
	public Integer getApprovalStatus();
	
	/**
	 * Set the approval status
	 * @param status the status value
	 */
	public void setApprovalStatus(Integer status);
	
	/**
	 * @return true if the supplier is approved
	 */
	public boolean isApproved();
}
