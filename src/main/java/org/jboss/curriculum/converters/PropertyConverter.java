/**
 * A simple SLSB EJB. The EJB has a Local interface for the JSF Backing bean to use.
 *
 * We are using this EJB directly with a JSF via EL, to show this is now possible.
 * This removes the need for the traditional "backing-bean" between the JSF and the EJB,
 * less boiler plate code needed.
 *
 * While this is possible, the down-side is that "Action" methods ("convert" in this case)
 * should return a String for JSF navigation, and the bean logic may be different to a
 * straight Stateless bean that would otherwise be built as a reusable "Component".
 *
 * Careful design of your EJBs should mitigate this issue and ultimately reduce the code
 * needed in your applications.
 *
 * @author Bruce Wolfe May 2012.
 */

package org.jboss.curriculum.converters;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Stateless
@Named("converter")
public class PropertyConverter implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    // These two properties are used by the JSF only
	private String property = "";
	private String convertedProperty = "";

	private String convert(String prop) {

	    if (prop == null || prop.isEmpty()) {
	        throw new RuntimeException("Null or empty String can't be converted");
	    }

		return prop + " CONVERTED";
	}

	/**
	 * Used as the JSF action method
	 */
	public void convert() {
		try {
			this.convertedProperty = this.convert(this.property);
		} catch (RuntimeException e) {
			this.convertedProperty = "Error - Unable to convert";
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage()));
		}
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String temperature) {
		this.property = temperature;
	}

	public String getConvertedProperty() {
		return convertedProperty;
	}

}
