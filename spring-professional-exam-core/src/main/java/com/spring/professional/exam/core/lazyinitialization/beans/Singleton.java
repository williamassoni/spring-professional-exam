package com.spring.professional.exam.core.lazyinitialization.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class Singleton {

	@Autowired
	@Lazy
	private Singleton2 singleton2;
	
	public Singleton() {
		System.err.println("Initializing Singleton bean");
	}
	
	public void what() {
		System.err.println("");
	}
}
