/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nww.core.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 *
 * @author MGA
 */
public class DefaultLocaleHelper implements LocaleHelper {

    private static final String SPLITTER = "_";

    /**
     * Returns the locale id string used inside the database to identify
     * localized objects.
     *
     * @param l The locale to determine the locale id from.
     * @return The locale id formatted like [language code]-[country code]. e.g.
     * en-US
     */
    @Override
    public String getLocaleID(Locale l) {
        return l.getLanguage() + SPLITTER + l.getCountry();
    }

    /**
     * Returns the locale for the passed locale id.
     *
     * @param id
     * @return
     */
    @Override
    public Locale getLocaleByID(String id) {
        Locale loc = null;

        if (!StringUtils.isEmpty(id)) {
            String[] parts = id.split(SPLITTER);

            if (parts.length == 2) {
                loc = new Locale(parts[0], parts[1]);
            }
        }

        return loc;
    }

    /**
     * Returns a list of all locales created from the passed locale ids.
     *
     * @param ids array of locale ids to create locale objects from
     * @return list of locales
     */
    @Override
    public List<Locale> getLocalesByIDs(String... ids) {
        return Arrays.stream(ids).map(id -> getLocaleByID(id)).collect(Collectors.toList());
    }

    /**
     * Returns the locale of the current request object.
     *
     * @return the locale object
     */
    @Override
    public Locale getCurrentLocale() {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest req = sra.getRequest();

        return req.getLocale();
    }

    /**
     * Returns the id of the locale of the current request object.
     *
     * @return the locale id of the current locale object
     */
    @Override
    public String getCurrentLocaleID() {
        return getLocaleID(getCurrentLocale());
    }
}
