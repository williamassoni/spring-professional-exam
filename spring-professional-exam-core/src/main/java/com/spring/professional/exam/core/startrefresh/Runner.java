package com.spring.professional.exam.core.startrefresh;

import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
class Fallback {
	
	@EventListener
	public void hook(final ApplicationStartingEvent start) {
		System.err.println("Doing start");
	}
	
	@EventListener
	public void hook(final ContextRefreshedEvent start) {
		System.err.println("Doing refresh");
	}
	
}
public class Runner {
	
	@Configuration
	@ComponentScan
	static class LocalConfiguration {
	
	}
	
	@Configuration
	@ComponentScan
	static class LocalConfiguration2 {
	
	}
	
	public static void main(String[] args) {
		final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(LocalConfiguration.class);
		context.registerShutdownHook();
		
		context.refresh();
		
		context.getBeansOfType(String.class);
	}
}
