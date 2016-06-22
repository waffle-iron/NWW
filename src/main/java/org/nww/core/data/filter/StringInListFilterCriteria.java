package org.nww.core.data.filter;

import java.util.List;

/**
 * A simple filter criteria implementation checking the existence of a string
 * inside a list of strings. Does only match if the list contains a totally
 * equal string like the context one.
 *
 * @author MGA
 */
public class StringInListFilterCriteria implements FilterCriteria<List<String>, String> {

    /**
     * Checks whether the passed list contains the context string.
     *
     * @param toCheck list of string where to find the context in
     * @param context the context string to be found
     * @return true if the context is found inside the list of strings
     */
    @Override
    public boolean match(List<String> toCheck, String context) {
        if (toCheck.isEmpty()) {
            return true;
        }

        return toCheck.contains(context);
    }

}
