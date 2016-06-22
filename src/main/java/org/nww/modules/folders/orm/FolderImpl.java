/**
 * 
 */
package org.nww.modules.folders.orm;

import java.util.ArrayList;
import java.util.List;

import org.nww.core.data.AbstractExtensiblePersistentObject;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.StringUtils;

/**
 * Basic implementation of {@link Folder} interface.
 * @author mga
 *
 */
@Document(collection = "folders")
public class FolderImpl extends AbstractExtensiblePersistentObject implements Folder {

	@Indexed(unique = false)
	private String name;
	private String parentFolderUUID;
	private List<String> subFolderUUIDs = new ArrayList<>();
	private List<String> contentUUIDs = new ArrayList<>();
	
	/* (non-Javadoc)
	 * @see org.nww.modules.folders.orm.Folder#getName()
	 */
	@Override
	public String getName() {
		return this.name;
	}

	/* (non-Javadoc)
	 * @see org.nww.modules.folders.orm.Folder#setName(java.lang.String)
	 */
	@Override
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public boolean hasParentFolderUUID() {
		return !StringUtils.isEmpty(this.parentFolderUUID);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.nww.modules.folders.orm.Folder#getParentFolderUUID()
	 */
	@Override
	public String getParentFolderUUID() {
		return this.parentFolderUUID;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.nww.modules.folders.orm.Folder#setParentFolderUUID(java.lang.String)
	 */
	@Override
	public void setParentFolderUUID(String parentUUID) {
		this.parentFolderUUID = parentUUID;
	}

	/* (non-Javadoc)
	 * @see org.nww.modules.folders.orm.Folder#hasSubFolderUUIDs()
	 */
	@Override
	public boolean hasSubFolderUUIDs() {
		return this.subFolderUUIDs.size() > 0;
	}

	/* (non-Javadoc)
	 * @see org.nww.modules.folders.orm.Folder#getSubFolderUUIDs()
	 */
	@Override
	public List<String> getSubFolderUUIDs() {
		return this.subFolderUUIDs;
	}

	/* (non-Javadoc)
	 * @see org.nww.modules.folders.orm.Folder#setSubFolderUUIDs(java.util.List)
	 */
	@Override
	public void setSubFolderUUIDs(List<String> subFolderUUIDs) {
		this.subFolderUUIDs = subFolderUUIDs;
	}

	/* (non-Javadoc)
	 * @see org.nww.modules.folders.orm.Folder#hasContentUUIDs()
	 */
	@Override
	public boolean hasContentUUIDs() {
		return this.contentUUIDs.size() > 0;
	}

	/* (non-Javadoc)
	 * @see org.nww.modules.folders.orm.Folder#getContentUUIDs()
	 */
	@Override
	public List<String> getContentUUIDs() {
		return this.contentUUIDs;
	}

	/* (non-Javadoc)
	 * @see org.nww.modules.folders.orm.Folder#setContentUUIDs(java.util.List)
	 */
	@Override
	public void setContentUUIDs(List<String> contentUUIDs) {
		this.contentUUIDs = contentUUIDs;
	}
}
