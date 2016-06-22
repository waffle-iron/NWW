/**
 * 
 */
package org.nww.modules.users.orm;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.nww.core.system.AbstractPersistentObjectManager;
import org.nww.core.system.OperationResult;
import org.nww.core.system.OperationResult.State;
import org.nww.modules.profiles.orm.Profile;
import org.nww.modules.profiles.orm.ProfileManager;
import org.nww.modules.users.queries.FindUserByProfileQuery;
import org.nww.modules.users.queries.FindUsersByProfilesQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.query.Query;

/**
 * @author mga
 *
 */
public class UserManagerImpl extends AbstractPersistentObjectManager<User, UserRepository<User>> implements UserManager {

	@Resource(name = "UserRepository")
	private UserRepository<User> userRepository;

	@Resource(name = "ProfileManager")
	private ProfileManager profileMgr;
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	/* (non-Javadoc)
	 * @see org.nww.core.system.PersistentObjectManager#getRepository()
	 */
	@Override
	public UserRepository<User> getRepository() {
		return this.userRepository;
	}

	/* (non-Javadoc)
	 * @see org.nww.core.system.PersistentObjectManager#setRepository(org.nww.core.data.PersistentObjectRepository)
	 */
	@Override
	public void setRepository(UserRepository<User> repository) {
		this.userRepository = repository;
	}

	/* (non-Javadoc)
	 * @see org.nww.core.system.PersistentObjectManager#createNew()
	 */
	@Override
	public User createNew() {
		return getRepository().createNew();
	}

	/* (non-Javadoc)
	 * @see org.nww.orm.users.UserManager#findByUsername(java.lang.String)
	 */
	@Override
	public User findByUsername(String username) {
		return getRepository().findByUsername(username);
	}

	/*
	 * (non-Javadoc)
	 * @see org.nww.modules.users.orm.UserManager#createNewUserProfileWithCheck(org.nww.modules.users.orm.User)
	 */
	@Override
	public Profile createNewUserProfileWithCheck(User u) {
		if(u.getProfile() != null) {
			return u.getProfile();
		}
		
		Profile p = profileMgr.createNew();
		OperationResult result = profileMgr.save(p); 
		if(result.getResultState() == State.SUCCESSFULL) {
			u.setProfile(p);
			if(save(u).getResultState() == State.SUCCESSFULL) {
				return p;
			}
		}
		
		log.error("Could not create new user profile!", result.getException());
		return null;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.nww.modules.users.orm.UserManager#prepareUserEmailAddressesForMassMails(org.nww.modules.users.orm.User)
	 */
	@Override
	public List<String> prepareUserEmailAddressesForMassMails(User currentUser) {
		List<? extends User> allUnfiltered = findAll();
		return allUnfiltered.stream()
				.filter(u -> !u.getUUID().equals(currentUser.getUUID()))
				.map(u -> u.getUsername())
				.collect(Collectors.toList());
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.nww.modules.users.orm.UserManager#findByProfileID(java.lang.String)
	 */
	@Override
	public User findByProfileID(String profileUUID) {
		Query q = new FindUserByProfileQuery(profileUUID);

		return getRepository().executeQuery(q);
	}

	/*
	 * (non-Javadoc)
	 * @see org.nww.modules.users.orm.UserManager#findByProfileID(java.util.List)
	 */
	@Override
	public List<? extends User> findByProfileID(List<String> profileUUIDs) {
		Query q = new FindUsersByProfilesQuery(profileUUIDs);
		return getRepository().executeListQuery(q);
	}
}
