/**
 * 
 */
package org.nww.modules.projects;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.nww.app.AbstractApplicationController;
import org.nww.modules.projects.orm.Project;
import org.nww.modules.projects.orm.ProjectManager;
import org.nww.modules.projects.orm.ProjectParticipantData;
import org.nww.modules.projects.orm.ProjectSupplierData;
import org.nww.modules.users.orm.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * The project controller is responsible for handling all servlet request related to
 * the project management feature.
 * @author mga
 */
@Controller
@RequestMapping(value = "/network/projects")
public class ProjectController extends AbstractApplicationController {
	
	private static final String TEMPLATE_CREATE_PROJECT = "projects/createProject";
	private static final String TEMPLATE_EDIT_PROJECT = "projects/editProject";
	private static final String REDIRECT_AFTER_ADD_MODE_CREATE = "create";
	private static final String REDIRECT_AFTER_ADD_MODE_EDIT = "edit";
	
	@Resource(name = "ProjectManager")
	private ProjectManager projectMgr;
	
	@Resource(name = "ProjectFormDataMapper")
	private ProjectFormDataMapper mapper;
	
	@ModelAttribute("Users")
	public List<? extends User> populateUsers() {
		return getUserManager().findAll();
	}
	
	/**
	 * Show the project list.
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String projectList(Model model) {
		// workaround for sorting, paging will be implemented later
		Pageable p = new PageRequest(0, 1000, Direction.DESC, "lastModified");
		
		Page<? extends Project> projects = projectMgr.findAll(p);
		
		model.addAttribute("Projects", projects.getContent());
		
		return "projects/projectList";
	}
	
	/**
	 * Show the project creation form.
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String create(Model model) {
		ProjectForm form = new ProjectForm();
		
		model.addAttribute("ProjectForm", form);
		
		return TEMPLATE_CREATE_PROJECT;
	}
	
	@RequestMapping(value = "/addParticipant.do", method = RequestMethod.POST)
	public String addParticipant(
			@ModelAttribute("ProjectForm") ProjectForm form, 
			@RequestParam(required = false, defaultValue = REDIRECT_AFTER_ADD_MODE_CREATE) String mode,
			Model model) {
		
		form.getParticipants().add(0, new ProjectParticipantData());
		
		if(REDIRECT_AFTER_ADD_MODE_EDIT.equals(mode)) {
			return TEMPLATE_EDIT_PROJECT;
		}
		
		return TEMPLATE_CREATE_PROJECT;
	}
	
	@RequestMapping(value = "/removeParticipant.do", method = RequestMethod.POST)
	public String removeParticipant(
			@ModelAttribute("ProjectForm") ProjectForm form,
			@RequestParam(required = true) Integer removeIndex,
			@RequestParam(required = false, defaultValue = REDIRECT_AFTER_ADD_MODE_CREATE) String mode,
			Model model) {
		
		form.getParticipants().remove((int)removeIndex);
		
		if(REDIRECT_AFTER_ADD_MODE_EDIT.equals(mode)) {
			return TEMPLATE_EDIT_PROJECT;
		}
		
		return TEMPLATE_CREATE_PROJECT;
	}
	
	@RequestMapping(value = "/addSupplier.do", method = RequestMethod.POST)
	public String addSupplier(
			@ModelAttribute("ProejctForm") ProjectForm form,
			@RequestParam(required = false, defaultValue = REDIRECT_AFTER_ADD_MODE_CREATE) String mode,
			Model model) {
		
		form.getSuppliers().add(0, new ProjectSupplierData());
		
		if(REDIRECT_AFTER_ADD_MODE_EDIT.equals(mode)) {
			return TEMPLATE_EDIT_PROJECT;
		}
		
		return TEMPLATE_CREATE_PROJECT;
	}
	
	@RequestMapping(value = "/removeSupplier.do", method = RequestMethod.POST)
	public String removeSupplier(
			@ModelAttribute("ProjectForm") ProjectForm form,
			@RequestParam(required = true) Integer removeIndex,
			@RequestParam(required = false, defaultValue = REDIRECT_AFTER_ADD_MODE_CREATE) String mode,
			Model model) {
		
		form.getSuppliers().remove((int)removeIndex);
		
		if(REDIRECT_AFTER_ADD_MODE_EDIT.equals(mode)) {
			return TEMPLATE_EDIT_PROJECT;
		}
		
		return TEMPLATE_CREATE_PROJECT;
	}
	
	/**
	 * Save a new project.
	 * @param form
	 * @param bindingResult
	 * @param redirectAttributes
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/new.do", method = RequestMethod.POST)
	public String createDo(
			@Valid @ModelAttribute("ProjectForm") ProjectForm form, 
			BindingResult bindingResult, 
			RedirectAttributes redirectAttributes, Model model) {
		
		if(!bindingResult.hasErrors()) {
			projectMgr.save(mapper.mapToPersistentObject(form));
			
			redirectAttributes.addAttribute("PCS", true);
			return "redirect:/network/projects/";
		}
		
		return TEMPLATE_CREATE_PROJECT;
	}
}
