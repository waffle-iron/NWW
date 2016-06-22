/**
 * 
 */
package org.nww.modules.users.queries;

import org.nww.modules.profiles.orm.Profile;
import org.nww.modules.users.orm.User;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

/**
 * A query to find a user by its profile UUID.
 * To be used by the user collection managing repository or manager.
 * @author mga
 *
 */
public class FindUserByProfileQuery extends Query {
	/**
	 * Create a new user query using the profile UUID
	 * @param profileUUID
	 */
	public FindUserByProfileQuery(String profileUUID) {
		super(Criteria.where(User.FIELD_PROFILE_UUID).is(profileUUID));
	}
	
	/**
	 * Create a new user query using the profile.
	 * @param p the profile to find the user for
	 */
	public FindUserByProfileQuery(Profile p) {
		super(Criteria.where(User.FIELD_PROFILE_UUID).is(p.getUUID()));
	}
}
