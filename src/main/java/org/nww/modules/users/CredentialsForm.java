/**
 * 
 */
package org.nww.modules.users;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.nww.core.data.form.AbstractPersistentObjectForm;

/**
 * @author mga
 *
 */
public class CredentialsForm extends AbstractPersistentObjectForm {
	@Email
	@NotNull
	private String email;
	@Length(max = 35)
	private String password;
	@Length(max = 35)
	private String passwordConfirmation;
	
	private List<String> roles = new ArrayList<>();
	
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * @return the passwordRepeat
	 */
	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}
	
	/**
	 * @param passwordConfirmation the passwordRepeat to set
	 */
	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}
	
	/**
	 * @return the roles
	 */
	public List<String> getRoles() {
		return roles;
	}

	
	/**
	 * @param roles the roles to set
	 */
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
	/**
	 * @param role the role to be looked up
	 * @return true if the role is asigned
	 */
	public boolean hasRole(String role) {
		return this.roles.contains(role);
	}
}
