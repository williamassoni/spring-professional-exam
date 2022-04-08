package com.spring.professional.exam.core.configurationproxy;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

public class Runner {

	@Configuration
	//@Component
	static class LocalConfiguration {
		
		@Bean
		public String printA() {
			System.err.println("Calling A");
			return "A";
		}
		
		@Bean
		@Scope("prototype")
		public String printC() {
			System.err.println("Calling C");
			printA();
			return "C";
		}
	}
	
	@Configuration(proxyBeanMethods = false)
	static class LiteBean {
	
		@Bean
		public String printD() {
			System.err.println("Calling D");
			
			return "D";
		}
		
		@Bean
		@Scope("prototype")
		public String printF() {
			printD();
			return "C";
		}
	}
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(LocalConfiguration.class, LiteBean.class);
		
		context.registerShutdownHook();
		
		context.getBean("printC");
		context.getBean("printF");
		context.getBean(LiteBean.class).printD();
	}
}
