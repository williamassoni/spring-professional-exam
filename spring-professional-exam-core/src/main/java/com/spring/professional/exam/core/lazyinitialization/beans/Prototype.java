package com.spring.professional.exam.core.lazyinitialization.beans;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "prototype")
public class Prototype {

	public Prototype() {
		System.err.println("Initializing Prototype bean");
	}
}
