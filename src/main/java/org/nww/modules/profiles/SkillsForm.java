/**
 * 
 */
package org.nww.modules.profiles;

import java.util.ArrayList;
import java.util.List;

import org.nww.core.data.form.AbstractPersistentObjectForm;

/**
 * Defines a webform for handling a users skills
 * @author mga
 *
 */
public class SkillsForm extends AbstractPersistentObjectForm {
	private List<String> skills = new ArrayList<>();
	private List<String> highlightedSkills = new ArrayList<>();
	
	/**
	 * @return the skills
	 */
	public List<String> getSkills() {
		return skills;
	}
	
	/**
	 * @param skills the skills to set
	 */
	public void setSkills(List<String> skills) {
		this.skills = skills;
	}
	
	/**
	 * @return the highlightedSkills
	 */
	public List<String> getHighlightedSkills() {
		return highlightedSkills;
	}
	
	/**
	 * @param highlightedSkills the highlightedSkills to set
	 */
	public void setHighlightedSkills(List<String> highlightedSkills) {
		this.highlightedSkills = highlightedSkills;
	}
}
