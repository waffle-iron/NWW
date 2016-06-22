/**
 * 
 */
package org.nww.modules.suppliers;

import java.util.ArrayList;
import java.util.List;

import org.nww.core.data.form.AbstractPersistentObjectForm;
import org.nww.core.validation.ListSize;

/**
 * @author mga
 *
 */
public class UserSuppliersForm extends AbstractPersistentObjectForm {
	@ListSize(min = 2, max = 100, message = "Der Name der Lieferanten muss mindestens 2 und maximal 100 Zeichen lang sein!")
	private List<String> supplierNames = new ArrayList<>();
	
	/**
	 * @return the supplier IDs
	 */
	public List<String> getSupplierNames() {
		return supplierNames;
	}
	
	/**
	 * Set the supplier IDs.
	 * @param supplierIDs list of IDs
	 */
	public void setSupplierNames(List<String> supplierNames) {
		this.supplierNames = supplierNames;
	}
}
