package com.spring.professional.exam.core.lookup;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(scopeName = "prototype")
public class ProtoypeBean {
	
	public ProtoypeBean() {
		System.err.println("new instance here...");
	}

}
