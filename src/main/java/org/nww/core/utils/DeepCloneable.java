package org.nww.core.utils;

/**
 * Interface to mark classes that are able to clone themselves deeply.
 *
 * @author MGA
 */
public interface DeepCloneable {

    /**
     * Create a deep copy of its one.
     *
     * @return A new independed clone object of itself.
     */
    public <T> T cloneDeep();
}
