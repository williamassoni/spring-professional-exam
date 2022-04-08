package com.spring.professional.exam.core.trick;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

interface IComputer {
	
}

class Computer implements IComputer {
	
}

class Laptop implements IComputer {
	
}

class CompLap extends Computer {
	
}

@Configuration
class LocalConfiguration {
	
	@Bean
	public IComputer getComputer() {
		return new Computer();
	}
	
	@Bean
	public IComputer getLaptop() {
		return new Laptop();
	}
	
	@Bean
	public IComputer getCompLap() {
		return new CompLap();
	}
}

public class Runner {
	
	public static void main(String[] args) {
		final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(LocalConfiguration.class);
		
		context.registerShutdownHook();
		
		context.getBeansOfType(Computer.class);
	}
	
}
