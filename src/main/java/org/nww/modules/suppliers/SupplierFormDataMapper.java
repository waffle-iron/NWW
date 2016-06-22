/**
 * 
 */
package org.nww.modules.suppliers;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.nww.core.data.form.FormDataMapper;
import org.nww.modules.suppliers.orm.Supplier;
import org.nww.modules.suppliers.orm.SupplierManager;

/**
 * @author mga
 *
 */
public class SupplierFormDataMapper implements FormDataMapper<SupplierForm, Supplier> {

	@Resource(name = "SupplierManager")
	private SupplierManager supplierMgr;
	
	@Override
	public SupplierForm mapToForm(Supplier s) {
		SupplierForm f = new SupplierForm();
		
		f.setUUID(s.getUUID());
		f.setStatus(s.getApprovalStatus());
		f.setName(s.getName());
		f.setDescription(s.getDescription());
		f.setUrl(s.getHomepage());
		
		return f;
	}

	@Override
	public Supplier mapToPersistentObject(SupplierForm f) {
		Supplier s = mapToExistingPersistentObject(f, supplierMgr.createNew());
		
		// make sure UUID is null to force auto creation during save at database
		s.setUUID(null);
		
		return s;
	}

	@Override
	public Supplier mapToExistingPersistentObject(SupplierForm f, Supplier s) {
		s.setUUID(f.getUUID());
		s.setApprovalStatus(f.getStatus());
		s.setName(StringUtils.trim(f.getName()));
		s.setDescription(f.getDescription());
		s.setHomepage(f.getUrl());
		
		return s;
	}

}
