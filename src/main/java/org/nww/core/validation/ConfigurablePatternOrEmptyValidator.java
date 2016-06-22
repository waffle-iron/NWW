/*******************************************************************************
 * Copyright 2014-2015 MGA, garth-3d
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package org.nww.core.validation;

import java.util.Properties;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.StringUtils;

/**
 * 
 *
 * @author MGA
 *
 */
public class ConfigurablePatternOrEmptyValidator implements ConstraintValidator<ConfigurablePatternOrEmpty, String> {

    @Resource(name = "propertyHelper")
    private Properties properties;
    private ConfigurablePatternOrEmpty annotation;
    private Pattern pattern;


    @Override
    public void initialize(ConfigurablePatternOrEmpty constraintAnnotation) {
        this.annotation = constraintAnnotation;
        this.pattern = Pattern.compile(properties.getProperty(this.annotation.property()));
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return StringUtils.isEmpty(value) || this.pattern.matcher(value).matches();
    }
}
