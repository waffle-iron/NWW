/**
 * 
 */
package org.nww.modules.users;

import javax.annotation.Resource;

import org.nww.core.data.form.FormDataMapper;
import org.nww.modules.users.orm.User;
import org.nww.modules.users.orm.UserManager;

/**
 * @author mga
 *
 */
public class CredentialsFormDataMapper implements FormDataMapper<CredentialsForm, User> {

	@Resource(name = "UserManager")
	private UserManager userMgr;
	
	/* (non-Javadoc)
	 * @see org.nww.core.data.form.FormDataMapper#mapToForm(org.nww.core.data.PersistentObject)
	 */
	@Override
	public CredentialsForm mapToForm(User u) {
		CredentialsForm f = new CredentialsForm();
		f.setEmail(u.getUsername());
		f.setRoles(u.getRoles());
		return f;
	}

	/* (non-Javadoc)
	 * @see org.nww.core.data.form.FormDataMapper#mapToPersistentObject(org.nww.core.data.form.Form)
	 */
	@Override
	public User mapToPersistentObject(CredentialsForm f) {
		return mapToExistingPersistentObject(f, userMgr.createNew());
	}

	/* (non-Javadoc)
	 * @see org.nww.core.data.form.FormDataMapper#mapToExistingPersistentObject(org.nww.core.data.form.Form, org.nww.core.data.PersistentObject)
	 */
	@Override
	public User mapToExistingPersistentObject(CredentialsForm f, User u) {
		u.setUsername(f.getEmail());
		u.setPassword(f.getPassword());
		u.setRoles(f.getRoles());
		
		return u;
	}

}
