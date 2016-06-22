/**
 * 
 */
package org.nww.config;

import org.nww.core.validation.ListSize;
import org.nww.core.validation.ListSizeValidator;
import org.nww.core.validation.MapSize;
import org.nww.core.validation.MapSizeValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author mga
 *
 */
@Configuration
public class ValidationConfig {
	
	/**
	 * @return custom validator for the {@link MapSize} annotation
	 */
	@Bean
	public MapSizeValidator mapSizeValidator() {
		return new MapSizeValidator();
	}
	
	/**
	 * @return custom validator for the {@link ListSize} annotation
	 */
	@Bean
	public ListSizeValidator listSizeValidator() {
		return new ListSizeValidator();
	}
}
