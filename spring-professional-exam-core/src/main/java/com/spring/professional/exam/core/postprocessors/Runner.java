package com.spring.professional.exam.core.postprocessors;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.spring.professional.exam.core.postprocessors.config.CustomBeanFactoryPostProcessor;
import com.spring.professional.exam.core.postprocessors.config.CustomBeanPostProcessor;

public class Runner {
	
	@org.springframework.context.annotation.Configuration
	@ComponentScan
	static class Configuration {
		
		@Bean
		public static CustomBeanFactoryPostProcessor getCustomBeanFactoryPostProcessor() {
			return new CustomBeanFactoryPostProcessor();
		}
		
		@Bean
		public static CustomBeanPostProcessor getCustomBeanPostProcessor() {
			return new CustomBeanPostProcessor();
		}
		
	}
	
	public static void main(String[] args) {
		final AnnotationConfigApplicationContext config = new AnnotationConfigApplicationContext(Configuration.class);
	
		config.registerShutdownHook();
	}
}
