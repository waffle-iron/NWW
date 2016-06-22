package org.nww.core.data;

import java.util.Date;

public interface PersistentObject {

    /**
     * Get the UUID of the persistent object. The UUID is usually used as a
     * primary key.
     *
     * @return
     */
    public String getUUID();

    /**
     * Set the UUID of the persistent object.
     *
     * @param uuid
     */
    public void setUUID(String uuid);

    /**
     * Get the last modification date of this object.
     *
     * @return Date
     */
    public Date getLastModified();

    /**
     * Set the last modification date of this object.
     *
     * @param lastModified
     */
    public void setLastModified(Date lastModified);
}
