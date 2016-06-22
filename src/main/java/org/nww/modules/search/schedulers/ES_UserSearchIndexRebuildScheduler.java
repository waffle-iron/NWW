/**
 * 
 */
package org.nww.modules.search.schedulers;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.nww.core.data.PersistentObjectRepository;
import org.nww.core.system.PersistentObjectManager;
import org.nww.modules.profiles.orm.Profile;
import org.nww.modules.search.AbstractSearchIndexRebuildScheduler;
import org.nww.modules.search.SearchIndexRebuildScheduler;
import org.nww.modules.search.orm.users.ES_User;
import org.nww.modules.search.orm.users.ES_UserRepository;
import org.nww.modules.suppliers.orm.SupplierManager;
import org.nww.modules.users.orm.User;
import org.nww.modules.users.orm.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * Elastic Search user search index rebuild scheduler.
 * Runs the rebuild of the index automatically every 10min.
 * 
 * @author mga
 */
public class ES_UserSearchIndexRebuildScheduler 
		extends AbstractSearchIndexRebuildScheduler<ES_User, User> 
		implements SearchIndexRebuildScheduler<ES_User, User> {
	
	private ES_User dummy = new ES_User();
	
	@Resource(name = "UserManager")
	private UserManager userMgr;
	
	@Resource(name = "SupplierManager")
	private SupplierManager supplierMgr;
	
	@Autowired
	private ES_UserRepository repo;
	
	
	@Override
	public CrudRepository<ES_User, String> getRepository() {
		return this.repo;
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
	 * @see org.nww.modules.search.SearchIndexRebuildScheduler#getPersistenceManager()
	 */
	@Override
	public PersistentObjectManager<User, ? extends PersistentObjectRepository<User>> getPersistenceManager() {
		return this.userMgr;
	}

	/*
	 * (non-Javadoc)
	 * @see org.nww.modules.search.SearchIndexRebuildScheduler#createDataMap(org.nww.core.data.PersistentObject)
	 */
	@Override
	public Map<String, Object> createDataMap(User u) {
		Map<String, Object> data = new HashMap<>();
		
		data.put("uuid", u.getUUID());
		data.put("email", u.getUsername());

		if(null != u.getProfile()) {
			Profile p = u.getProfile();
			data.put("firstName", p.getSurename());
			data.put("lastName", p.getName());
			data.put("companyName", p.getCompany());
			data.put("street1", p.getStreet1() + " " + p.getHouseNo());
			data.put("street2", p.getStreet2());
			data.put("city", p.getCity());
			data.put("postalcode", p.getPostalCode());
			data.put("fax", p.getFax());
			data.put("telephone", p.getTelephone());
			data.put("mobile", p.getMobile());
			data.put("homepage", p.getHomepage());
			data.put("specials", p.getSpecials());
			data.put("abilities", p.getAbilities());
			data.put("suppliers", supplierMgr.getSuppliersFromExtensibleObject(p).stream().map(s -> s.getName()).collect(Collectors.toList()));
		}
		
		return data;
	}

	/*
	 * (non-Javadoc)
	 * @see org.nww.modules.search.SearchIndexRebuildScheduler#getDummyInstance()
	 */
	@Override
	public ES_User getDummyInstance() {
		return this.dummy;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.nww.modules.search.AbstractSearchIndexRebuildScheduler#isIndexingPossible(org.nww.core.data.PersistentObject)
	 */
	@Override
	protected boolean isIndexingPossible(User u) {
		// TODO: allow disabling of users
		return true;
	}
}
