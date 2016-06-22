package org.nww.core.data.filter;

import java.util.List;
import java.util.Set;

/**
 *
 * @author MGA
 * @param <T> the element type the filter applies to.
 * @param <K> the element type of the context that is needed to apply this
 * filter
 */
public interface Filter<T extends Filterable, K> {

    /**
     * Sets the set of criteria the filter tries to apply.
     *
     * @param fcs set of criteria
     */
    public void setCriterias(Set<FilterCriteria<T, K>> fcs);

    /**
     * Get the set of criteria the filter tries to apply.
     *
     * @return set of criteria
     */
    public Set<FilterCriteria<T, K>> getCriterias();

    /**
     * Add a new criteria to the filters criteria set.
     *
     * @param fc the new criteria
     */
    public void addCriteria(FilterCriteria<T, K> fc);

    /**
     * Removes a criteria from the filters criteria set.
     *
     * @param fc the criteria to remove
     */
    public void removeCriteria(FilterCriteria<T, K> fc);

    /**
     * Applies all criteria to the passed object until every single criteria
     * was
     * matched or until one fails. There is no guarantee that: - every criteria
     * will be checked if one fails - the order of the criteria is always the
     * same
     * @param toCheck the filterable object to check
     * @param context the context information needed to check the criteria
     * @return true if the filter applies
     */
    public boolean apply(T toCheck, K context);

    /**
     * Applies all criteria to all passed objects and returns a new list
     * containing only the elements matching the filter criteria.
     *
     * @param listToCheck the list of objects to be filtered
     * @param context the context information needed to check the criteria
     * @return list of all objects matching the filter criteria
     */
    public List<T> applyAll(List<T> listToCheck, K context);
}
