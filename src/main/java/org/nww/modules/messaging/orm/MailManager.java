/**
 * 
 */
package org.nww.modules.messaging.orm;

import org.nww.core.system.PersistentObjectManager;
import org.nww.modules.messaging.MailForm;

/**
 * Defines methods to manage mail objects.
 * @author mga
 *
 */
public interface MailManager extends PersistentObjectManager<Mail, MailRepository<Mail>> {
	
	public static final String FOLDER_DRAFTS = "DRAFTS";
	public static final String FOLDER_INBOX = "INBOX";
	public static final String FOLDER_SENT = "SENT";
	public static final String FOLDER_TRASH = "TRASH";
	
	/**
	 * Send the mail  with the passed UUID.
	 * @param uuid the UUID of the mail to be sent
	 * @return true if the mail could be sent successfully
	 */
	public boolean send(String uuid);

	/**
	 * Send the mail with the passed UUID and override the mail content field.
	 * @param uuid the UUID of of them mail to be sent
	 * @param overrideContent the content to be sent instead of the original mail content
	 * @return true if the mail could be sent successfully
	 */
	public boolean send(String uuid, String overrideContent);
	
	/**
	 * Send the passed mail.
	 * @param m the mail to be sent
	 * @return true if the mail could be sent successfully
	 */
	boolean send(Mail m);
	
	/**
	 * Send the passed mail and override the mail content field.
	 * @param m the mail to be sent
	 * @param overrideContent the content to be sent instead of the original mail content
	 * @return true if the mail could be sent successfully
	 */
	boolean send(Mail m, String overrideContent);
	
	/**
	 * Save the passed mail form information as a draft.
	 * @param form the mail data form
	 * @return the saved draft mail
	 */
	public Mail saveDraft(MailForm form);

	/**
	 * Find all mails inside the passed folder inside the passed postbox.
	 * @param postbox the postbox to find the mails for
	 * @param folder the folder to find the mails for
	 * @return iterable of mails
	 */
	public Iterable<? extends Mail> findByFolder(String postbox, String folder);
	
	// TODO: introduce paging findByFolder here
}
