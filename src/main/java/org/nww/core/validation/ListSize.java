/**
 * 
 */
package org.nww.core.validation;

import java.lang.annotation.*;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Marks lists of strings to be validated for their single element min and max length. 
 * @author mga
 *
 */
@Documented
@Constraint(validatedBy = ListSizeValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ListSize {
	int min() default 10;

    int max() default 100;

    String message() default "All elements must be between {min} and {max} characters long.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
