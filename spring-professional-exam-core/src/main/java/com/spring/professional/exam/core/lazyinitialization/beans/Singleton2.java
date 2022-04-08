package com.spring.professional.exam.core.lazyinitialization.beans;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "prototype")
public class Singleton2 {

	public Singleton2() {
		System.err.println("Initialization of Singleton2");
	}
}
