package com.spring.professional.exam.aop.springprofessionalaop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class WithinBeanAspect {

	@Pointcut("within(com.spring..Spring*)")
	public void allMethodFromSpringBean2() {
		
	}
	
	@Before(value = "allMethodFromSpringBean2()")
	public void printAllMethods(final JoinPoint joinPoint) {
		System.err.println("SpringWithinBeanAspect calling "+ joinPoint.getSignature().getName());
	}
	
	
	@Before(value = "within(*..*)")
	public void intercept (final JoinPoint joinPoint) {
		System.err.println("SpringWithinBeanAspect calling "+ joinPoint.getSignature().getName());
	}
}
