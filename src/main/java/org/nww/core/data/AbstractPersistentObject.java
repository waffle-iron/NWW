/**
 *
 */
package org.nww.core.data;

import java.util.Date;

import org.nww.core.utils.ApplicationContextHelper;
import org.nww.core.utils.LocaleHelper;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Transient;

/**
 * @author mga
 *
 */
public abstract class AbstractPersistentObject implements PersistentObject {

    @Id
    private String uuid;

    @LastModifiedDate
    private Date lastModified;

    @Transient
    protected LocaleHelper localeHelper = ApplicationContextHelper.getBeanByClass(LocaleHelper.class);

    /* (non-Javadoc)
     * @see org.sunrise.core.data.PersistentObject#getUUID()
     */
    @Override
    public String getUUID() {
        return this.uuid;
    }

    /* (non-Javadoc)
     * @see org.sunrise.core.data.PersistentObject#setUUID(java.lang.String)
     */
    @Override
    public void setUUID(String uuid) {
        this.uuid = uuid;
    }

    /* (non-Javadoc)
     * @see org.sunrise.core.data.PersistentObject#getLastModified()
     */
    @Override
    public Date getLastModified() {
        return this.lastModified;
    }

    /* (non-Javadoc)
     * @see org.sunrise.core.data.PersistentObject#setLastModified(java.util.Date)
     */
    @Override
    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    @Override
    public int hashCode() {
        if (null != getUUID()) {
            return getUUID().hashCode();
        }
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (null != obj && obj instanceof AbstractPersistentObject) {
            AbstractPersistentObject aObj = (AbstractPersistentObject) obj;
            return getUUID() != null && getUUID().equals(aObj.getUUID());
        }

        return false;
    }
    
    @Override
    public String toString() {
    	return getUUID() + getLastModified().toString();
    }
}
