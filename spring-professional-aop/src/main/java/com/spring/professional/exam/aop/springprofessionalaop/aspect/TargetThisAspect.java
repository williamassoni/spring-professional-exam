package com.spring.professional.exam.aop.springprofessionalaop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TargetThisAspect {

	@Before(value = "target(com.spring.professional.exam.aop.springprofessionalaop.beans.Food)")
	public void printAllMethods(final JoinPoint joinPoint) {
		System.err.println("matching by target "+ joinPoint.getSignature().getName());
	}
	
	@Before(value = "this(com.spring.professional.exam.aop.springprofessionalaop.beans.Food)")
	public void printAllMethods2(final JoinPoint joinPoint) {
		System.err.println("matching by this Food"+ joinPoint.getSignature().getName());
	}

	//will not be gotten since the proxy is not an instance of Salad but is an instance of Food
	@Before(value = "this(com.spring.professional.exam.aop.springprofessionalaop.beans.Salad)")
	public void printAllMethods3(final JoinPoint joinPoint) {
		System.err.println("matching by this Salad"+ joinPoint.getSignature().getName());
	}
	
	@Before(value = "target(com.spring.professional.exam.aop.springprofessionalaop.beans.Salad)")
	public void printAllMethods4(final JoinPoint joinPoint) {
		System.err.println("matching by target "+ joinPoint.getSignature().getName());
	}
}
