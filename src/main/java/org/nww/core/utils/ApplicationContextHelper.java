/**
 * *****************************************************************************
 * Copyright 2014-2015 MGA, garth-3d
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 * ****************************************************************************
 */
package org.nww.core.utils;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Provides static access to the application context and to all beans.
 *
 * @see http://blog.jdevelop.eu/?p=154
 * @author MGA
 */
public class ApplicationContextHelper implements ApplicationContextAware {

    private static ApplicationContext ctx = null;

    public static ApplicationContext getApplicationContext() {
        return ctx;
    }

    /**
     * Get a bean by its name.
     *
     * @param name the name of the bean
     * @return the bean or null if nothing was found
     */
    public static Object getBeanByName(String name) {
        return ctx != null ? ctx.getBean(name) : null;
    }

    /**
     * Get a bean by its type.
     *
     * @param clazz the type of the bean
     * @return the bean or null if nothing was found
     */
    public static <T> T getBeanByClass(Class<T> clazz) {
        return ctx != null ? (T)ctx.getBean(clazz) : null;
    }

    /**
     * Get all beans by their type.
     *
     * @param <T> the type to cast the beans to
     * @param clazz the type of the beans
     * @return map of all found beans for the passed type
     */
    public static <T> Map<String, T> getBeansByClass(Class<T> clazz) {
        return ctx != null ? ctx.getBeansOfType(clazz) : Collections.emptyMap();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ctx = applicationContext;
    }
}
