package com.payProject.system.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * <p>目前暂时未使用</p>
 * @author ADMIN
 *
 */
@Target(ElementType.METHOD)             
@Retention(RetentionPolicy.RUNTIME)     
@Documented                             
@Inherited                              
public @interface LoginRequired {
    boolean required() default false;
}
