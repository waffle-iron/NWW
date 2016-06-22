/**
 * 
 */
package org.nww.modules.suppliers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.nww.core.data.form.FormDataMapper;
import org.nww.modules.profiles.orm.Profile;
import org.nww.modules.profiles.orm.ProfileManager;
import org.nww.modules.suppliers.orm.Supplier;
import org.nww.modules.suppliers.orm.SupplierManager;

/**
 * @author mga
 *
 */
public class UserSuppliersFormDataMapper implements FormDataMapper<UserSuppliersForm, Profile> {

	@Resource(name = "SupplierManager")
	private SupplierManager supplierMgr;
	
	@Resource(name = "ProfileManager")
	private ProfileManager profileMgr;
	
	/* (non-Javadoc)
	 * @see org.nww.core.data.form.FormDataMapper#mapToForm(org.nww.core.data.PersistentObject)
	 */
	@Override
	public UserSuppliersForm mapToForm(Profile p) {
		UserSuppliersForm f = new UserSuppliersForm();
		
		if(null != p) {
			f.setSupplierNames(supplierMgr.getSuppliersFromExtensibleObject(p).stream().map(s -> s.getName()).collect(Collectors.toList()));
		}
		
		return f;
	}

	/* (non-Javadoc)
	 * @see org.nww.core.data.form.FormDataMapper#mapToPersistentObject(org.nww.core.data.form.Form)
	 */
	@Override
	public Profile mapToPersistentObject(UserSuppliersForm f) {
		Profile p = mapToExistingPersistentObject(f, profileMgr.createNew());
			
		p.setUUID(null);
		
		return p;
	}

	/* (non-Javadoc)
	 * @see org.nww.core.data.form.FormDataMapper#mapToExistingPersistentObject(org.nww.core.data.form.Form, org.nww.core.data.PersistentObject)
	 */
	@Override
	public Profile mapToExistingPersistentObject(UserSuppliersForm f, Profile p) {
		List<Supplier> suppliers = new ArrayList<>();
		
		f.getSupplierNames().forEach(n ->suppliers.add(supplierMgr.findByName(n)));
		
		return supplierMgr.setSuppliersForExtensibleObject(p, suppliers);
	}
}
