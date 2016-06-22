/**
 *
 */
package org.nww.core.data;

import java.util.List;

/**
 * Defines the basic methods for object extensions.
 *
 * @author MGA
 *
 */
public interface ObjectExtension {

    /**
     * Get the name.
     *
     * @return The name of the entity.
     */
    public String getName();

    /**
     * Get the locale id.
     *
     * @return The locale id or null if the value is not localized.
     */
    public String getLocaleID();

    /**
     * Get the string value.
     *
     * @return The string value or null if no value is set.
     */
    public String getString();

    /**
     * Get the double value.
     *
     * @return The double value or null if no value is set.
     */
    public Double getDouble();

    /**
     * Get the integer value.
     *
     * @return The integer value or null if no value is set.
     */
    public Integer getInteger();

    /**
     * Set the name of the entity.
     *
     * @param name The name value.
     */
    public void setName(String name);

    /**
     * Set the locale id.
     *
     * @param localeID The identifier string for the entities locale
     * information.
     */
    public void setLocaleID(String localeID);

    /**
     * Set the string value.
     *
     * @param value The string value.
     */
    public void setString(String value);

    /**
     * Set the double value.
     *
     * @param value The double value.
     */
    public void setDouble(Double value);

    /**
     * Set the integer value.
     *
     * @param value The integer value.
     */
    public void setInteger(Integer value);
    
    /**
     * @return list of string values
     */
    public List<String> getMultipleString();
    
    /**
     * Set multiple string values.
     * @param data list of string values
     */
    public void setMultipleString(List<String> data);
    
    /**
     * @return list of double values
     */
    public List<Double> getMultipleDouble();
    
    /**
     * Set multiple double values.
     * @param data list of double values
     */
    public void setMultipleDouble(List<Double> data);
    
    /**
     * @return list of integer values
     */
    public List<Integer> getMultipleInteger();
    
    /**
     * Set multiple integer values.
     * @param data list of integer values
     */
    public void setMultipleInteger(List<Integer> data);
}
