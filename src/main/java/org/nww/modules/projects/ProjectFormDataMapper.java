/**
 * 
 */
package org.nww.modules.projects;

import javax.annotation.Resource;

import org.nww.core.data.form.FormDataMapper;
import org.nww.modules.projects.orm.Project;
import org.nww.modules.projects.orm.ProjectManager;

/**
 * The form data mapping object for project POJOs.
 * @author mga
 */
public class ProjectFormDataMapper implements FormDataMapper<ProjectForm, Project> {

	@Resource(name = "ProjectManager")
	private ProjectManager projectMgr;
	
	/* (non-Javadoc)
	 * @see org.nww.core.data.form.FormDataMapper#mapToForm(org.nww.core.data.PersistentObject)
	 */
	@Override
	public ProjectForm mapToForm(Project p) {
		ProjectForm f = new ProjectForm();
		
		f.setName(p.getName());
		f.setDescription(p.getDescription());
		f.setOwnerUUID(p.getOwnerUUID());
		f.setStart(p.getProjectStart());
		f.setEnd(p.getProjectEnd());
		f.setCustomer(p.getProjectCustomer());
		f.setLocation(p.getProjectLocation());
		f.setParticipants(p.getParticipants());
		f.setSuppliers(p.getSuppliers());
		f.setGallery(p.getGallery());
		
		return f;
	}

	/* (non-Javadoc)
	 * @see org.nww.core.data.form.FormDataMapper#mapToPersistentObject(org.nww.core.data.form.Form)
	 */
	@Override
	public Project mapToPersistentObject(ProjectForm form) {
		Project p = projectMgr.createNew();
		
		return mapToExistingPersistentObject(form, p);
	}

	/* (non-Javadoc)
	 * @see org.nww.core.data.form.FormDataMapper#mapToExistingPersistentObject(org.nww.core.data.form.Form, org.nww.core.data.PersistentObject)
	 */
	@Override
	public Project mapToExistingPersistentObject(ProjectForm f, Project p) {
		p.setName(f.getName());
		p.setDescription(f.getDescription());
		p.setOwnerUUID(f.getOwnerUUID());
		p.setProjectStart(f.getStart());
		p.setProjectEnd(f.getEnd());
		p.setProjectCustomer(f.getCustomer());
		p.setProjectLocation(f.getLocation());
		p.setParticipants(f.getParticipants());
		p.setSuppliers(f.getSuppliers());
		p.setGallery(f.getGallery());
		
		return p;
	}
}
