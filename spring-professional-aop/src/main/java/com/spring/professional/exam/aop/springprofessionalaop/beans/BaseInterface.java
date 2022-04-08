package com.spring.professional.exam.aop.springprofessionalaop.beans;

@CustomAnnotation
public interface BaseInterface {

	@CustomAnnotation
	public default String doSomething() {
		return "TTT";
	}
	
	@CustomAnnotation
	public String someThingElse();
}
