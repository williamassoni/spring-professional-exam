package com.spring.professional.exam.core.lifecycle.beans;

import javax.annotation.PreDestroy;

public class PrototypeBean {

	@PreDestroy
	public void destroyjsr() {
		System.err.println("PrototypeBean calling @PreDestroy");
	}
	
	public void shutdown() {
		System.err.println("PrototypeBean calling destroy");
	}
}
