/**
 * 
 */
package org.nww.modules.search.orm.pinboard;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Extension methods definition for the default Spring managed elastic search pinboard entry repository.
 * @author mga
 *
 */
public interface ES_PinboardEntryRepositoryCustom {
	/**
	 * Find a page of pinboard entries that fields match the query term.
	 * @param q the query term to look up
	 * @param p the paging and sorting information
	 * @return page of {@link ES_PinboardEntry} objects
	 */
	public Page<ES_PinboardEntry> findPinboardEntries(String q, Pageable p);
}
