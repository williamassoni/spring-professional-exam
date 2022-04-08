package com.spring.professional.exam.core.qualifier;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SampleBean4{

	@Qualifier("sampleImpl2")
	@Autowired
	SampleInterface it;

	@PostConstruct
	public void run() {
		System.err.println();
	}
	
	
}
