/**
 * 
 */
package org.nww.services.web;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author mga
 */
public class URLUtilsServiceImpl implements URLUtilsService {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	private static final String ENCODING = "UTF-8";
	
	/* (non-Javadoc)
	 * @see org.nww.services.web.URLUtilsService#escapeURLSegments(java.lang.String)
	 */
	@Override
	public String encodeURLSegments(String segment) {
		String encoded = segment.trim(); // replace leading and following whitespaces
		
		try {
			return URLEncoder.encode(encoded, ENCODING);
		} catch (UnsupportedEncodingException e) {
			log.error("This error should never happen because the encoding is hardcoded to \"UTF-8\"!", e);
		}
		
		return encoded;
	}
	
	/* (non-Javadoc)
	 * @see org.nww.services.web.URLUtilsService#decodeURLSegments(java.lang.String)
	 */
	@Override
	public String decodeURLSegments(String segment) {
		try {
			return URLDecoder.decode(segment, ENCODING);
		} catch (UnsupportedEncodingException e) {
			log.error("This error should never happen because the encoding is hardcoded to \"UTF-8\"!", e);
		}
		
		return segment;
	}
}
