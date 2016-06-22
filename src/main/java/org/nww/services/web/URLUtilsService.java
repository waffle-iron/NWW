/**
 * 
 */
package org.nww.services.web;

/**
 * Provides helper methods to work with URLs and URL encoding.
 * @author mga
 */
public interface URLUtilsService {
	/**
	 * Escape special characters not beeing allowed within urls.
	 * @param segment the url segment to be escaped
	 * @return the escaped segment string
	 */
	public String encodeURLSegments(String segment);
	
	/**
	 * Unescape special characters not beeing allowed within urls.
	 * @param segment the url segment to be unescaped
	 * @return the unescaped segment string
	 */
	public String decodeURLSegments(String segment);
}
