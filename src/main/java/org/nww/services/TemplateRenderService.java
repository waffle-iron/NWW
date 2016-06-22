package org.nww.services;

import java.util.Map;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.IContext;

/**
 * Provides methods for a rendering service that allows rendering any kind of template to a string.
 * @author mga
 *
 */
public interface TemplateRenderService {
	/**
	 * Get the template rendering engine for this service.
	 * @return the rendering engine
	 */
	public TemplateEngine getEngine();
	
	/**
	 * Set the template rendering engine for this service.
	 * @param engine the rendering engine
	 */
	public void setEngine(TemplateEngine engine);
	
	/**
	 * Render the template using the given context and return the result string.
	 * @param template the template to be rendered
	 * @param ctx the context holding all render information
	 * @return the rendered template
	 */
	public String renderTemplate(String template, IContext ctx);
	
	/**
	 * Prepare a context object to be used e.g. for rendering a template.
	 * @param attributes the named attributes to be put into the context
	 * @return the context object
	 */
	public IContext prepareContext(Map<String, Object> attributes);
}
