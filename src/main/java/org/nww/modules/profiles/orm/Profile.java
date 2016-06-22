package org.nww.modules.profiles.orm;

import java.util.List;

import org.nww.core.data.ExtensiblePersistentObject;

/**
 * Defines methods for user objects.
 * @author mga
 *
 */
public interface Profile extends ExtensiblePersistentObject {

	/**
	 * @return the user title
	 */
	public String getTitle();
	
	/**
	 * Set the users title.
	 * @param title the title
	 */
	public void setTitle(String title);
	
	/**
	 * @return the company description
	 */
	public String getCompanyDescription();
	
	/**
	 * Set the company description.
	 * @param description the description
	 */
	public void setCompanyDescription(String description);
	
	/**
	 * @return the users company role
	 */
	public String getCompanyUserRole();
	
	/**
	 * Set the users company role.
	 * @param role the company role
	 */
	public void setCompanyUserRole(String role);
	
	/**
	 * @return the users fax number
	 */
	public String getFax();

	/**
	 * Set the users fax number.
	 * @param fax the fax number
	 */
	public void setFax(String fax);
	
	/**
	 * @return the surename
	 */
	public String getSurename();

	/**
	 * Set the users surename.
	 * @param surename the surename
	 */
	public void setSurename(String surename);

	/**
	 * Get the users name.
	 * @return the name
	 */
	public String getName();

	/**
	 * Set the users name.
	 * @param name the name
	 */
	public void setName(String name);

	/**
	 * @return the organization name
	 */
	public String getCompany();

	/**
	 * Set the users organization name.
	 * @param company the company name
	 */
	public void setCompany(String company);

	/**
	 * @return the street
	 */
	public String getStreet1();
	
	/**
	 * Set the street
	 * @param street1 the street
	 */
	public void setStreet1(String street1);
	
	/**
	 * @return additional street information
	 */
	public String getStreet2();
	
	/**
	 * Set additional street information.
	 * @param street2 additional street information
	 */
	public void setStreet2(String street2);
	
	/**
	 * @return house number
	 */
	public String getHouseNo();
	
	/**
	 * Set the house number.
	 * @param houseNo the house no
	 */
	public void setHouseNo(String houseNo);
	
	/**
	 * @return the postal code
	 */
	public String getPostalCode();
	
	/**
	 * Set the postal code
	 * @param postalCode the postal code
	 */
	public void setPostalCode(String postalCode);
	
	/**
	 * @return the city
	 */
	public String getCity();
	
	/**
	 * Set the city.
	 * @param city the city
	 */
	public void setCity(String city);

	/**
	 * @return the telephone number
	 */
	public String getTelephone();

	/**
	 * Set the users telephone number.
	 * @param telephone the telephone number
	 */
	public void setTelephone(String telephone);

	/**
	 * @return the mobile number
	 */
	public String getMobile();

	/**
	 * Set the users mobile number.
	 * @param mobile the mobile number
	 */
	public void setMobile(String mobile);

	/**
	 * @return the email
	 */
	public String getEmail();

	/**
	 * Set the users email.
	 * @param email the email
	 */
	public void setEmail(String email);

	/**
	 * @return the homepage
	 */
	public String getHomepage();

	/**
	 * Set the users homepage.
	 * @param homepage
	 */
	public void setHomepage(String homepage);
	
	/**
	 * @return the abilities
	 */
	public List<String> getAbilities();
	
	/**
	 * Set the abilities.
	 * @param abilitites list of abilities
	 */
	public void setAbilities(List<String> abilitites);
	
	/**
	 * @return the special abilities
	 */
	public List<String> getSpecials();
	
	/**
	 * Set the special abilities.
	 * @param specials list of special abilities
	 */
	public void setSpecials(List<String> specials);
	
	/**
	 * @return the hobbies
	 */
	public List<String> getHobbies();
	
	/**
	 * Set the hobbies.
	 * @param hobbies list of hobbies
	 */
	public void setHobbies(List<String> hobbies);
}