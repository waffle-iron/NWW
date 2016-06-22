/**
 * 
 */
package org.nww.config;

import org.nww.modules.search.schedulers.ES_PinboardEntrySearchIndexRebuildScheduler;
import org.nww.modules.search.schedulers.ES_SupplierSearchIndexRebuildScheduler;
import org.nww.modules.search.schedulers.ES_UserSearchIndexRebuildScheduler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The system wide scheduler configuration.
 * @author mga
 *
 */
@Configuration
public class SchedulerConfig {
	
	/**
	 * @return the elastic search user index rebuild scheduler
	 */
	@Bean
	public ES_UserSearchIndexRebuildScheduler setupESUserSearchIndexRebuildScheduler() {
		return new ES_UserSearchIndexRebuildScheduler();
	}
	
	/**
	 * @return the elastic search pinboard entry index rebuild scheduler
	 */
	@Bean
	public ES_PinboardEntrySearchIndexRebuildScheduler setupESPinboardEntrySearchIndexRebuildScheduler() {
		return new ES_PinboardEntrySearchIndexRebuildScheduler();
	}
	
	/**
	 * @return the elastic search supplier index rebuild scheduler
	 */
	@Bean
	public ES_SupplierSearchIndexRebuildScheduler setupESSupplierSearchIndexRebuildScheduler() {
		return new ES_SupplierSearchIndexRebuildScheduler();
	}
}
