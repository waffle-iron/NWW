/**
 * 
 */
package org.nww.modules.files;

/**
 * Class used as a JSON result for AJAX file uploads.
 * @author mga
 *
 */
public class FileUploadResult {
	private boolean success;
	private String url;
	
	/**
	 * Create a new file upload result object.
	 * @param url the file download request URL
	 */
	public FileUploadResult(boolean success, String url) {
		this.success = success;
		this.url = url;
	}
	
	/**
	 * @return true if the file upload succeeded otherwise false
	 */
	public boolean getSuccess() {
		return this.success;
	}
	
	/**
	 * Set whether the file upload succeeded or not.
	 * @param success true if successful otherwise false
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	/**
	 * @return get the request URL string
	 */
	public String getUrl() {
		return url;
	}
	
	/**
	 * Set the URL string to retrieve the uploaded file.
	 * @param url the file request URL string
	 */
	public void setUrl(String url) {
		this.url = url;
	}
}
