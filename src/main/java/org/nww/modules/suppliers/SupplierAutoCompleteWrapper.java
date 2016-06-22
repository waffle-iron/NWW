/**
 * 
 */
package org.nww.modules.suppliers;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mga
 *
 */
public class SupplierAutoCompleteWrapper {
	private class DataEntry {
		private String value;
		private Object data;
		
		public DataEntry(String v, Object d) {
			this.value = v;
			this.data = d;
		}
		
		@SuppressWarnings("unused") // used within HTML templates only
		public String getValue() {
			return value;
		}
		
		@SuppressWarnings("unused") // used within HTML templates only
		public Object getData() {
			return data;
		}
	}
	
	private String query;
	private List<DataEntry> suggestions = new ArrayList<>();
	
	/**
	 * @return the query string for the auto complete request
	 */
	public String getQuery() {
		return query;
	}
	
	/**
	 * Set the query string for the autocomplete request.
	 * @param query the query string
	 */
	public void setQuery(String query) {
		this.query = query;
	}
	
	/**
	 * @return the suggestion data
	 */
	public List<DataEntry> getSuggestions() {
		return suggestions;
	}
	
	/**
	 * Set the suggestion data.
	 * @param suggestions the suggestion data
	 */
	public void setSuggestions(List<DataEntry> suggestions) {
		this.suggestions = suggestions;
	}
	
	/**
	 * Adds a new entry to the suggestion data.
	 * @param value the value of the suggestion
	 * @param data additional data
	 */
	public void addSuggestion(String value, Object data) {
		if(null == this.suggestions) {
			this.suggestions = new ArrayList<>();
		}
		
		this.suggestions.add(new DataEntry(value, data));
	}
}
