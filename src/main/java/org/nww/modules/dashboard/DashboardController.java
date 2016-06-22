/**
 * 
 */
package org.nww.modules.dashboard;

import static org.nww.app.Constants.*;

import org.nww.app.AbstractApplicationController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Mirco Gatz
 */
@Controller
@RequestMapping(value = "/network")
public class DashboardController extends AbstractApplicationController {
	
	/**
	 * Handles the dashboard requests for the INSPINIA theme.
	 * As most of the dashboard elements are loaded via ajax there is not much to do here.
	 * @param model the view model
	 * @return the path to the dashboard view
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String dashboard(Model model) {
		model.addAttribute(SELECTED_MENU_ITEM, "dashboard");
		
		return "dashboard/default";
	}
}
