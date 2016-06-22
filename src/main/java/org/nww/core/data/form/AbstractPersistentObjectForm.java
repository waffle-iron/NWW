/**
 *
 */
package org.nww.core.data.form;

import java.util.Date;

/**
 * @author MGA
 *
 */
public abstract class AbstractPersistentObjectForm implements Form {

    private String uuid;
    private Date lastModified;

    /**
     * @return the uuid
     */
    public String getUUID() {
        return uuid;
    }

    /**
     * @param uuid the uuid to set
     */
    public void setUUID(String uuid) {
        this.uuid = uuid;
    }

    /**
     * @return the lastModified
     */
    public Date getLastModified() {
        return lastModified;
    }

    /**
     * @param lastModified the lastModified to set
     */
    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }
}
