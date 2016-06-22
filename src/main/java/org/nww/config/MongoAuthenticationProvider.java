/**
 * 
 */
package org.nww.config;

import java.util.ArrayList;
import java.util.List;

import org.nww.modules.users.orm.User;
import org.nww.modules.users.orm.UserManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * @author mga
 *
 */
@Service
public class MongoAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	@Autowired
	private UserManager userMgr;
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	public UserManager getUserManager() {
		return userMgr;
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider#additionalAuthenticationChecks(org.springframework.security.core.userdetails.UserDetails, org.springframework.security.authentication.UsernamePasswordAuthenticationToken)
	 */
	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		if(!userDetails.getPassword().equals(authentication.getCredentials())) {
			log.debug("Login credentials do not match: " + userDetails.toString());
			throw new AuthenticationCredentialsNotFoundException("");
		}
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider#retrieveUser(java.lang.String, org.springframework.security.authentication.UsernamePasswordAuthenticationToken)
	 */
	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		UserDetails user = null;
		
		User mongoUser = getUserManager().findByUsername(username);
		if(null != mongoUser) {
			List<GrantedAuthority> roles = new ArrayList<>();
			mongoUser.getRoles().forEach(r -> roles.add(new SimpleGrantedAuthority(r)));
			
			user = new org.springframework.security.core.userdetails.User(mongoUser.getUsername(), mongoUser.getPassword(), roles);
		}
		else {
			log.debug("Could not find user with name '" + username + "'");
			throw new AuthenticationCredentialsNotFoundException("");
		}
		
		return user;
	}

}
