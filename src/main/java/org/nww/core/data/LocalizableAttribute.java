package org.nww.core.data;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Localizable attribute allows storing of one attribute with different values
 * per locale id.
 *
 * @author MGA
 *
 */
public interface LocalizableAttribute<T extends Object> {

    /**
     * Set a localized value. Does creating or updating of possibly existing
     * values.
     *
     * @param value The string value.
     * @param localeID The locale id.
     */
    public void setValue(T value, String localeID);

    /**
     * Get the localized value of this attribute.
     *
     * @param localeID
     */
    public T getValue(String localeID);

    /**
     * Get the list of all localized values.
     *
     * @return List of localized values without a guarenteed order.
     */
    public List<T> getValues();

    /**
     * Get a map of localized values mapped to their respective locale id.
     *
     * @return Map of mapped values.
     */
    public Map<String, T> getMappedValues();

    /**
     * Get a set of the locale ids that have values for this attribute.
     *
     * @return
     */
    public Set<String> getLocaleIDs();
}
