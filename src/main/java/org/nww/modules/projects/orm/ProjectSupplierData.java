/**
 * 
 */
package org.nww.modules.projects.orm;

import org.nww.core.utils.ApplicationContextHelper;
import org.nww.modules.suppliers.orm.Supplier;
import org.nww.modules.suppliers.orm.SupplierManager;
import org.springframework.data.annotation.Transient;

/**
 * POJO to store information about a supplier and what his role was within a project.
 * @author mga
 */
public class ProjectSupplierData {
	private String supplierUUID;
	private String description;

	@Transient
	private Supplier supplier;
	@Transient
	private SupplierManager supplierMgr = ApplicationContextHelper.getBeanByClass(SupplierManager.class);
	
	/**
	 * Default constructor
	 */
	public ProjectSupplierData() {
		super();
	}
	
	/**
	 * Create a new {@link ProjectSupplierData} object using the passed data.
	 * @param supplierUUID the supplier UUID
	 * @param description the description
	 */
	public ProjectSupplierData(String supplierUUID, String description) {
		this.supplierUUID = supplierUUID;
		this.description = description;
	}
	
	/**
	 * @return the supplierUUID
	 */
	public String getSupplierUUID() {
		return supplierUUID;
	}
	
	/**
	 * @param supplierUUID the supplierUUID to set
	 */
	public void setSupplierUUID(String supplierUUID) {
		this.supplierUUID = supplierUUID;
	}
	
	/**
	 * @return the supplier
	 */
	public Supplier getSupplier() {
		if(null == this.supplier) {
			this.supplier = supplierMgr.findOne(this.supplierUUID);
		}
		return supplier;
	}
	
	/**
	 * @param supplier the supplier to set
	 */
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
		this.supplierUUID = supplier.getUUID();
	}
	
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		if(null == this.supplierUUID || null == this.description) {
			return super.hashCode();
		}
		
		return this.supplierUUID.hashCode() ^ this.description.hashCode();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		if(o instanceof ProjectSupplierData) {
			ProjectSupplierData other = (ProjectSupplierData) o;
			return other.getSupplier().equals(this.getSupplier()) && other.getDescription().equals(this.getDescription());
		}
		
		return false;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getSupplier().getName() + " - " + getDescription();
	}
}
