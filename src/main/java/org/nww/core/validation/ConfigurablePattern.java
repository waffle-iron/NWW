package org.nww.core.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 *
 * @author MGA
 */
@Documented
@Constraint(validatedBy = ConfigurablePatternValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ConfigurablePattern {

    String property() default "";

    String message() default "no message";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
