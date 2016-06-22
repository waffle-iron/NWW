/**
 * 
 */
package org.nww.modules.files;

import java.util.Arrays;

/**
 * @author mga
 *
 */
public class FileRequestInformation {
	
	private static final String ATTRIBUTE_SEPARATOR = "_";
	
	private String purName;
	private String[] attributes;
	
	public FileRequestInformation(String requestFileName) {
		String[] splitted = requestFileName.split(ATTRIBUTE_SEPARATOR);
		
		this.purName = splitted[0];
		if(splitted.length > 1) {
			this.attributes = Arrays.copyOfRange(splitted, 1, splitted.length);
		}
	}
	
	/**
	 * @return the pur file name without possible attached attributes
	 */
	public String getPurName() {
		return purName;
	}
	
	/**
	 * @return array of attributes to handle
	 */
	public String[] getAttributes() {
		return attributes;
	}
	
	/**
	 * @return true if the file name has parsed attributes to handle
	 */
	public boolean hasAttributes() {
		return null != this.attributes && this.attributes.length > 0;
	}
	
	/**
	 * @return the number of attributes parsed from the file name
	 */
	public int getAttributeCount() {
		return hasAttributes() ? this.attributes.length : 0;
	}
}
