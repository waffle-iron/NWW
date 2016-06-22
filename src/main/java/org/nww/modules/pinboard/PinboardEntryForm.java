/**
 * 
 */
package org.nww.modules.pinboard;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.nww.core.data.form.AbstractPersistentObjectForm;

/**
 * @author mga
 *
 */
public class PinboardEntryForm extends AbstractPersistentObjectForm {
	@NotNull
	private Integer type = 0;
	@NotNull
	private Integer status = 0;
	@Length(min = 5, max = 150)
	private String subject;
	private String description;
	@NotNull
	private String owningUserUUID;
	
	/**
	 * @return the type
	 */
	public Integer getType() {
		return type;
	}
	
	/**
	 * @param type the type to set
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	
	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}
	
	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}
	
	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
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
	
	/**
	 * @return the owningUserUUID
	 */
	public String getOwningUserUUID() {
		return owningUserUUID;
	}
	
	/**
	 * @param owningUserUUID the owningUserUUID to set
	 */
	public void setOwningUserUUID(String owningUserUUID) {
		this.owningUserUUID = owningUserUUID;
	}
}
