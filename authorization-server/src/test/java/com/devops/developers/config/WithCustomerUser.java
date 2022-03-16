package com.devops.developers.config;


import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME) // so that this annotation exist in bytecode
@WithSecurityContext(factory = WithCustomeSecurityContextFactory.class)
public @interface WithCustomerUser {
    String authority();

}
