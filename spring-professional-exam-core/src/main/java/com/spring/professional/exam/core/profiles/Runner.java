package com.spring.professional.exam.core.profiles;

import java.util.Map;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

public class Runner {
	
	@Configuration
	@ComponentScan
	static class LocalConfiguration {
		
		@Bean
		@Profile({"dev", "prod"})
		public String printA() {
			return "A";
		}
		
		@Bean
		@Profile("dev")
		public String printB() {
			return "B";
		}
		
		@Bean
		@Profile({"!prod"})
		public String printC() {
			return "C";
		}
		
		@Bean
		@Profile("dev & prod")
		public String printD() {
			return "D";
		}
		
		@Bean
		@Profile("test")
		public String printE() {
			return "E";
		}
		
		@Bean
		@Profile("test")
		public String printF() {
			printE();
			return "F";
		}
		
		@Profile({"p1", "!p2"})
		@Bean
		public String printG() {
			return "G has been injected";
		}
	}
		
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.getEnvironment().setActiveProfiles("p1", "p2");
		context.register(LocalConfiguration.class);
		
		context.refresh();
		context.registerShutdownHook();
		
		Map<String, String> list = context.getBeansOfType(String.class);
		list.values().forEach(f -> System.err.println(f));
		
		
		//context.getBean(ProfileBean.class);
	}
}
