/**
 * 
 */
package org.nww.modules.frontend;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * The data mapping class used for the contact form in the frontend.
 * @author mga
 *
 */
public class ContactForm {
	
	public ContactForm() {
		this.name = "";
		this.email = "";
		this.message = "";
	}
	
	@NotEmpty
	@Length(min = 1, max = 30)
	private String name;
	
	@NotEmpty
	@Email
	private String email;
	
	@NotEmpty
	@Length(min = 1, max = 2000)
	private String message;

	/**
	 * Get the name of the contact.
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Set the name of the contact.
	 * @param name the name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Get the email of the contact.
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Set the email of the contact.
	 * @param email the email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Get the text.
	 * @return the messsage
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * Set the message.
	 * @param message the message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
