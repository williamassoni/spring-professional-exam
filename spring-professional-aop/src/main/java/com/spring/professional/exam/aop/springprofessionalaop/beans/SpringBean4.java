package com.spring.professional.exam.aop.springprofessionalaop.beans;

import org.springframework.stereotype.Service;

import com.spring.professional.exam.aop.springprofessionalaop.obj.CustomObject;

@Service
public class SpringBean4 {

	@CustomAnnotation
	public void doSomething() {
		
	}
	
	public void doSomethingElse(final CustomObject object) {
		
	}
}
