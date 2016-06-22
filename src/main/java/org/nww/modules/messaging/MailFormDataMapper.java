/**
 * 
 */
package org.nww.modules.messaging;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.nww.core.data.form.FormDataMapper;
import org.nww.modules.messaging.orm.Mail;
import org.nww.modules.messaging.orm.MailRepository;
import org.springframework.util.StringUtils;

/**
 * @author mga
 *
 */
public class MailFormDataMapper implements FormDataMapper<MailForm, Mail> {

	@Resource(name = "MailRepository")
	private MailRepository<? extends Mail> mailRepository;
	
	/*
	 * (non-Javadoc)
	 * @see org.nww.core.data.form.FormDataMapper#mapToForm(org.nww.core.data.PersistentObject)
	 */
	@Override
	public MailForm mapToForm(Mail n) {
		MailForm f = new MailForm();
		f.setUUID(n.getUUID());
		f.setRecipients(StringUtils.collectionToDelimitedString(n.getRecipients(), ";") );
		f.setSubject(n.getSubject());
		f.setContent(n.getContent());
		
		return f;
	}

	/*
	 * (non-Javadoc)
	 * @see org.nww.core.data.form.FormDataMapper#mapToPersistentObject(org.nww.core.data.form.Form)
	 */
	@Override
	public Mail mapToPersistentObject(MailForm f) {
		Mail n = mapToExistingPersistentObject(f, mailRepository.createNew());
		
		n.setUUID(null);
		
		return n;
	}

	/*
	 * (non-Javadoc)
	 * @see org.nww.core.data.form.FormDataMapper#mapToExistingPersistentObject(org.nww.core.data.form.Form, org.nww.core.data.PersistentObject)
	 */
	@Override
	public Mail mapToExistingPersistentObject(MailForm f, Mail n) {
		n.setUUID(f.getUUID());
		
		String[] rec = !StringUtils.isEmpty(f.getRecipients()) ? f.getRecipients().split(";") : new String[0];
		List<String> recipients = new ArrayList<>();
		for (int i = 0; i < rec.length; i++) {
			recipients.add(rec[i]);
		}
		
		n.setRecipients(recipients);
		n.setSubject(f.getSubject());
		n.setContent(f.getContent());
		
		return n;
	}

}
