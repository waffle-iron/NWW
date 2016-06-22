/**
 * 
 */
package org.nww.modules.profiles.orm;

import java.util.ArrayList;
import java.util.List;

import org.nww.core.data.AbstractExtensiblePersistentObject;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author mga
 *
 */
@Document(collection = "profiles")
public class ProfileImpl extends AbstractExtensiblePersistentObject implements Profile {
	private String title;
	private String companyDescription; 
	private String companyUserRole;
	private String fax;
	private String surename;
	private String name;
	private String company;
	private String street1;
	private String street2;
	private String houseNo;
	private String postalCode;
	private String city;
	private String telephone;
	private String mobile;
	private String email;
	private String homepage;
	private List<String> abilities = new ArrayList<>();
	private List<String> specials = new ArrayList<>();
	private List<String> hobbies = new ArrayList<>();

	/*
	 * (non-Javadoc)
	 * @see org.nww.modules.profiles.orm.Profile#getTitle()
	 */
	@Override
	public String getTitle() {
		return this.title;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.nww.modules.profiles.orm.Profile#setTitle(java.lang.String)
	 */
	@Override
	public void setTitle(String title) {
		this.title = title;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.nww.modules.profiles.orm.Profile#getCompanyUserRole()
	 */
	@Override
	public String getCompanyUserRole() {
		return this.companyUserRole;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.nww.modules.profiles.orm.Profile#setCompanyUserRole(java.lang.String)
	 */
	@Override
	public void setCompanyUserRole(String role) {
		this.companyUserRole = role;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.nww.modules.profiles.orm.Profile#getCompanyDescription()
	 */
	@Override
	public String getCompanyDescription() {
		return this.companyDescription;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.nww.modules.profiles.orm.Profile#setCompanyDescription(java.lang.String)
	 */
	@Override
	public void setCompanyDescription(String description) {
		this.companyDescription = description;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.nww.modules.profiles.orm.Profile#getFax()
	 */
	@Override
	public String getFax() {
		return this.fax;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.nww.modules.profiles.orm.Profile#setFax(java.lang.String)
	 */
	@Override
	public void setFax(String fax) {
		this.fax = fax;
	}
	
	/* (non-Javadoc)
	 * @see org.nww.dao.users.User#getSurename()
	 */
	@Override
	public String getSurename() {
		return surename;
	}
	
	/* (non-Javadoc)
	 * @see org.nww.dao.users.User#setSurename(java.lang.String)
	 */
	@Override
	public void setSurename(String surename) {
		this.surename = surename;
	}
	
	/* (non-Javadoc)
	 * @see org.nww.dao.users.User#getName()
	 */
	@Override
	public String getName() {
		return name;
	}
	
	/* (non-Javadoc)
	 * @see org.nww.dao.users.User#setName(java.lang.String)
	 */
	@Override
	public void setName(String name) {
		this.name = name;
	}
	
	/* (non-Javadoc)
	 * @see org.nww.dao.users.User#getOrganization()
	 */
	@Override
	public String getCompany() {
		return company;
	}
	
	/* (non-Javadoc)
	 * @see org.nww.dao.users.User#setOrganization(java.lang.String)
	 */
	@Override
	public void setCompany(String organization) {
		this.company = organization;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.nww.orm.profiles.Profile#getPostalCode()
	 */
	@Override
	public String getPostalCode() {
		return this.postalCode;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.nww.orm.profiles.Profile#setPostalCode(java.lang.String)
	 */
	@Override
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.nww.orm.profiles.Profile#getCity()
	 */
	@Override
	public String getCity() {
		return this.city;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.nww.orm.profiles.Profile#setCity(java.lang.String)
	 */
	@Override
	public void setCity(String city) {
		this.city = city;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.nww.orm.profiles.Profile#getHouseNo()
	 */
	@Override
	public String getHouseNo() {
		return this.houseNo;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.nww.orm.profiles.Profile#setHouseNo(java.lang.String)
	 */
	@Override
	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.nww.orm.profiles.Profile#getStreet1()
	 */
	@Override
	public String getStreet1() {
		return this.street1;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.nww.orm.profiles.Profile#setStreet1(java.lang.String)
	 */
	@Override
	public void setStreet1(String street1) {
		this.street1 = street1;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.nww.orm.profiles.Profile#getStreet2()
	 */
	@Override
	public String getStreet2() {
		return this.street2;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.nww.orm.profiles.Profile#setStreet2(java.lang.String)
	 */
	@Override
	public void setStreet2(String street2) {
		this.street2 = street2;
	}
	
	/* (non-Javadoc)
	 * @see org.nww.dao.users.User#getTelephone()
	 */
	@Override
	public String getTelephone() {
		return telephone;
	}
	
	/* (non-Javadoc)
	 * @see org.nww.dao.users.User#setTelephone(java.lang.String)
	 */
	@Override
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	/* (non-Javadoc)
	 * @see org.nww.dao.users.User#getMobile()
	 */
	@Override
	public String getMobile() {
		return mobile;
	}
	
	/* (non-Javadoc)
	 * @see org.nww.dao.users.User#setMobile(java.lang.String)
	 */
	@Override
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	/* (non-Javadoc)
	 * @see org.nww.dao.users.User#getEmail()
	 */
	@Override
	public String getEmail() {
		return email;
	}
	
	/* (non-Javadoc)
	 * @see org.nww.dao.users.User#setEmail(java.lang.String)
	 */
	@Override
	public void setEmail(String email) {
		this.email = email;
	}
	
	/* (non-Javadoc)
	 * @see org.nww.dao.users.User#getHomepage()
	 */
	@Override
	public String getHomepage() {
		if(null == homepage) {
			return null;
		}
		
		String formatted = homepage;
		if(!formatted.startsWith("https://") && !formatted.startsWith("http://")) {
			formatted = "http://" + formatted;
		}
		return formatted;
	}
	
	/* (non-Javadoc)
	 * @see org.nww.dao.users.User#setHomepage(java.lang.String)
	 */
	@Override
	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.nww.modules.profiles.orm.Profile#getAbilities()
	 */
	@Override
	public List<String> getAbilities() {
		return this.abilities;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.nww.modules.profiles.orm.Profile#setAbilities(java.util.List)
	 */
	@Override
	public void setAbilities(List<String> abilitites) {
		this.abilities = abilitites;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.nww.modules.profiles.orm.Profile#getSpecials()
	 */
	@Override
	public List<String> getSpecials() {
		return this.specials;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.nww.modules.profiles.orm.Profile#setSpecials(java.util.List)
	 */
	@Override
	public void setSpecials(List<String> specials) {
		this.specials = specials;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.nww.modules.profiles.orm.Profile#getHobbies()
	 */
	@Override
	public List<String> getHobbies() {
		return this.hobbies;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.nww.modules.profiles.orm.Profile#setHobbies(java.util.List)
	 */
	@Override
	public void setHobbies(List<String> hobbies) {
		this.hobbies = hobbies;
	}
}
