/**
 * 
 */
package org.nww.modules.pinboard;

import javax.annotation.Resource;

import org.nww.core.data.form.FormDataMapper;
import org.nww.modules.pinboard.orm.PinboardEntry;
import org.nww.modules.pinboard.orm.PinboardManager;

/**
 * @author mga
 *
 */
public class PinboardEntryFormDataMapper implements FormDataMapper<PinboardEntryForm, PinboardEntry> {

	@Resource(name = "PinboardManager")
	private PinboardManager pinboardMgr;
	
	@Override
	public PinboardEntryForm mapToForm(PinboardEntry e) {
		PinboardEntryForm f = new PinboardEntryForm();
		
		f.setUUID(e.getUUID());
		f.setType(e.getType());
		f.setStatus(e.getStatus());
		f.setSubject(e.getSubject());
		f.setDescription(e.getDescription());
		f.setOwningUserUUID(e.getOwningUserUUID());
		
		return f;
	}

	@Override
	public PinboardEntry mapToPersistentObject(PinboardEntryForm form) {
		PinboardEntry e = pinboardMgr.createNew();
		return mapToExistingPersistentObject(form, e);
	}

	@Override
	public PinboardEntry mapToExistingPersistentObject(PinboardEntryForm f,
			PinboardEntry e) {
		e.setType(f.getType());
		e.setStatus(f.getStatus());
		e.setSubject(f.getSubject());
		e.setDescription(f.getDescription());
		e.setOwningUserUUID(f.getOwningUserUUID());
		
		return e;
	}

}
