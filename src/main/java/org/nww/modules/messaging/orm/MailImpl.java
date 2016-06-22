package org.nww.modules.messaging.orm;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.nww.core.data.AbstractPersistentObject;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.StringUtils;

@Document(collection = "mails")
public class MailImpl extends AbstractPersistentObject implements Mail {
	private String content;
	private List<String> recipients = new ArrayList<>();
	private String subject;
	private Date sendDate;
	
	/*
	 * (non-Javadoc)
	 * @see org.nww.modules.notification.Notification#getContent()
	 */
	@Override
	public String getContent() {
		return this.content;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.nww.modules.notification.Notification#setContent(java.lang.String)
	 */
	@Override
	public void setContent(String content) {
		this.content = content;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.nww.modules.messaging.orm.Mail#hasRecipients()
	 */
	@Override
	public boolean hasRecipients() {
		return this.recipients.size() > 0;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.nww.modules.notification.Notification#getRecipients()
	 */
	@Override
	public List<String> getRecipients() {
		return this.recipients;
	}

	/*
	 * (non-Javadoc)
	 * @see org.nww.modules.notification.Notification#setRecipients(java.util.List)
	 */
	@Override
	public void setRecipients(List<String> recipients) {
		this.recipients = recipients;
	}

	/*
	 * (non-Javadoc)
	 * @see org.nww.modules.messaging.orm.Mail#hasSubject()
	 */
	@Override
	public boolean hasSubject() {
		return !StringUtils.isEmpty(this.subject);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.nww.modules.notification.Notification#getSubject()
	 */
	@Override
	public String getSubject() {
		return this.subject;
	}

	/*
	 * (non-Javadoc)
	 * @see org.nww.modules.notification.Notification#setSubject(java.lang.String)
	 */
	@Override
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/*
	 * (non-Javadoc)
	 * @see org.nww.modules.notification.Notification#getSendDate()
	 */
	@Override
	public Date getSendDate() {
		return this.sendDate;
	}

	/*
	 * (non-Javadoc)
	 * @see org.nww.modules.notification.Notification#setSendDate(java.util.Date)
	 */
	@Override
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.nww.core.data.AbstractPersistentObject#toString()
	 */
	@Override
	public String toString() {
		return "UUID: " + getUUID() + " | Subject: " + getSubject() + " | MailTo: " + getRecipients();
	}
}
