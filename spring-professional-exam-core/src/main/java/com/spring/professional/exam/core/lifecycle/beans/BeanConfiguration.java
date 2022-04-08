package com.spring.professional.exam.core.lifecycle.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class BeanConfiguration {

	@Bean(initMethod = "initMethod")
	public PotatoBean getPotatoBean() {
		return new PotatoBean();
	}
	
	@Bean
	@Scope(scopeName = "prototype")
	public PrototypeBean getPrototype() {
		return new PrototypeBean();
	}
	
	
}
