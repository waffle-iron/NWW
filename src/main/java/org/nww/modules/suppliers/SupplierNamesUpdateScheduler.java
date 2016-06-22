/**
 * 
 */
package org.nww.modules.suppliers;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.nww.modules.suppliers.orm.Supplier;
import org.nww.modules.suppliers.orm.SupplierManager;

//TODO: remove this class with next release after 0.8.1


/**
 *
 * @author mga
 */
public class SupplierNamesUpdateScheduler {
	
	@Resource(name = "SupplierManager")
	private SupplierManager supplierMgr;
	
	@PostConstruct
	public void updateNames() {
		List<? extends Supplier> suppliers = supplierMgr.findAll();
		
		for (Supplier s : suppliers) {
			s.setName(s.getName().trim());
			supplierMgr.save(s);
		}
	}
}
