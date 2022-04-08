package com.spring.professional.exam.core.lazyinitialization.beans;

import org.springframework.stereotype.Component;

@Component
@CustomAnnotation
public class BeanCustomLazy {

	public BeanCustomLazy() {
		System.err.println("Initializing BeanCustomLazy bean");
	}
}
