package org.nww.core.validation;

import java.util.Properties;
import java.util.regex.Pattern;
import javax.annotation.Resource;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author MGA
 */
public class ConfigurablePatternValidator implements ConstraintValidator<ConfigurablePattern, String> {

    @Resource(name = "propertyHelper")
    private Properties properties;
    private ConfigurablePattern annotation;
    private Pattern pattern;


    @Override
    public void initialize(ConfigurablePattern constraintAnnotation) {
        this.annotation = constraintAnnotation;
        this.pattern = Pattern.compile(properties.getProperty(this.annotation.property()));
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return this.pattern.matcher(value).matches();
    }
}
