package com.spring.professional.exam.core.interfaces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class Runner {

	@Autowired
	private ApplicationContext context;
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Runner.class);
	
		context.registerShutdownHook();
		
		context.getBean(BaseInterface.class);
	}

}
