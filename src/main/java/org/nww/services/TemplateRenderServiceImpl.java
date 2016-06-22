/**
 * 
 */
package org.nww.services;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.IContext;

/**
 * @author mga
 *
 */
public class TemplateRenderServiceImpl implements TemplateRenderService {

	@Autowired
	private TemplateEngine templateEngine;
	
	/* (non-Javadoc)
	 * @see org.nww.services.TemplateRenderService#getEngine()
	 */
	@Override
	public TemplateEngine getEngine() {
		return this.templateEngine;
	}

	/* (non-Javadoc)
	 * @see org.nww.services.TemplateRenderService#setEngine(org.thymeleaf.TemplateEngine)
	 */
	@Override
	public void setEngine(TemplateEngine engine) {
		this.templateEngine = engine;
	}

	/* (non-Javadoc)
	 * @see org.nww.services.TemplateRenderService#renderTemplate(java.lang.String, org.thymeleaf.context.IContext)
	 */
	@Override
	public String renderTemplate(String template, IContext ctx) {
		return getEngine().process(template, ctx);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.nww.services.TemplateRenderService#prepareContext(java.util.Map)
	 */
	@Override
	public IContext prepareContext(Map<String, Object> attributes) {
		Context ctx = new Context();
		
		ctx.setVariables(attributes);
		
		return ctx;
	}
}
