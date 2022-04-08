package com.spring.professional.exam.core.lazyinitialization.beans;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class SingletonWithPrototype {

	final Prototype type;
	
	public SingletonWithPrototype(Prototype type) {
		this.type = type;
		System.err.println("Initializing SingletonWithPrototype bean");
	}
}
