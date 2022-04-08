package com.spring.professional.exam.core.factory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

public class Runner {
	
	@Configuration
	@PropertySource("application.properties")
	static class LocalConfiguration {

		@Bean
		public LocalBeanFactory strange(@Value("${spel.properties}") final String weirdStuff) {
			return new LocalBeanFactory(weirdStuff);
		}
		
	}
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(LocalConfiguration.class);
		
		context.registerShutdownHook();
		
		context.getBean(LocalDTO.class);
		context.getBean("strange");
	}
}
