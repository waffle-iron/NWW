/**
 * 
 */
package org.nww.modules.messaging.orm;

import javax.annotation.Resource;

import org.nww.core.system.AbstractPersistentObjectManager;
import org.nww.core.system.OperationResult.State;
import org.nww.modules.folders.orm.Folder;
import org.nww.modules.folders.orm.FolderRepository;
import org.nww.modules.messaging.MailForm;
import org.nww.modules.messaging.MailFormDataMapper;
import org.nww.services.web.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;

/**
 * @author mga
 *
 */
public class MailManagerImpl extends AbstractPersistentObjectManager<Mail, MailRepository<Mail>> implements MailManager {
	
	@Resource(name = "MailRepository")
	private MailRepository<Mail> mailRepository;
	
	@Resource(name = "FolderRepository")
	private FolderRepository<Folder> folderRepository;
	
	@Resource(name = "MailFormDataMapper")
	private MailFormDataMapper mailFormDataMapper;
	
	@Resource(name = "EmailService")
	private EmailService emailService;
	
	@Value("${nww.mail.contact}")
	private String contactMail;
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public MailRepository<Mail> getRepository() {
		return this.mailRepository;
	}
	
	/**
	 * @return the email service instance
	 */
	public EmailService getEmailService() {
		return emailService;
	}

	/*
	 * (non-Javadoc)
	 * @see org.nww.core.system.PersistentObjectManager#setRepository(org.nww.core.data.PersistentObjectRepository)
	 */
	@Override
	public void setRepository(MailRepository<Mail> repository) {
		this.mailRepository = repository;
	}

	/*
	 * (non-Javadoc)
	 * @see org.nww.core.system.AbstractPersistentObjectManager#createNew()
	 */
	@Override
	public Mail createNew() {
		Mail m = getRepository().createNew();
		save(m);
		return m;
	}

	/*
	 * (non-Javadoc)
	 * @see org.nww.modules.messaging.orm.MailManager#send(java.lang.String)
	 */
	@Override
	public boolean send(String uuid) {
		Mail m = findOne(uuid);
		return send(m);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.nww.modules.messaging.orm.MailManager#send(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean send(String uuid, String overrideContent) {
		Mail m = findOne(uuid);
		
		return null != m ? send(m, overrideContent) : false;
	}

	/*
	 * (non-Javadoc)
	 * @see org.nww.modules.messaging.orm.MailManager#send(org.nww.modules.messaging.orm.Mail)
	 */
	@Override
	public boolean send(Mail m) {
		return send(m, m.getContent());
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.nww.modules.messaging.orm.MailManager#send(org.nww.modules.messaging.orm.Mail, java.lang.String)
	 */
	@Override
	public boolean send(Mail m, String overrideContent) {
		
		if(null == m) {
			log.error("Tried sending email but object is null!");
			return false;
		}
		
		boolean sendResult = getEmailService().sendMail(m.getRecipients().toArray(new String[m.getRecipients().size()]), 
				contactMail, 
				m.getSubject(), 
				overrideContent);
		
		if(sendResult) {
			// cleanup possible drafts
			Folder drafts = getOrCreatePostboxFolder(contactMail, FOLDER_DRAFTS);
			drafts.getContentUUIDs().remove(m.getUUID());
			
			folderRepository.save(drafts);
			
			Folder sent = getOrCreatePostboxFolder(contactMail, FOLDER_SENT);
			sent.getContentUUIDs().add(m.getUUID());
			
			folderRepository.save(sent);
		}
		
		return sendResult;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.nww.modules.messaging.orm.MailManager#saveDraft(org.nww.modules.messaging.MailForm)
	 */
	@Override
	public Mail saveDraft(MailForm form) {
		Mail draft = null;
		
		// check whether to create a new ohne 
		if(!StringUtils.isEmpty(form.getUUID()) && exists(form.getUUID())) {
			draft = findOne(form.getUUID());
		}
		else {
			draft = createNew();
			if(save(draft).getResultState() != State.SUCCESSFULL) {
				return null;
			}
			form.setUUID(draft.getUUID());
		}
		
		// update data from form
		draft = mailFormDataMapper.mapToExistingPersistentObject(form, draft);
		
		// save
		if(save(draft).getResultState() == State.SUCCESSFULL) {
			Folder draftFolder = getOrCreatePostboxFolder(contactMail, FOLDER_DRAFTS);
			if(!draftFolder.getContentUUIDs().contains(draft.getUUID())) {
				draftFolder.getContentUUIDs().add(draft.getUUID());
				
				folderRepository.save(draftFolder);
			}
			return draft;
		}
		
		return null;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.nww.modules.messaging.orm.MailManager#findByFolder(java.lang.String, java.lang.String)
	 */
	@Override
	public Iterable<? extends Mail> findByFolder(String postbox, String folder) {
		return findAll(getOrCreatePostboxFolder(postbox, folder).getContentUUIDs());
	}
	
	/**
	 * Checks the existence of a folder inside a postbox. Creates both if they could not be found.
	 * @param postbox the postbox name as the root folder
	 * @param folder the folder inside the postbox
	 * @return the folder inside the postbox
	 */
	private Folder getOrCreatePostboxFolder(String postbox, String folder) {
		// lookup postbox folder
		Folder postboxFolder = folderRepository.findByName(postbox);
		if(null == postboxFolder) {
			postboxFolder = folderRepository.createNew();
			postboxFolder.setName(postbox);
			folderRepository.save(postboxFolder);
		}
		
		// lookup folder inside postbox
		Folder f = folderRepository.findSubfolderByParentUUID(postboxFolder.getUUID(), folder);
		if(null == f) {
			f = folderRepository.createNew();
			f.setName(folder);
			f.setParentFolderUUID(postboxFolder.getUUID());
			folderRepository.save(f);
			
			postboxFolder.getSubFolderUUIDs().add(f.getUUID());
			
			folderRepository.save(postboxFolder);
		}
		
		return f;
	}
}
