package com.spring.professional.exam.core.autowired.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Bean3 {

	@Autowired(required = false)
	public Bean3(final Bean1 bean1) {
	}

	@Autowired(required = false)
	Bean3(final Bean1 bean1, final Bean2 bean2) {
	}
	
}
