package com.spring.professional.exam.aop.springprofessionalaop.beans;

import org.springframework.stereotype.Component;

@Component
public class AdviceExecutionOrderBean {

	public void execute() {
		System.err.println("calling execute");
	}
}
