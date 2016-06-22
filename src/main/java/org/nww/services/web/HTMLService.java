/**
 * 
 */
package org.nww.services.web;

/**
 * Provides service methods used with HTML markup.
 * @author mga
 *
 */
public interface HTMLService {
	/**
	 * Remove the tags and their attributes from the passed HTML code and return only the plain text.
	 * @param html the code to clean from HTML tags
	 * @return the cleaned up plain text
	 */
	public String removeHTMLTags(String html);

	/**
	 * Parses the passed HTML string for image tags and tries to append the file size information from a possible
	 * style attribute width and height (either or both) information to the src url for optimized image output. 
	 * @param html the HMTL markup to be parsed and optimized
	 * @return the optimized html markup
	 */
	public String optimizedHTMLImageTags(String html);

	/**
	 * Converts well known new line markers like \r\n to HTML <br/> tags.
	 * @param withNewlines the string to search new lines inside 
	 * @return HTML converted string
	 */
	public String convertNewlinesToHTML(String withNewlines);

	/**
	 * Converts HTML br tags into line feeds.
	 * @param withBreaks the HTML to search for br tags
	 * @return HTML with line feeds
	 */
	String convertHTMLBreaksToNewlines(String withBreaks);
}
