package com.spring.professional.exam.core.environment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

public class Runner {

	@Configuration
	@PropertySource("classpath:local.properties")
	static class LocalConfiguration {
	
		@Bean
		public String getBeanA(@Value("${test:nothing}") final String test) {
			return test;
		}
	}
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(LocalConfiguration.class);
		
		context.registerShutdownHook();
		
		
		System.err.println(context.getBean(String.class));
	}
}
