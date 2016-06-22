/**
 * 
 */
package org.nww.modules.pinboard.orm;

import java.util.Date;

import org.nww.core.data.AbstractExtensiblePersistentObject;
import org.nww.core.utils.ApplicationContextHelper;
import org.nww.modules.users.orm.User;
import org.nww.modules.users.orm.UserManager;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Basic implementation of the {@link PinboardEntry} interface.
 * 
 * @author mga
 */
@Document(collection = "pinboard_entries")
public class PinboardEntryImpl extends AbstractExtensiblePersistentObject implements PinboardEntry {

	private Integer type;
	private Integer status;
	private String subject;
	private String description;
	private String owningUserUUID;
	private Date creationDate = new Date();
	
	@Transient
	private UserManager userMgr = ApplicationContextHelper.getBeanByClass(UserManager.class);
	@Transient
	private User owningUser;
	
	@Override
	public Integer getType() {
		return this.type;
	}

	@Override
	public void setType(Integer type) {
		this.type = type;
	}

	@Override
	public Integer getStatus() {
		return this.status;
	}

	@Override
	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String getSubject() {
		return this.subject;
	}

	@Override
	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Override
	public String getDescription() {
		return this.description;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String getOwningUserUUID() {
		return this.owningUserUUID;
	}

	@Override
	public void setOwningUserUUID(String owningUserUUID) {
		this.owningUserUUID = owningUserUUID;
		this.owningUser = null;
	}

	@Override
	public User getOwningUser() {
		if(null == this.owningUser) {
			this.owningUser = userMgr.findOne(this.owningUserUUID);
		}
		
		return this.owningUser;
	}

	@Override
	public void setOwningUser(User owningUser) {
		this.owningUser = owningUser;
		this.owningUserUUID = owningUser.getUUID();
	}

	@Override
	public Date getCreationDate() {
		return this.creationDate;
	}

	@Override
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
}
