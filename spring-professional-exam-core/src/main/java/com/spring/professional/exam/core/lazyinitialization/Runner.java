package com.spring.professional.exam.core.lazyinitialization;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import com.spring.professional.exam.core.lazyinitialization.beans.Pojo;
import com.spring.professional.exam.core.lazyinitialization.beans.Singleton;

public class Runner {

	//@ComponentScan
	@ComponentScan(lazyInit = false)
	static class LocalConfig {
		
		@Bean
		//@Scope(value = "singleton")
		//@Lazy
		@Lazy(false)
		public Pojo getPojo() {
			return new Pojo();
		}
	}
	
	public static void main(String[] args) {
		final AnnotationConfigApplicationContext configuration = new AnnotationConfigApplicationContext(LocalConfig.class);
		
		configuration.registerShutdownHook();
		
		
		//configuration.getBean(SingletonWithPrototype.class);
		
		configuration.getBean(Pojo.class);
		configuration.getBean(Singleton.class).what();
	}
}
