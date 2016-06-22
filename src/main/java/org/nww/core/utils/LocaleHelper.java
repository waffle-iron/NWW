package org.nww.core.utils;

import java.util.List;
import java.util.Locale;

/**
 * This class provides some util methods for working with locales.
 *
 * @author MGA
 *
 */
public interface LocaleHelper {
    /**
     * Returns the locale id string used inside the database to identify
     * localized objects.
     *
     * @param l The locale to determine the locale id from.
     * @return The locale id formatted like [language code]-[country code]. e.g.
     * en-US
     */
    public String getLocaleID(Locale l);

    /**
     * Returns the locale for the passed locale id.
     *
     * @param id
     * @return
     */
    public Locale getLocaleByID(String id);

    /**
     * Returns a list of all locales created from the passed locale ids.
     *
     * @param ids array of locale ids to create locale objects from
     * @return list of locales
     */
    public List<Locale> getLocalesByIDs(String... ids);

    /**
     * Returns the locale of the current request object.
     *
     * @return the locale object
     */
    public Locale getCurrentLocale();

    /**
     * Returns the id of the locale of the current request object.
     *
     * @return the locale id of the current locale object
     */
    public String getCurrentLocaleID();
}
