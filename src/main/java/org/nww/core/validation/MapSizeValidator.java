/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nww.core.validation;

import java.util.Map;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.util.StringUtils;

/**
 *
 * @author MGA
 */
public class MapSizeValidator implements ConstraintValidator<MapSize, Map<String, String>> {

    private MapSize annotation;

    @Override
    public void initialize(MapSize constraintAnnotation) {
        annotation = constraintAnnotation;
    }

    @Override
    public boolean isValid(Map<String, String> value, ConstraintValidatorContext context) {
        if (null == value) {
            return false;
        }

        for (Map.Entry<String, String> entrySet : value.entrySet()) {
            String key = entrySet.getKey();
            String input = entrySet.getValue();

            if (StringUtils.isEmpty(key) || null == input) {
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
