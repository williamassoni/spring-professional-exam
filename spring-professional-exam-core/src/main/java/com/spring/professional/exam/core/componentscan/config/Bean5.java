package com.spring.professional.exam.core.componentscan.config;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

interface IBean5 {
	
}

@Component
@Scope(scopeName =  "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Bean5 implements IBean5{
	
}
