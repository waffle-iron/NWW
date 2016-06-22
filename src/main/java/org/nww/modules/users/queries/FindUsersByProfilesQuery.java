/**
 * 
 */
package org.nww.modules.users.queries;

import java.util.List;

import org.nww.modules.users.orm.User;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

/**
 * A query to find users by a list of profile UUIDs.
 * @author mga
 *
 */
public class FindUsersByProfilesQuery extends Query {
	/**
	 * Create a new query to find users by a list of profile UUIDs.
	 * @param profileUUIDs the list of profile UUIDs
	 */
	public FindUsersByProfilesQuery(List<String> profileUUIDs) {
		super(Criteria.where(User.FIELD_PROFILE_UUID).in(profileUUIDs));
	}
}
