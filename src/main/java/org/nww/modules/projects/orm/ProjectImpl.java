/**
 * 
 */
package org.nww.modules.projects.orm;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.nww.core.data.AbstractExtensiblePersistentObject;
import org.nww.core.utils.ApplicationContextHelper;
import org.nww.modules.files.orm.FileInformation;
import org.nww.modules.files.orm.FileManager;
import org.nww.modules.suppliers.orm.Supplier;
import org.nww.modules.users.orm.User;
import org.nww.modules.users.orm.UserManager;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * MongoDB based implementation of the {@link Project} POJO interface.
 * @author mga
 */
@Document(collection = "projects")
public class ProjectImpl extends AbstractExtensiblePersistentObject implements Project {
	@Indexed
	private String name;
	private String description;
	@Indexed
	private String ownerUUID;
	private Date start;
	private Date end;
	private String location;
	private String customer;
	private List<ProjectParticipantData> participants = new ArrayList<>();
	private List<ProjectSupplierData> suppliers = new ArrayList<>();
	private List<ProjectFileData> gallery = new ArrayList<>();

	@Transient
	private User owner;
	@Transient
	private List<? extends FileInformation> galleryFiles;
	@Transient
	private UserManager userMgr = ApplicationContextHelper.getBeanByClass(UserManager.class);
	@Transient
	private FileManager fileMgr = ApplicationContextHelper.getBeanByClass(FileManager.class);
	
	/* (non-Javadoc)
	 * @see org.nww.modules.projects.Project#getName()
	 */
	@Override
	public String getName() {
		return this.name;
	}

