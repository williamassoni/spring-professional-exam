package com.spring.professional.exam.aop.springprofessionalaop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AnnotationAspect {

	@Before(value = "@annotation(com.spring.professional.exam.aop.springprofessionalaop.beans.CustomAnnotation)")
	public void advice(final JoinPoint joinPoint) {
		System.err.println("@annotation "+ joinPoint.getSignature().getName());
	}
	
	@Before(value = "@args(com.spring.professional.exam.aop.springprofessionalaop.beans.CustomAnnotation)")
	public void advice2(final JoinPoint joinPoint) {
		System.err.println("@args "+ joinPoint.getSignature().getName());
	}
	
	@Before(value = "@within(org.springframework.stereotype.Service)")
	public void advice3(final JoinPoint joinPoint) {
		System.err.println("@within "+ joinPoint.getSignature().getName());
	}
	
	@Before(value = "@within(com.spring.professional.exam.aop.springprofessionalaop.beans.CustomAnnotation)")
	public void adviceAgainstSuportAnnottation(final JoinPoint joinPoint) {
		System.err.println("@within "+ joinPoint.getSignature().getName());	
	}
	
	@Before(value = "@target(org.springframework.stereotype.Service)")
	public void advice4(final JoinPoint joinPoint) {
		System.err.println("@target "+ joinPoint.getSignature().getName());
	}
}
