/**
 * 
 */
package org.nww.modules.suppliers.orm;

import org.nww.core.data.AbstractExtensiblePersistentObject;
import org.nww.core.utils.ApplicationContextHelper;
import org.nww.modules.files.orm.FileInformation;
import org.nww.modules.files.orm.FileManager;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.StringUtils;

/**
 * @author mga
 *
 */
@Document(collection = "suppliers")
public class SupplierImpl extends AbstractExtensiblePersistentObject implements Supplier {
	@Indexed(unique = true)
	private String name;
	private String description;
	private String logoUUID;
	private String homepage;
	private Integer approvalState;
	
	@Transient
	private FileInformation logo;

	@Transient
	private FileManager fileMgr = ApplicationContextHelper.getBeanByClass(FileManager.class);
	
	/* (non-Javadoc)
	 * @see org.nww.modules.suppliers.Supplier#getName()
	 */
	@Override
	public String getName() {
		return this.name;
	}

	/* (non-Javadoc)
	 * @see org.nww.modules.suppliers.Supplier#setName(java.lang.String)
	 */
	@Override
	public void setName(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see org.nww.modules.suppliers.Supplier#getDescription()
	 */
	@Override
	public String getDescription() {
		return this.description;
	}

	/* (non-Javadoc)
	 * @see org.nww.modules.suppliers.Supplier#setDescription(java.lang.String)
	 */
	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	/* (non-Javadoc)
	 * @see org.nww.modules.suppliers.Supplier#hasDescription()
	 */
	@Override
	public boolean hasDescription() {
		return !StringUtils.isEmpty(this.description);
	}

	/* (non-Javadoc)
	 * @see org.nww.modules.suppliers.Supplier#getLogoFileInformationUUID()
	 */
	@Override
	public String getLogoFileInformationUUID() {
		return this.logoUUID;
	}

	/* (non-Javadoc)
	 * @see org.nww.modules.suppliers.Supplier#setLogoFileInformationUUID(java.lang.String)
	 */
	@Override
	public void setLogoFileInformationUUID(String fiUUID) {
		this.logoUUID = fiUUID;
	}

	/* (non-Javadoc)
	 * @see org.nww.modules.suppliers.Supplier#getLogoFileInformation()
	 */
	@Override
	public FileInformation getLogoFileInformation() {
		if(null == this.logo && !StringUtils.isEmpty(this.logoUUID)) {
			logo = fileMgr.findOne(this.logoUUID);
		}
		return this.logo;
	}

	/* (non-Javadoc)
	 * @see org.nww.modules.suppliers.Supplier#setLogoFileInformation(org.nww.modules.files.orm.FileInformation)
	 */
	@Override
	public void setLogoFileInformation(FileInformation fi) {
		this.logoUUID = fi.getUUID();
		this.logo = fi;
	}

	/* (non-Javadoc)
	 * @see org.nww.modules.suppliers.Supplier#hasLogo()
	 */
	@Override
	public boolean hasLogo() {
		return !StringUtils.isEmpty(this.logoUUID);
	}

	/* (non-Javadoc)
	 * @see org.nww.modules.suppliers.Supplier#getHomepage()
	 */
	@Override
	public String getHomepage() {
		return this.homepage;
	}

	/* (non-Javadoc)
	 * @see org.nww.modules.suppliers.Supplier#setHomepage(java.lang.String)
	 */
	@Override
	public void setHomepage(String url) {
		this.homepage = url;
	}

	/* (non-Javadoc)
	 * @see org.nww.modules.suppliers.Supplier#hasHomepage()
	 */
	@Override
	public boolean hasHomepage() {
		return !StringUtils.isEmpty(this.homepage);
	}

	/* (non-Javadoc)
	 * @see org.nww.modules.suppliers.Supplier#getApprovalStatus()
	 */
	@Override
	public Integer getApprovalStatus() {
		return this.approvalState;
	}

	/* (non-Javadoc)
	 * @see org.nww.modules.suppliers.Supplier#setApprovalStatus(java.lang.Integer)
	 */
	@Override
	public void setApprovalStatus(Integer status) {
		this.approvalState = status;
	}

	/* (non-Javadoc)
	 * @see org.nww.modules.suppliers.Supplier#isApproved()
	 */
	@Override
	public boolean isApproved() {
		return Supplier.STATUS_APPROVED.equals(this.approvalState);
	}
}
