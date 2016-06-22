/**
 * 
 */
package org.nww.modules.messaging;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.nww.core.data.form.Form;

/**
 * @author mga
 *
 */
public class MailForm implements Form {
	private String UUID;
	private String content;
	@NotNull
	@Length(min = 1)
	private String recipients;
	@NotNull
	@Length(max = 150)
	private String subject;
	
	/**
	 * @return get the UUID
	 */
	public String getUUID() {
		return UUID;
	}
	
	/**
	 * @param uUID set the UUID
	 */
	public void setUUID(String uUID) {
		UUID = uUID;
	}
	
	/**
	 * @return the content
	 */
	public String getContent() {
		return this.content;
	}
	
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	/**
	 * @return the recipients
	 */
	public String getRecipients() {
		return recipients;
	}
	
	/**
	 * @param recipients the recipients to set
	 */
	public void setRecipients(String recipients) {
		this.recipients = recipients;
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
}
