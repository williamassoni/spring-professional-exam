package com.spring.professional.exam.core.lazyinitialization.beans;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Lazy;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Lazy(value = true)
public @interface CustomAnnotation {

}
