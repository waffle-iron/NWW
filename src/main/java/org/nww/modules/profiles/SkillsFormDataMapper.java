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
public class SkillsFormDataMapper implements FormDataMapper<SkillsForm, Profile> {

	@Resource(name = "ProfileManager")
	private ProfileManager profileMgr;
	
	@Override
	public SkillsForm mapToForm(Profile p) {
		SkillsForm f = new SkillsForm();
		
		f.setSkills(p.getAbilities());
		f.setHighlightedSkills(p.getSpecials());
		
		return f;
	}

	@Override
	public Profile mapToPersistentObject(SkillsForm f) {
		Profile p = mapToExistingPersistentObject(f, profileMgr.createNew());
		
		// make sure a new UUID is created by the system
		p.setUUID(null);
		
		return p;
	}

	@Override
	public Profile mapToExistingPersistentObject(SkillsForm f, Profile p) {
		p.setAbilities(f.getSkills());
		p.setSpecials(f.getHighlightedSkills());
		
		return p;
	}

}
