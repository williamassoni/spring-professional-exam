package com.spring.professional.exam.core.beanoverwrite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@SpringBootApplication
public class Runner {
	
	@Configuration
	@Import(Conf2.class)
	static class Conf1 {
		
		@Bean
		public String same() {
			return "A";
		}
	}
	
	@Configuration
	static class Conf2 {
		
		@Bean
		public Integer same() {
			return 3;
		}
	}

	
	public static void main(String[] args) {
		SpringApplication.run(Runner.class, args);
		
/*		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Conf1.class);
		context.registerShutdownHook();
		
		context.getBeanFactory().registerSingleton("same", "FF");
		context.getBeansOfType(Integer.class);*/
	}
}
