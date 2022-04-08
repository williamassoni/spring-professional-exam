package com.spring.professional.exam.core.propertysource.inner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
public class Runner implements CommandLineRunner{
	
	@Configuration
	@PropertySources({
		@PropertySource(value = "classpath:local.properties"),
		@PropertySource(value = "classpath:local-${spring.profiles.active}.properties", ignoreResourceNotFound = true)	
	})
	static class LocalConfiguration {
		
		@Bean
		public String localconfig(@Value("${local.anyvalue}") final String test) {
			return test;
		}
	}
	
	@Autowired
	private ApplicationContext context;
	
	public static void main(String[] args) {
		SpringApplication.run(Runner.class, args);
		/*AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		
		context.getEnvironment().setActiveProfiles("test");
		context.register(LocalConfiguration.class);
		context.refresh();
		
		context.getBean("localconfig");*/
		
	}

	@Override
	public void run(String... args) throws Exception {
		context.getBean("localconfig");
	}
}
