/**
 *
 */
package org.nww.core.data;

import java.util.List;

/**
 * @author MGA This is the default implementation of the ObjectExtension
 * interface.
 *
 */
public class DefaultObjectExtension implements ObjectExtension {

    private String name;
    private String localeID;
    private String stringValue;
    private Double doubleValue;
    private Integer integerValue;
    private List<String> stringValues;
    private List<Double> doubleValues;
    private List<Integer> integerValues;


    public DefaultObjectExtension() {
    	super();
    }
    
    public DefaultObjectExtension(String name) {
    	super();
    	
    	this.name = name;
	}
    
    public DefaultObjectExtension(String name, String localeID) {
    	super();
    	
    	this.name = name;
    	this.localeID = localeID;
    }
    
    /* (non-Javadoc)
     * @see org.sunrise.core.data.ObjectExtension#getName()
     */
    @Override
    public String getName() {
        return this.name;
    }

    /* (non-Javadoc)
     * @see org.sunrise.core.data.ObjectExtension#getLocaleID()
     */
    @Override
    public String getLocaleID() {
        return localeID;
    }

    /* (non-Javadoc)
     * @see org.sunrise.core.data.ObjectExtension#getString()
     */
    @Override
    public String getString() {
        return stringValue;
    }

    /* (non-Javadoc)
     * @see org.sunrise.core.data.ObjectExtension#getDouble()
     */
    @Override
    public Double getDouble() {
        return doubleValue;
    }

    /* (non-Javadoc)
     * @see org.sunrise.core.data.ObjectExtension#getInteger()
     */
    @Override
    public Integer getInteger() {
        return integerValue;
    }

    /* (non-Javadoc)
     * @see org.sunrise.core.data.ObjectExtension#setName(java.lang.String)
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /* (non-Javadoc)
     * @see org.sunrise.core.data.ObjectExtension#setLocaleID(java.lang.String)
     */
    @Override
    public void setLocaleID(String localeID) {
        this.localeID = localeID;
    }

    /* (non-Javadoc)
     * @see org.sunrise.core.data.ObjectExtension#setString(java.lang.String)
     */
    @Override
    public void setString(String value) {
        this.stringValue = value;
    }

    /* (non-Javadoc)
     * @see org.sunrise.core.data.ObjectExtension#setDouble(java.lang.Double)
     */
    @Override
    public void setDouble(Double value) {
        this.doubleValue = value;
    }

    /* (non-Javadoc)
     * @see org.sunrise.core.data.ObjectExtension#setInteger(java.lang.Integer)
     */
    @Override
    public void setInteger(Integer value) {
        this.integerValue = value;
    }

    /*
     * (non-Javadoc)
     * @see org.nww.core.data.ObjectExtension#getMultipleString()
     */
	@Override
	public List<String> getMultipleString() {
		return this.stringValues;
	}

	/*
	 * (non-Javadoc)
	 * @see org.nww.core.data.ObjectExtension#setMultipleString(java.util.List)
	 */
	@Override
	public void setMultipleString(List<String> data) {
		this.stringValues = data;
	}

	/*
	 * (non-Javadoc)
	 * @see org.nww.core.data.ObjectExtension#getMultipleDouble()
	 */
	@Override
	public List<Double> getMultipleDouble() {
		return this.doubleValues;
	}

	/*
	 * (non-Javadoc)
	 * @see org.nww.core.data.ObjectExtension#setMultipleDouble(java.util.List)
	 */
	@Override
	public void setMultipleDouble(List<Double> data) {
		this.doubleValues = data;
	}

	/*
	 * (non-Javadoc)
	 * @see org.nww.core.data.ObjectExtension#getMultipleInteger()
	 */
	@Override
	public List<Integer> getMultipleInteger() {
		return this.integerValues;
	}

	/*
	 * (non-Javadoc)
	 * @see org.nww.core.data.ObjectExtension#setMultipleInteger(java.util.List)
	 */
	@Override
	public void setMultipleInteger(List<Integer> data) {
		this.integerValues = data;
	}
}
