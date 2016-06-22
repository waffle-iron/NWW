/**
 * 
 */
package org.nww.modules.users.orm;

import java.util.List;

import org.nww.core.system.PersistentObjectManager;
import org.nww.modules.profiles.orm.Profile;

/**
 * @author mga
 *
 */
public interface UserManager extends PersistentObjectManager<User, UserRepository<User>> {
	/**
	 * Find a user by its unique user name.
	 * @param username the username
	 * @return the user object or null if none is found
	 */
	public User findByUsername(String username);

	/**
	 * Creates and saves a new profile for the passed user
	 * if he does not have one.
	 * @param u the user
	 * @return a new saved profile or the already existing profile.
	 */
	public Profile createNewUserProfileWithCheck(User u);
	
	/**
	 * Create a list of email addresses of all users except the passed current user.
	 * @param currentUser the user whose email not to include
	 * @return list of email addresses
	 */
	public List<String> prepareUserEmailAddressesForMassMails(User currentUser);
	
	/**
	 * Find a user by its profile UUID.
	 * @param profileUUID the profile UUID
	 * @return the user object or null if none is found
	 */
	public User findByProfileID(String profileUUID);
	
	/**
	 * Find a list of users for the passed list of profile UUIDs.
	 * @param profileUUIDs the list of profile UUIDs
	 * @return list of users, will ignore not found users
	 */
	public List<? extends User> findByProfileID(List<String> profileUUIDs);
}
