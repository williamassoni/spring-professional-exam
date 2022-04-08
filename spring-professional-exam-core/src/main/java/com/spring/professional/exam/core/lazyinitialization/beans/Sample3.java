package com.spring.professional.exam.core.lazyinitialization.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class Sample3 {

	@Autowired
	@Lazy
	private Prototype prototype;
	
	@Bean
	public String teste() {
		return "";
	}
}
