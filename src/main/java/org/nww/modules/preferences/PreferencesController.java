/**
 * 
 */
package org.nww.modules.preferences;

import org.nww.app.AbstractApplicationController;
import org.nww.modules.users.orm.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Handles all requests for preferences
 * @author mga
 *
 */
@Controller
public class PreferencesController extends AbstractApplicationController {
	@Value("${nww.preferences.enabled}")
	private boolean isPreferenceFeatureEnabled;
	
	@RequestMapping(value = "/network/preferences/inc/profileButton/{uuid}", method = RequestMethod.GET)
	public String includeUserProfilePreferenceButton(@PathVariable("uuid") String uuid, Model model) {
		if(!isPreferenceFeatureEnabled) {
			return "common/empty";
		}
		
		User u = getUserManager().findOne(uuid);
		
		model.addAttribute("User", u);
		
		return "preferences/inc/userPreferencesButton";
	}
	
	@RequestMapping(value = "/network/users/{user}/preferences", method = RequestMethod.GET)
	public String editUserPreferences(@PathVariable("user") String username, RedirectAttributes redirectAttributes, Model model) {
		if(!isPreferenceFeatureEnabled) {
			return "redirect:/network/";
		}
		
		User u = getUserManager().findByUsername(username);
		if(null == u) {
			redirectAttributes.addAttribute("e", "UNF");
			return "redirect:/network/";
		}
		
		model.addAttribute("User", u);
		
		return "preferences/editUserPreferences";
	}
}
