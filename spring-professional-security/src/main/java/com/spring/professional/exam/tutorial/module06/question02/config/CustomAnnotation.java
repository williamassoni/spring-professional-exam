package com.spring.professional.exam.tutorial.module06.question02.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.annotation.security.RolesAllowed;

import org.springframework.security.access.annotation.Secured;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@RolesAllowed(value = {"ROLE_CUSTOMER_READ", "ROLE_SUPER_ADMIN"})
//@PreAuthorize("hasRole('ROLE_CUSTOMER_READ') || hasRole('ROLE_SUPER_ADMIN')")
@Secured({"ROLE_CUSTOMER_READ", "ROLE_SUPER_ADMIN"})
public @interface CustomAnnotation {

}