	/* (non-Javadoc)
	 * @see org.nww.modules.projects.Project#setName(java.lang.String)
	 */
	@Override
	public void setName(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see org.nww.modules.projects.Project#getDescription()
	 */
	@Override
	public String getDescription() {
		return this.description;
	}

	/* (non-Javadoc)
	 * @see org.nww.modules.projects.Project#setDescription(java.lang.String)
	 */
	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	/* (non-Javadoc)
	 * @see org.nww.modules.projects.Project#getOwnerUUID()
	 */
	@Override
	public String getOwnerUUID() {
		return this.ownerUUID;
	}

	/* (non-Javadoc)
	 * @see org.nww.modules.projects.Project#setOwnerUUID(java.lang.String)
	 */
	@Override
	public void setOwnerUUID(String uuid) {
		this.ownerUUID = uuid;
		this.owner = null;
	}

	/* (non-Javadoc)
	 * @see org.nww.modules.projects.Project#getOwner()
	 */
	@Override
	public User getOwner() {
		if(this.owner == null) {
			this.owner = userMgr.findOne(getOwnerUUID());
		}
		return this.owner;
	}

	/* (non-Javadoc)
	 * @see org.nww.modules.projects.Project#setOwner(org.nww.modules.users.orm.User)
	 */
	@Override
	public void setOwner(User u) {
		this.ownerUUID = u.getUUID();
		this.owner = u;
	}

	/* (non-Javadoc)
	 * @see org.nww.modules.projects.Project#getProjectStart()
	 */
	@Override
	public Date getProjectStart() {
		return this.start;
	}

	/* (non-Javadoc)
	 * @see org.nww.modules.projects.Project#setProjectStart(java.util.Date)
	 */
	@Override
	public void setProjectStart(Date start) {
		this.start = start;
	}

	/* (non-Javadoc)
	 * @see org.nww.modules.projects.Project#getProjectEnd()
	 */
	@Override
	public Date getProjectEnd() {
		return this.end;
	}

	/* (non-Javadoc)
	 * @see org.nww.modules.projects.Project#setProjectEnd(java.util.Date)
	 */
	@Override
	public void setProjectEnd(Date end) {
		this.end = end;
	}

	/* (non-Javadoc)
	 * @see org.nww.modules.projects.Project#getProjectLocation()
	 */
	@Override
	public String getProjectLocation() {
		return this.location;
	}

	/* (non-Javadoc)
	 * @see org.nww.modules.projects.Project#setProjectLocation(java.lang.String)
	 */
	@Override
	public void setProjectLocation(String location) {
		this.location = location;
	}

	/* (non-Javadoc)
	 * @see org.nww.modules.projects.Project#getProjectCustomer()
	 */
	@Override
	public String getProjectCustomer() {
		return this.customer;
	}

	/* (non-Javadoc)
	 * @see org.nww.modules.projects.Project#setProjectCustomer(java.lang.String)
	 */
	@Override
	public void setProjectCustomer(String customer) {
		this.customer = customer;
	}

	/* (non-Javadoc)
	 * @see org.nww.modules.projects.Project#getSuppliers()
	 */
	@Override
	public List<ProjectSupplierData> getSuppliers() {
		return this.suppliers;
	}

	/* (non-Javadoc)
	 * @see org.nww.modules.projects.Project#setSuppliers(java.util.List)
	 */
	@Override
	public void setSuppliers(List<ProjectSupplierData> suppliers) {
		this.suppliers = suppliers;
	}

	/* (non-Javadoc)
	 * @see org.nww.modules.projects.Project#addSupplier(org.nww.modules.projects.ProjectSupplierData)
	 */
	@Override
	public void addSupplier(ProjectSupplierData supplier) {
		this.suppliers.add(supplier);
	}

	/* (non-Javadoc)
	 * @see org.nww.modules.projects.Project#removeSupplier(org.nww.modules.projects.ProjectSupplierData)
	 */
	@Override
	public void removeSupplier(Supplier s) {
		ProjectSupplierData psd = null;
		
		for (ProjectSupplierData data : getSuppliers()) {
			if(data.getSupplierUUID().equals(s.getUUID())) {
				psd = data;
				break;
			}
		}
		
		if(null != psd) {
			this.suppliers.remove(psd);
		}
	}

	/* (non-Javadoc)
	 * @see org.nww.modules.projects.Project#getGallery()
	 */
	@Override
	public List<ProjectFileData> getGallery() {
		return this.gallery;
	}

	/* (non-Javadoc)
	 * @see org.nww.modules.projects.Project#setGallery(java.util.List)
	 */
	@Override
	public void setGallery(List<ProjectFileData> files) {
		this.gallery = files;
	}

	/* (non-Javadoc)
	 * @see org.nww.modules.projects.Project#addGalleryFile(org.nww.modules.projects.ProjectFileData)
	 */
	@Override
	public void addGalleryFile(ProjectFileData file) {
		this.gallery.add(file);
	}

	/* (non-Javadoc)
	 * @see org.nww.modules.projects.Project#removeGalleryFile(org.nww.modules.projects.ProjectFileData)
	 */
	@Override
	public void removeGalleryFile(FileInformation file) {
		ProjectFileData pfd = null;
		
		for (ProjectFileData data : getGallery()) {
			if(data.getFileInformationUUID().equals(file.getUUID())) {
				pfd = data;
				break;
			}
		}
		
		if(null != pfd) {
			this.gallery.remove(pfd);
		}
	}

	/* (non-Javadoc)
	 * @see org.nww.modules.projects.orm.Project#getParticipants()
	 */
	@Override
	public List<ProjectParticipantData> getParticipants() {
		return this.participants;
	}

	/* (non-Javadoc)
	 * @see org.nww.modules.projects.orm.Project#setParticipants(java.util.List)
	 */
	@Override
	public void setParticipants(List<ProjectParticipantData> participants) {
		this.participants = participants;
	}

	/* (non-Javadoc)
	 * @see org.nww.modules.projects.orm.Project#addParticipant(org.nww.modules.projects.orm.ProjectParticipantData)
	 */
	@Override
	public void addParticipant(ProjectParticipantData participantData) {
		this.participants.add(participantData);
	}

	/* (non-Javadoc)
	 * @see org.nww.modules.projects.orm.Project#removeParticipant(org.nww.modules.users.orm.User)
	 */
	@Override
	public void removeParticipant(User u) {
		ProjectParticipantData ppd = null;
		
		for (ProjectParticipantData data : getParticipants()) {
			if(data.getUserUUID().equals(u.getUUID())) {
				ppd = data;
				break;
			}
		}
		
		if(null != ppd) {
			this.participants.remove(ppd);
		}
	}
}
