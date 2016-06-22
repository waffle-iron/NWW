package org.nww.services.web;

import java.util.Map;

import org.springframework.mail.MailSender;
import org.thymeleaf.context.Context;

/**
 * Defines methods for a email service.
 * @author mga
 *
 */
public interface EmailService {
	/**
	 * Get the MailSender object responsible for handling the mail transfer.
	 * @return the mail sender object
	 */
	public MailSender getMailSender();

	/**
	 * Set the MailSender object responsible for handling the mail transfer.
	 * @param mailSender the mail sender object
	 */
	public void setMailSender(MailSender mailSender);

	/**
	 * Sends a mail with the passed parameters.
	 * @param to the recipients of the mail
	 * @param from the author of the mail
	 * @param subject the subject of the mail
	 * @param message the mail message
	 * @return true if the mail was successfully send
	 */
	public boolean sendMail(String[] to, String from, String subject, String message);
	
	/**
	 * Sends a mail with the passed template to be rendered as the mail body.
	 * @param to the recipients
	 * @param from the author
	 * @param subject the subject
	 * @param template the template path to be rendered
	 * @param c the render context object
	 * @return true if the mail was successfully send
	 */
	public boolean sendMail(String[] to, String from, String subject, String template, Context c);
	
	/**
	 * Sends a mail with the passed template to be rendered as the mail body.
	 * @param to the recipients
	 * @param from the author
	 * @param subject the subject
	 * @param template the template path to be rendered
	 * @param attributes the context attributes for rendering process
	 * @return true if the mail was successfully send
	 */
	public boolean sendMail(String[] to, String from, String subject, String template, Map<String, Object> attributes);

}