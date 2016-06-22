/**
 * 
 */
package org.nww.modules.projects.orm;

import org.nww.core.utils.ApplicationContextHelper;
import org.nww.modules.users.orm.User;
import org.nww.modules.users.orm.UserManager;
import org.springframework.data.annotation.Transient;

/**
 * POJO to store information about a user and what his role was within a project.
 * @author mga
 */
public class ProjectParticipantData {
	private String userUUID;
	private String description;

	@Transient
	private User user;
	@Transient
	private UserManager userMgr = ApplicationContextHelper.getBeanByClass(UserManager.class);
	
	/**
	 * Default constructor.
	 */
	public ProjectParticipantData() {
		super();
	}
	
	/**
	 * Create a new {@link ProjectParticipantData} object using the passed data.
	 */
	public ProjectParticipantData(String userUUID, String description) {
		this.userUUID = userUUID;
		this.description = description;
	}
	
	/**
	 * @return the userUUID
	 */
	public String getUserUUID() {
		return userUUID;
	}
	
	/**
	 * @param userUUID the userUUID to set
	 */
	public void setUserUUID(String userUUID) {
		this.userUUID = userUUID;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		if(null == this.user) {
			this.user = userMgr.findOne(getUserUUID());
		}
		return user;
	}
	
	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.userUUID = user.getUUID();
		this.user = user;
	}
	
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		if(null == this.userUUID || null == this.description) {
			return super.hashCode();
		}
		return this.userUUID.hashCode() ^ this.description.hashCode();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		if(o instanceof ProjectParticipantData) {
			ProjectParticipantData other = (ProjectParticipantData) o;
			return other.getUser().equals(this.getUser()) && other.getDescription().equals(this.getDescription());
		}
		
		return false;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getUser().getDisplayName() + " - " + getDescription();
	}
}
