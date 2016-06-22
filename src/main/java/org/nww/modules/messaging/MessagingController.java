/**
 * 
 */
package org.nww.modules.messaging;

import static org.nww.app.Constants.SELECTED_MENU_ITEM;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.nww.app.AbstractApplicationController;
import org.nww.modules.messaging.orm.Mail;
import org.nww.modules.messaging.orm.MailManager;
import org.nww.modules.users.orm.User;
import org.nww.services.TemplateRenderService;
import org.nww.services.web.HTMLService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.context.Context;

/**
 * Handles all request to create, manage and send user notifications using the backend web interface.
 * @author mga
 *
 */
@Controller
@RequestMapping(value = "/network/messages")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class MessagingController extends AbstractApplicationController {
	
	private static final String SELECTED_MENU_ITEM_MESSAGES = "messages";
	
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Resource(name = "MailManager")
	private MailManager mailMgr;
	
	@Resource(name = "MailFormDataMapper")
	private MailFormDataMapper mailFormDataMapper;
	
	@Resource(name = "TemplateRenderService")
	private TemplateRenderService templateRenderService;

	@Resource(name = "HTMLService")
	private HTMLService htmlService;
	
	@RequestMapping(value = { "/", "/{postbox}/", "/{postbox}/{folder}/" }, method = RequestMethod.GET)
	public String list(@PathVariable Optional<String> postbox, @PathVariable Optional<String> folder, Model model) {
		model.addAttribute(SELECTED_MENU_ITEM, SELECTED_MENU_ITEM_MESSAGES);
		
		Iterable<? extends Mail> mails = mailMgr.findByFolder(postbox.orElse("info@netzwerkwohnen.org"), folder.orElse(MailManager.FOLDER_INBOX));
		model.addAttribute("Mails", mails);
		model.addAttribute("SelectedFolder", folder.orElse(MailManager.FOLDER_INBOX));
		
		return "messages/listView";
	}
	
	@RequestMapping(value = "/mail/compose", method = RequestMethod.GET)
	public String composeMail(@RequestParam(name = "draftUUID", required = false) Optional<String> draftUUID, Model model) {
		model.addAttribute(SELECTED_MENU_ITEM, SELECTED_MENU_ITEM_MESSAGES);
		
		MailForm form = new MailForm();
		
		if(draftUUID.isPresent()) {
			log.debug("Found draftUUID try to load draft:");
			Mail draft = mailMgr.findOne(draftUUID.get());
			if(null != draft) {
				log.debug("Found draft for draftUUID '" + draftUUID + "'");
				form = mailFormDataMapper.mapToForm(draft);
			}
			log.error("No draft found for draftUUID '" + draftUUID + "'! Possible data inconsistency?");
		}
				
		model.addAttribute("MailForm", form);
		
		return "messages/mail/compose";
	}
	
	@RequestMapping(value = "/mail/saveDraft.do", method = RequestMethod.POST)
	public ResponseEntity<String> saveDraft(@ModelAttribute("MailForm") MailForm form, Model model) {
		// check for existing mail
		Mail m = mailMgr.saveDraft(form);
		
		if(null != m) {
			return ResponseEntity.ok(m.getUUID());			
		}
		
		return ResponseEntity.badRequest().body("");
	}

	
	@RequestMapping(value = "/mail/sendMail.do", method = RequestMethod.POST)
	public String sendMail(@Valid @ModelAttribute("MailForm") MailForm form, BindingResult bindingResult, 
				RedirectAttributes redirectAttributes, Model model) {
		
		if(!bindingResult.hasErrors()) {
			log.debug("Binding result has no errors, trying to save and send mail!");
			
			Mail m = StringUtils.isEmpty(form.getUUID()) ? 
					mailMgr.createNew() : mailMgr.findOne(form.getUUID());
					
			form.setUUID(m.getUUID()); // ensures the UUID not to get lost through mapping			
			m = mailFormDataMapper.mapToExistingPersistentObject(form, m);
			
			// optimize image tags markup
			m.setContent(htmlService.optimizedHTMLImageTags(m.getContent()));
			
			final Context c = new Context();
			c.setVariable("Mail", m);
			c.setLocale(Locale.GERMANY);
			
			String mailContent = templateRenderService.renderTemplate("messages/mail/templates/dynamicContent", c);
			
			if(mailMgr.send(m, mailContent)) {
				log.debug("Mail sent successfully!");
				
				m.setSendDate(new Date());
				mailMgr.save(m); // result is not important currently as long as send succeeded
				log.debug("Mail saved successfully!");
				
				redirectAttributes.addAttribute("m", "SMS"); // send mail success
				return "redirect:/network/messages/";
			}
			else {
				log.error("Could not send mail: " + m);
				model.addAttribute("ERROR", "SME"); // send mail error
			}
		}
		
		model.addAttribute(SELECTED_MENU_ITEM, SELECTED_MENU_ITEM_MESSAGES);
		return "/messages/mail/compose";
	}
	
	@RequestMapping(value = "/showAddressSelection", method = RequestMethod.GET)
	public String showAddressSelection(Model model) {
		// simply generate a list modal of all users
		List<? extends User> users = getUserManager().findAll();
		
		model.addAttribute("Users", users);
		// TODO: add paging support here!!
		
		return "messages/modals/addressSelection";
	}
	
	@RequestMapping(value = "/details/{uuid}", method = RequestMethod.GET)
	public String showMail(@PathVariable("uuid") String uuid, RedirectAttributes redirectAttributes, Model model) {
		Mail m = mailMgr.findOne(uuid);
		
		if(null == m) {
			log.error("Could not find mail with UUID '" + uuid + "'!");
			redirectAttributes.addFlashAttribute("e", "MNF");
			return "redirect:/network/messages/";
		}
		
		model.addAttribute("Mail", m);
		
		return "messages/mail/details";
	}
}
