package com.spring.professional.exam.core.jsr;

import javax.annotation.Resource;
import javax.inject.Named;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.spring.professional.exam.core.jsr.beans.SpringBean1;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class LocalReference {
		
	@Resource
	private String type1;
}

public class Runner {

	@Configuration
	@ComponentScan
	public static class LocalConfiguration {
		
		@Bean
		@Named("potato")
		public String type1() {
			return "OK";
		}
		
		@Bean
		@Named("potato")
		@Primary
		public String type2() {
			return "OK2";
		}
		
		@Bean
		public String type3() {
			return "OK3";
		}
		
		@Bean
		@Named("wtf")
		public LocalReference type4() {
			return new LocalReference();
		}
	}

	public static void main(String[] args) {
		final AnnotationConfigApplicationContext configuration = new AnnotationConfigApplicationContext(LocalConfiguration.class);
		
		configuration.registerShutdownHook();
		
		
		//configuration.getBean(SingletonWithPrototype.class);
		
		System.err.println(configuration.getBean(SpringBean1.class).getShowAllStrings());
		System.err.println(configuration.getBean(SpringBean1.class).getType1());
		System.err.println(configuration.getBean(LocalReference.class).getType1());
	}
}
