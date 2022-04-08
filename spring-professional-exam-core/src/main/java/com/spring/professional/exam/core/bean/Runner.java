package com.spring.professional.exam.core.bean;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

public class Runner {
	private static final Logger logger = Logger.getLogger("Runner");
	
	@Configuration
	static class Local {

		@Bean(name  = {"pick-up-1","pick-up-2","pick-up-3"})
		public String getBean1() {
			return "OK";
		}
		
		@Bean(autowireCandidate = false, name = "non_autowired")
		public String getBean2() {
			return "OK2";
		}
		
		@Bean
		public String getBean3(final List<String> temps) {
			if(List.of(temps).contains("OK2")) {
				logger.warning("OK2 should not be here...");
			}
			return "OK";
		}
	}
	
	
	public static void main(String[] args) {
		final AnnotationConfigApplicationContext conf = new AnnotationConfigApplicationContext(Local.class);

		conf.getAliases("pick-up-1");
	}
}
