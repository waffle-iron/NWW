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
 * Provides a storage for localizable doubles.
 *
 * @author MGA
 *
 */
public class LocalizedDouble implements LocalizableAttribute<Double> {

    /**
     * Field that is used to store localized persistent values.
     */
    private Map<String, Double> localizations = new HashMap<>();

    /*
     * (non-Javadoc)
     *
     * @see
     * org.sunrise.core.data.LocalizableAttribute#setValue(java.lang.Object,
     * java.lang.String)
     */
    @Override
    public void setValue(Double value, String localeID) {
        localizations.put(localeID, value);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.sunrise.core.data.LocalizableAttribute#getValue(java.lang.String)
     */
    @Override
    public Double getValue(String localeID) {
        return localizations.get(localeID);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.sunrise.core.data.LocalizableAttribute#getValues()
     */
    @Override
    public List<Double> getValues() {
        return new ArrayList<Double>(localizations.values());
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

    /*
     * (non-Javadoc)
     *
     * @see org.sunrise.core.data.LocalizableAttribute#getMappedValues()
     */
    @Override
    public Map<String, Double> getMappedValues() {
        return localizations;
    }
}
