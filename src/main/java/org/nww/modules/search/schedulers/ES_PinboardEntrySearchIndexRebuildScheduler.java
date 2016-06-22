/**
 * 
 */
package org.nww.modules.search.schedulers;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.nww.core.data.PersistentObjectRepository;
import org.nww.core.system.PersistentObjectManager;
import org.nww.modules.pinboard.orm.PinboardEntry;
import org.nww.modules.pinboard.orm.PinboardManager;
import org.nww.modules.search.AbstractSearchIndexRebuildScheduler;
import org.nww.modules.search.SearchIndexRebuildScheduler;
import org.nww.modules.search.orm.pinboard.ES_PinboardEntry;
import org.nww.modules.search.orm.pinboard.ES_PinboardEntryRepository;
import org.nww.services.web.HTMLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * Elastic Search pinboard entry rebuild scheduler.
 * Runs the rebuild every 10min.
 * 
 * @author mga
 */
public class ES_PinboardEntrySearchIndexRebuildScheduler 
		extends AbstractSearchIndexRebuildScheduler<ES_PinboardEntry, PinboardEntry> 
		implements SearchIndexRebuildScheduler<ES_PinboardEntry, PinboardEntry> {

	private ES_PinboardEntry dummy = new ES_PinboardEntry();
	
	@Resource(name = "PinboardManager")
	private PinboardManager pinboardMgr;
	
	@Resource(name = "HTMLService")
	private HTMLService htmlService;
	
	@Autowired
	private ES_PinboardEntryRepository repo;
	
	/* (non-Javadoc)
	 * @see org.nww.modules.search.SearchIndexRebuildScheduler#getRepository()
	 */
	@Override
	public CrudRepository<ES_PinboardEntry, String> getRepository() {
		return this.repo;
	}

	/*
	 * (non-Javadoc)
	 * @see org.nww.modules.search.SearchIndexRebuildScheduler#getPersistenceManager()
	 */
	@Override
	public PersistentObjectManager<PinboardEntry, ? extends PersistentObjectRepository<PinboardEntry>> getPersistenceManager() {
		return this.pinboardMgr;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.nww.modules.search.SearchIndexRebuildScheduler#getDummyInstance()
	 */
	@Override
	public ES_PinboardEntry getDummyInstance() {
		return this.dummy;
	}

	/*
	 * (non-Javadoc)
	 * @see org.nww.modules.search.SearchIndexRebuildScheduler#createDataMap(org.nww.core.data.PersistentObject)
	 */
	@Override
	public Map<String, Object> createDataMap(PinboardEntry pe) {
		Map<String, Object> data = new HashMap<>();
		
		data.put("uuid", pe.getUUID());
		data.put("subject", pe.getSubject());
		data.put("description", htmlService.removeHTMLTags(pe.getDescription()));
		data.put("userName", pe.getOwningUser().getUsername());
		data.put("userDisplayName", pe.getOwningUser().getDisplayName());
		
		return data;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.nww.modules.search.AbstractSearchIndexRebuildScheduler#rebuildIndex()
	 */
	@Override
	@Scheduled (fixedDelay = 600000, initialDelay = 5000) // run every 10min, after 5 seconds intial start delay
	public void rebuildIndex() {
		super.rebuildIndex();
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.nww.modules.search.AbstractSearchIndexRebuildScheduler#isIndexingPossible(org.nww.core.data.PersistentObject)
	 */
	@Override
	protected boolean isIndexingPossible(PinboardEntry p) {
		return p.getStatus().equals(PinboardEntry.STATUS_OPEN);
	}
}
