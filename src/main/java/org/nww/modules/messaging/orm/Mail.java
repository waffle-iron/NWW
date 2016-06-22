/**
 * 
 */
package org.nww.modules.messaging.orm;

import java.util.Date;
import java.util.List;

import org.nww.core.data.PersistentObject;

/**
 * @author mga
 *
 */
public interface Mail extends PersistentObject {
	/**
	 * @return the content
	 */
	public String getContent();
	
	/**
	 * Set the content.
	 * @param content the content
	 */
	public void setContent(String content);
	
	/**
	 * @return true if the email has recipients
	 */
	public boolean hasRecipients();
	
	/**
	 * @return list of recipient email addresses
	 */
	public List<String> getRecipients();
	
	/**
	 * Set the recipient email addresses.
	 * @param recipients list of email addresses
	 */
	public void setRecipients(List<String> recipients);
	
	/**
	 * @return true if the subject is not null or empty
	 */
	public boolean hasSubject();
	
	/**
	 * @return the email subject
	 */
	public String getSubject();
	
	/**
	 * Set the email subject
	 * @param subject the subject
	 */
	public void setSubject(String subject);
	
	/**
	 * @return the date the newsletter was send
	 */
	public Date getSendDate();

	/**
	 * Set the date the newsletter was send.
	 * @param sendDate the send date
	 */
	public void setSendDate(Date sendDate);
	
}
