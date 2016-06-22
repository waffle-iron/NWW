/**
 * 
 */
package org.nww.core.validation;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

/**
 * A validator implementation for the {@link ListSize} annotation.
 * @author mga
 *
 */
@Component
public class ListSizeValidator implements ConstraintValidator<ListSize, List<String>> {

	private ListSize annotation;
	
	/*
	 * (non-Javadoc)
	 * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
	 */
	@Override
	public void initialize(ListSize constraintAnnotation) {
		this.annotation = constraintAnnotation;
	}

	/*
	 * (non-Javadoc)
	 * @see javax.validation.ConstraintValidator#isValid(java.lang.Object, javax.validation.ConstraintValidatorContext)
	 */
	@Override
	public boolean isValid(List<String> value, ConstraintValidatorContext context) {
		if (null == value) {
            return false;
        }

		for (String input : value) {
			if (null == input) {
				return false;
			}
			
			// check min size
			if (input.length() < annotation.min()) {
				return false;
			}
			
			// check max size
			if (input.length() > annotation.max()) {
				return false;
			}
			
		}

        return true;
	}
}
