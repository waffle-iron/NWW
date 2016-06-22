package org.nww.core.data.filter;

/**
 *
 * @author MGA
 * @param <T> the object type that this criteria applies to
 * @param <K> the type of the context information needed to match the criteria
 * against
 */
public interface FilterCriteria<T, K> {

    /**
     * Checks the passed filterable object against its criteria.
     *
     * @param toCheck the object to be checked
     * @param context the context information needed to match the criteria
     * against
     * @return true if the object matches the criteria
     */
    public boolean match(T toCheck, K context);
}
