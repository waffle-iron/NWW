/**
 * 
 */
package org.nww.modules.profiles.orm;

import javax.annotation.Resource;

import org.nww.core.system.AbstractPersistentObjectManager;

/**
 * @author mga
 *
 */
public class ProfileManagerImpl extends AbstractPersistentObjectManager<Profile, ProfileRepository<Profile>> implements ProfileManager {

	@Resource(name = "ProfileRepository")
	private ProfileRepository<Profile> profileRepository;
	
	/* (non-Javadoc)
	 * @see org.nww.core.system.PersistentObjectManager#getRepository()
	 */
	@Override
	public ProfileRepository<Profile> getRepository() {
		return this.profileRepository;
	}

	/* (non-Javadoc)
	 * @see org.nww.core.system.PersistentObjectManager#setRepository(org.nww.core.data.PersistentObjectRepository)
	 */
	@Override
	public void setRepository(ProfileRepository<Profile> repository) {
		this.profileRepository = repository;
	}

	@Override
	public Profile createNew() {
		return getRepository().createNew();
	}
}
