package com.spring.professional.exam.core.weblifecycle;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Runner implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(Runner.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		context.getBean(InjectionPoint.class);
	}
}
