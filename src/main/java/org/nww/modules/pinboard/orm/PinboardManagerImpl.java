/**
 * 
 */
package org.nww.modules.pinboard.orm;

import java.util.List;

import javax.annotation.Resource;

import org.nww.core.system.AbstractPersistentObjectManager;
import org.nww.modules.users.orm.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Basic implementation of the {@link PinboardManager} interface.
 * @author mga
 *
 */
public class PinboardManagerImpl extends AbstractPersistentObjectManager<PinboardEntry, PinboardEntryRepository<PinboardEntry>>
		implements PinboardManager{

	@Resource(name = "PinboardEntryRepository")
	private PinboardEntryRepository<PinboardEntry> pinboardEntryRepo;
	
	/*
	 * (non-Javadoc)
	 * @see org.nww.core.system.PersistentObjectManager#getRepository()
	 */
	@Override
	public PinboardEntryRepository<PinboardEntry> getRepository() {
		return this.pinboardEntryRepo;
	}

	/*
	 * (non-Javadoc)
	 * @see org.nww.core.system.PersistentObjectManager#setRepository(org.nww.core.data.PersistentObjectRepository)
	 */
	@Override
	public void setRepository(PinboardEntryRepository<PinboardEntry> repository) {
		this.pinboardEntryRepo = repository;
	}

	/*
	 * (non-Javadoc)
	 * @see org.nww.modules.pinboard.orm.PinboardManager#findAllByTypeAndStatus(org.nww.modules.pinboard.PinboardEntryType, org.nww.modules.pinboard.PinboardEntryStatus)
	 */
	@Override
	public List<? extends PinboardEntry> findAllByTypeAndStatus(Integer type, Integer status) {
		return getRepository().findAllByTypeAndStatus(type, status);
	}

	/*
	 * (non-Javadoc)
	 * @see org.nww.modules.pinboard.orm.PinboardManager#findAllByTypeAndStatus(org.nww.modules.pinboard.PinboardEntryType, org.nww.modules.pinboard.PinboardEntryStatus, org.springframework.data.domain.Pageable)
	 */
	@Override
	public Page<? extends PinboardEntry> findAllByTypeAndStatus(Integer type, Integer status,
			Pageable p) {
		return getRepository().findAllByTypeAndStatus(type, status, p);
	}

	/*
	 * (non-Javadoc)
	 * @see org.nww.modules.pinboard.orm.PinboardManager#findAllByUser(org.nww.modules.users.orm.User)
	 */
	@Override
	public List<? extends PinboardEntry> findAllByUser(User u) {
		return getRepository().findAllByUser(u);
	}

	/*
	 * (non-Javadoc)
	 * @see org.nww.modules.pinboard.orm.PinboardManager#findAllByUser(org.nww.modules.users.orm.User, org.springframework.data.domain.Pageable)
	 */
	@Override
	public Page<? extends PinboardEntry> findAllByUser(User u, Pageable p) {
		return getRepository().findAllByUser(u, p);
	}

}
