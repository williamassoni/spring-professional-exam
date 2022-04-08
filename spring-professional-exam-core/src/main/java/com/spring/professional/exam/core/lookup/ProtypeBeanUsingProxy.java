package com.spring.professional.exam.core.lookup;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

//@Component
@Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ProtypeBeanUsingProxy {

	public void one() {
		System.err.println("call one");
	}
	
	protected void two() {
		System.err.println("call two");
	}
	
	void three() {
		System.err.println("call three");
	}
	
}
