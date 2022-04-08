package com.spring.professional.exam.core.autowired.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Bean4 {

	/*
	public Bean4(final @Qualifier("bean1") GenericBean generic, final GenericBeanExtension beanextension3) {
		generic.doSomething();
		beanextension3.doSomethingElse();
	}*/

	public Bean4(@Autowired @Qualifier("bean1") GenericBean generic, final GenericBeanExtension beanextension3) {
		generic.doSomething();
		beanextension3.doSomethingElse();
	}
}
