/**
 *
 */
package org.nww.core.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Provides a storage for localizable strings.
 *
 * @author MGA
 *
 */
public class LocalizedString implements LocalizableAttribute<String> {

    /**
     * field that is used to keep the persistent localizations.
     */
    private Map<String, String> localizations = new HashMap<>();

    /* (non-Javadoc)
     * @see org.sunrise.core.data.LocalizableAttribute#setValue(java.lang.Object, java.lang.String)
     */
    @Override
    public void setValue(String value, String localeID) {
        localizations.put(localeID, value);
    }

    /* (non-Javadoc)
     * @see org.sunrise.core.data.LocalizableAttribute#getValue(java.lang.String)
     */
    @Override
    public String getValue(String localeID) {
        return localizations.get(localeID);
    }

    /* (non-Javadoc)
     * @see org.sunrise.core.data.LocalizableAttribute#getValues()
     */
    @Override
    public List<String> getValues() {
        return new ArrayList<>(localizations.values());
    }

    /*
     * (non-Javadoc)
     *
     * @see org.sunrise.core.data.LocalizableAttribute#getLocaleIDs()
     */
    @Override
    public Set<String> getLocaleIDs() {
        return localizations.keySet();
    }

    /* (non-Javadoc)
     * @see org.sunrise.core.data.LocalizableAttribute#getMappedValues()
     */
    @Override
    public Map<String, String> getMappedValues() {
        return localizations;
    }
}
