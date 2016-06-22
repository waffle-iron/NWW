/**
 * 
 */
package org.nww.modules.pinboard;

import static org.nww.app.Constants.*;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.nww.app.AbstractApplicationController;
import org.nww.core.system.OperationResult.State;
import org.nww.modules.pinboard.orm.PinboardEntry;
import org.nww.modules.pinboard.orm.PinboardManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.context.IContext;

/**
 * @author mga
 *
 */
@Controller
@RequestMapping(value = "/network/pinboard")
public class PinboardController extends AbstractApplicationController {
	
	private static final String ERROR_PINBOARD_ENTRY_NOT_FOUND = "PENF";

	@Resource(name = "PinboardManager")
	private PinboardManager pinboardMgr;
	
	@Resource(name = "PinboardEntryFormDataMapper")
	private PinboardEntryFormDataMapper formDataMapper;
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@ModelAttribute(SELECTED_MENU_ITEM)
	public String populateSelectedMenuItem() {
		return "pinboard";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showOverview(Model model) {
		List<? extends PinboardEntry> offers = pinboardMgr.findAllByTypeAndStatus(PinboardEntry.TYPE_OFFER, PinboardEntry.STATUS_OPEN);
		List<? extends PinboardEntry> requests = pinboardMgr.findAllByTypeAndStatus(PinboardEntry.TYPE_REQUEST, PinboardEntry.STATUS_OPEN);
		
		model.addAttribute("Offers", offers);
		model.addAttribute("Requests", requests);
		
		return "pinboard/overview";
	}
	
	@RequestMapping(value = "/createEntry", method = RequestMethod.GET)
	public String createEntry(Model model) {
		PinboardEntryForm f = new PinboardEntryForm();
		f.setOwningUserUUID(populateCurrentUser().getUUID());
		
		if(populateCurrentUser().isAdmin()) {
			log.debug("CurrentUser is admin: load all users for selection mode.");
			model.addAttribute("AllUsers", getUserManager().findAll());
		}
		model.addAttribute("PinboardEntryForm", f);
		
		return "pinboard/editEntry";
	}
	
	@RequestMapping(value = "/createEntry.do", method = RequestMethod.POST)
	public String createEntryDo(@Valid @ModelAttribute("PinboardEntryForm") PinboardEntryForm form, 
			BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
		
		if(!bindingResult.hasErrors()) {
			log.debug("Binding result has no errors. Trying to save entry.");
			PinboardEntry p = formDataMapper.mapToPersistentObject(form);
			
			if(pinboardMgr.save(p).getResultState() == State.SUCCESSFULL) {
				log.debug("Entry saved. Attempting to send mail..");
				model.addAttribute("Entry", p);
				
				IContext ctx = getTemplateRenderService().prepareContext(model.asMap());
				
				String renderedContent = getTemplateRenderService().renderTemplate("pinboard/mail/pinboardEntryCreatedMail", ctx);
				
				boolean mailSubmitted = getEmailService().sendMail(
						getUserManager().prepareUserEmailAddressesForMassMails(p.getOwningUser()).toArray(new String[0]),
						populateContactEmail(), 
						(p.getType().equals(PinboardEntry.TYPE_OFFER) ? "Neues Angebot von " : "Neue Anfrage von ") + p.getOwningUser().getDisplayName(),
						renderedContent);
				
				if(mailSubmitted) {
					log.debug("Mail send successfully!");
					redirectAttributes.addAttribute(REDIRECT_PARAM_NAME_MESSAGE, "PESC");
				}
				else {
					log.debug("Mail sending error");
					redirectAttributes.addAttribute(REDIRECT_PARAM_NAME_ERROR, "PENE"); // pinboard entry notification error
				}
				return "redirect:/network/pinboard/";
			}
		}
		
		log.debug("Binding result has errors. Return to form..");
		
		if(populateCurrentUser().isAdmin()) {
			log.debug("CurrentUser is admin: load all users for selection mode.");
			model.addAttribute("AllUsers", getUserManager().findAll());
		}
		
		return "pinboard/editEntry";
	}
	
	@RequestMapping(value = "/entries/{uuid}", method = RequestMethod.GET)
	public String entryDetails(@PathVariable("uuid") String uuid, RedirectAttributes redirectAttributes, Model model) {
		PinboardEntry e = pinboardMgr.findOne(uuid);
		
		if(null == e) {
			log.error("Could not find PinboardEntry with UUID '" + uuid + "'!");
			redirectAttributes.addAttribute(REDIRECT_PARAM_NAME_ERROR, ERROR_PINBOARD_ENTRY_NOT_FOUND);
			return "redirect:/network/pinboard/";
		}
		
		model.addAttribute("Entry", e);
		
		return "pinboard/entryDetails";
	}
	
	@RequestMapping(value = "/entries/{uuid}/edit", method = RequestMethod.GET)
	public String editEntry(@PathVariable("uuid") String uuid, RedirectAttributes redirectAttributes, Model model) {
		PinboardEntry e = pinboardMgr.findOne(uuid);
		
		if(null == e) {
			log.error("Could not find PinboardEntry with UUID '" + uuid + "'!");
			redirectAttributes.addAttribute(REDIRECT_PARAM_NAME_ERROR, ERROR_PINBOARD_ENTRY_NOT_FOUND);
			return "redirect:/network/pinboard/";
		}
		
		PinboardEntryForm f = formDataMapper.mapToForm(e);
		
		if(populateCurrentUser().isAdmin()) {
			log.debug("CurrentUser is admin: load all users for selection mode.");
			model.addAttribute("AllUsers", getUserManager().findAll());
		}
		
		model.addAttribute("PinboardEntryForm", f);
		
		return "pinboard/editEntry";
	}
	
	@RequestMapping(value = "entries/edit.do", method = RequestMethod.POST)
	public String editEntryDo(@Valid @ModelAttribute("PinboardEntryForm") PinboardEntryForm form, 
			BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
		PinboardEntry e = pinboardMgr.findOne(form.getUUID());
		
		if(null == e) {
			log.error("Could not find PinboardEntry with UUID '" + form.getUUID() + "'!");
			redirectAttributes.addAttribute(REDIRECT_PARAM_NAME_ERROR, ERROR_PINBOARD_ENTRY_NOT_FOUND);
			return "redirect:/network/pinboard/";
		}
		
		if(!bindingResult.hasErrors()) {
			PinboardEntry p = formDataMapper.mapToExistingPersistentObject(form, e);
			
			if(pinboardMgr.save(p).getResultState() == State.SUCCESSFULL) {
				model.addAttribute("Entry", p);
				
				IContext ctx = getTemplateRenderService().prepareContext(model.asMap());
				
				String renderedContent = getTemplateRenderService().renderTemplate("pinboard/mail/pinboardEntryEditedMail", ctx);
				
				boolean mailSubmitted = getEmailService().sendMail(
						getUserManager().prepareUserEmailAddressesForMassMails(p.getOwningUser()).toArray(new String[0]),
						populateContactEmail(), 
						p.getOwningUser().getDisplayName() + " hat " + (p.getType().equals(PinboardEntry.TYPE_OFFER) ? "sein Angebot" : "seine Anfrage") + " bearbeitet!",
						renderedContent);
				
				if(mailSubmitted) {
					redirectAttributes.addAttribute(REDIRECT_PARAM_NAME_MESSAGE, "PESC");
				}
				else {
					redirectAttributes.addAttribute(REDIRECT_PARAM_NAME_ERROR, "PENE"); // pinboard entry notification error
				}
				return "redirect:/network/pinboard/";
			}
		}
		
		if(populateCurrentUser().isAdmin()) {
			model.addAttribute("AllUsers", getUserManager().findAll());
		}
		
		return "pinboard/editEntry";
	}
	
	@RequestMapping(value = "entries/{uuid}/close.do", method = RequestMethod.POST)
	public String closeEntryDo(
			@PathVariable("uuid") String uuid,
			@RequestParam(name = "origin", required = true) String origin,
			RedirectAttributes redirectAttributes, Model model) {
		PinboardEntry p = pinboardMgr.findOne(uuid);
		
		if(null == p) {
			log.error("Could not find PinboardEntry with UUID '" + uuid + "'!");
			redirectAttributes.addAttribute(REDIRECT_PARAM_NAME_ERROR, ERROR_PINBOARD_ENTRY_NOT_FOUND);
			return "redirect:/network/pinboard/";
		}
		
		p.setStatus(PinboardEntry.STATUS_CLOSED);
		
		if(pinboardMgr.save(p).getResultState() == State.SUCCESSFULL) {
			model.addAttribute("Entry", p);
			IContext ctx = getTemplateRenderService().prepareContext(model.asMap());
			
			String renderedContent = getTemplateRenderService().renderTemplate("pinboard/mail/pinboardEntryEditedMail", ctx);
			
			boolean mailSubmitted = getEmailService().sendMail(
					getUserManager().prepareUserEmailAddressesForMassMails(p.getOwningUser()).toArray(new String[0]),
					populateContactEmail(), 
					p.getOwningUser().getDisplayName() + " hat " + (p.getType().equals(PinboardEntry.TYPE_OFFER) ? "sein Angebot" : "seine Anfrage") + " geschlossen!",
					renderedContent);
			if(mailSubmitted) {
				redirectAttributes.addAttribute(REDIRECT_PARAM_NAME_MESSAGE, "PECS");
			}
			else {
				redirectAttributes.addAttribute(REDIRECT_PARAM_NAME_ERROR, "PENE"); // pinboard entry notification error
			}
		}
		else {
			redirectAttributes.addAttribute(REDIRECT_PARAM_NAME_ERROR, "PECE");
		}
		
		if("overview".equals(origin)) {
			return "redirect:/network/pinboard/";
		}
		
		return "redirect:/network/users/" + origin + "/";
	}
}
