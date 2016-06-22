/**
 * 
 */
package org.nww.modules.profiles;

import javax.annotation.Resource;

import org.nww.core.data.form.FormDataMapper;
import org.nww.modules.profiles.orm.Profile;
import org.nww.modules.profiles.orm.ProfileManager;

/**
 * @author mga
 *
 */
public class ProfileFormDataMapper implements FormDataMapper<ProfileForm, Profile> {

	@Resource(name = "ProfileManager")
	private ProfileManager profileMgr;
	
	@Override
	public ProfileForm mapToForm(Profile p) {
		ProfileForm f = new ProfileForm();
		
		f.setUUID(p.getUUID());
		f.setSurename(p.getSurename());
		f.setName(p.getName());
		f.setCompany(p.getCompany());
		
		f.setStreet1(p.getStreet1());
		f.setStreet2(p.getStreet2());
		f.setHouseNo(p.getHouseNo());
		f.setPostalCode(p.getPostalCode());
		f.setCity(p.getCity());
		
		f.setTelephone(p.getTelephone());
		f.setMobile(p.getMobile());
		f.setEmail(p.getEmail());
		f.setHomepage(p.getHomepage());
		
		// new fields after release 0.6
		f.setTitle(p.getTitle());
		f.setCompanyDescription(p.getCompanyDescription());
		f.setFax(p.getFax());
		
		// new fields after release 0.7
		f.setCompanyUserRole(p.getCompanyUserRole());
		
		return f;
	}

	@Override
	public Profile mapToPersistentObject(ProfileForm form) {
		Profile p = mapToExistingPersistentObject(form, profileMgr.createNew());
		
		// make sure a new UUID is created by the system
		p.setUUID(null);
		
		return p;
	}

	@Override
	public Profile mapToExistingPersistentObject(ProfileForm f, Profile p) {
		p.setUUID(f.getUUID());
		p.setSurename(f.getSurename());
		p.setName(f.getName());
		p.setCompany(f.getCompany());
		
		p.setStreet1(f.getStreet1());
		p.setStreet2(f.getStreet2());
		p.setHouseNo(f.getHouseNo());
		p.setPostalCode(f.getPostalCode());
		p.setCity(f.getCity());
		
		p.setTelephone(f.getTelephone());
		p.setMobile(f.getMobile());
		p.setEmail(f.getEmail());
		p.setHomepage(f.getHomepage());
		
		// new fields after release 0.6
		p.setTitle(f.getTitle());
		p.setCompanyDescription(f.getCompanyDescription());
		p.setFax(f.getFax());
		
		// new fields after release 0.7
		p.setCompanyUserRole(f.getCompanyUserRole());
		
		return p;
	}
}
