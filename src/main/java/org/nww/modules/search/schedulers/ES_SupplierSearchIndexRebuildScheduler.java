/**
 * 
 */
package org.nww.modules.search.schedulers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.nww.core.data.PersistentObjectRepository;
import org.nww.core.system.PersistentObjectManager;
import org.nww.modules.profiles.orm.Profile;
import org.nww.modules.profiles.orm.ProfileManager;
import org.nww.modules.search.AbstractSearchIndexRebuildScheduler;
import org.nww.modules.search.SearchIndexRebuildScheduler;
import org.nww.modules.search.orm.suppliers.ES_Supplier;
import org.nww.modules.search.orm.suppliers.ES_SupplierRepository;
import org.nww.modules.suppliers.orm.Supplier;
import org.nww.modules.suppliers.orm.SupplierManager;
import org.nww.modules.suppliers.queries.FindProfilesBySupplierQuery;
import org.nww.modules.users.orm.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author mga
 *
 */
public class ES_SupplierSearchIndexRebuildScheduler extends AbstractSearchIndexRebuildScheduler<ES_Supplier, Supplier>
		implements SearchIndexRebuildScheduler<ES_Supplier, Supplier> {

	@Resource(name = "SupplierManager")
	private SupplierManager supplierMgr;
	
	@Resource(name = "UserManager")
	private UserManager userMgr;
	
	@Resource(name = "ProfileManager")
	private ProfileManager profileMgr;
	
	@Autowired
	private ES_SupplierRepository repo; 
	
	private ES_Supplier dummy = new ES_Supplier();
	
	/*
	 * (non-Javadoc)
	 * @see org.nww.modules.search.SearchIndexRebuildScheduler#getDummyInstance()
	 */
	@Override
	public ES_Supplier getDummyInstance() {
		return this.dummy;
	}

	/*
	 * (non-Javadoc)
	 * @see org.nww.modules.search.SearchIndexRebuildScheduler#getRepository()
	 */
	@Override
	public CrudRepository<ES_Supplier, String> getRepository() {
		return this.repo;
	}

	/*
	 * (non-Javadoc)
	 * @see org.nww.modules.search.SearchIndexRebuildScheduler#getPersistenceManager()
	 */
	@Override
	public PersistentObjectManager<Supplier, ? extends PersistentObjectRepository<Supplier>> getPersistenceManager() {
		return this.supplierMgr;
	}

	/*
	 * (non-Javadoc)
	 * @see org.nww.modules.search.SearchIndexRebuildScheduler#createDataMap(org.nww.core.data.PersistentObject)
	 */
	@Override
	public Map<String, Object> createDataMap(Supplier s) {
		Map<String, Object> data = new HashMap<>();
		
		data.put("uuid", s.getUUID());
		data.put("name", s.getName());
		data.put("description", s.getDescription());
		data.put("users", prepareUserData(s));
		
		return data;
	}

	private List<String> prepareUserData(Supplier s) {
		Query q = new FindProfilesBySupplierQuery(s);
		List<? extends Profile> profilesWithSupplier = profileMgr.findAllByQuery(q);
		
		List<String> searchableUserData = new ArrayList<>();
		
		profilesWithSupplier.forEach(p -> {
			searchableUserData.add(p.getEmail() + " " + StringUtils.defaultString(p.getSurename()) + " " + StringUtils.defaultString(p.getName()));
		});
		
		return searchableUserData;
	}

	/*
	 * (non-Javadoc)
	 * @see org.nww.modules.search.AbstractSearchIndexRebuildScheduler#isIndexingPossible(org.nww.core.data.PersistentObject)
	 */
	@Override
	protected boolean isIndexingPossible(Supplier p) {
		return p.isApproved();
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
}
