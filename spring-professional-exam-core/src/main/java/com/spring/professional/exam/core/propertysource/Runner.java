package com.spring.professional.exam.core.propertysource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

public class Runner {

	@Configuration
	@PropertySource(value = "classpath:local.properties")
	@PropertySource(value = "classpath:local2.properties")
	static class Local {
		
		@Value("${local.anyvalue}")
		@Bean
		public String commonValue(final String local) {
			return local;
		}
		
		@Value("${local.overwrite}")
		@Bean
		public String commonValue2(final String local) {
			return local;
		}
	}
	
	public static void main(String[] args) {
		final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		
		context.register(Local.class);
		
		context.refresh();
		
		context.getBean("commonValue");
		context.getBean("commonValue2");
		
	}
}
