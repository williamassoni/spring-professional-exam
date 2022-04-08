package com.spring.professional.exam.core.jsr.priority;

import java.util.List;

import javax.annotation.Priority;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

interface Something {
	
}

@Component
//@Order(2)
@Priority(1)
class Potato implements Something {
	
}


@Component
//@Priority(1)
class Meat implements Something {
	
}

public class Runner {
	
	@Configuration
	@ComponentScan
	static class Config {
		
		@Bean
		public String beanE(final List<Something> beans) {
			return "E";
		}
	}
	
	public static void main(String[] args) {
		final AnnotationConfigApplicationContext config = new AnnotationConfigApplicationContext(Config.class);

		config.registerShutdownHook();
		
		config.getBeansOfType(Something.class);
	}
}
