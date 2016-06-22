/**
 *
 */
package org.nww.core.data.form;

import org.nww.core.data.PersistentObject;

/**
 * The form data mapper interface provides to basic mapping methods needed for
 * converting form data to persistent objects and vice versa.
 *
 * @author MGA
 *
 */
public interface FormDataMapper<T extends Form, K extends PersistentObject> {

    /**
     * Maps the passed persistent objects data into the form object.
     *
     * @param obj the persistent object
     * @return the form data
     */
    public T mapToForm(K obj);

    /**
     * Maps the passed form object into the persistent object.
     *
     * @param form the form object
     * @return the persistent object
     */
    public K mapToPersistentObject(T form);

    /**
     * Maps the passed form object into the existing persistent object and
     * returns it.
     *
     * @param form the form object
     * @param existingObj the persistent object to be updated
     * @return the persistent object
     */
    public K mapToExistingPersistentObject(T form, K existingObj);
}
