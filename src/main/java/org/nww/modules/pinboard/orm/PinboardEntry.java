/**
 * 
 */
package org.nww.modules.pinboard.orm;

import java.util.Date;

import org.nww.core.data.ExtensiblePersistentObject;
import org.nww.modules.users.orm.User;

/**
 * Defines methods neededd for pinboard entries
 * 
 * @author mga
 */
public interface PinboardEntry extends ExtensiblePersistentObject {
	
	public static final Integer TYPE_OFFER = 0;
	public static final Integer TYPE_REQUEST = 1;
	
	public static final Integer STATUS_OPEN = 0;
	public static final Integer STATUS_CLOSED = 1;
	
	
	/**
	 * @return the entries type
	 */
	public Integer getType();
	
	/**
	 * Set the entries type.
	 * @param type the type
	 */
	public void setType(Integer type);
	
	/**
 	 * @return the entries status
	 */
	public Integer getStatus();
	
	/**
	 * Set the entries status.
	 * @param status the status
	 */
	public void setStatus(Integer status);
	
	/**
	 * @return get the entries subject
	 */
	public String getSubject();
	
	/**
	 * Set the entries subject.
	 * @param subject the subject
	 */
	public void setSubject(String subject);
	
	/**
	 * @return get the entries description
	 */
	public String getDescription();
	
	/**
	 * Set the entries description.
	 * @param description the description
	 */
	public void setDescription(String description);
	
	/**
	 * @return the entries owning users UUID
	 */
	public String getOwningUserUUID();
	
	/**
	 * Set the entries owning users UUID. 
	 * @param owningUserUUID
	 */
	public void setOwningUserUUID(String owningUserUUID);
	
	/**
	 * @return the entries owning user
	 */
	public User getOwningUser();
	
	/**
	 * Set the entries owning user.
	 * @param owningUser the owning user
	 */
	public void setOwningUser(User owningUser);

	/**
	 * @return the entries creation date
	 */
	public Date getCreationDate();

	/**
	 * Set the entries creation date.
	 * @param creationDate the creation date
	 */
	public void setCreationDate(Date creationDate);
}
