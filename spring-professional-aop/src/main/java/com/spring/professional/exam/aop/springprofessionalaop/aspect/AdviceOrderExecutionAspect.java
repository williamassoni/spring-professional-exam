package com.spring.professional.exam.aop.springprofessionalaop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.SneakyThrows;

@Component
@Aspect
public class AdviceOrderExecutionAspect {

	@Pointcut("execution (* *..AdviceExecutionOrderBean.*(..))")
	public void executionOrderPointCut() {
		
	}

	@Before(value = "executionOrderPointCut()")
	public void beforeAdvice() {
		System.err.println("beforeAdvice");	
	}
	
	@SneakyThrows
	@Around(value = "executionOrderPointCut()")
	public void arroundAdvice(final ProceedingJoinPoint joinpoint) {
		joinpoint.proceed();
	}
	
	@After(value = "executionOrderPointCut()")
	public void afterAdvice() {
		System.err.println("afterAdvice");
	}
	
	@AfterThrowing(value = "executionOrderPointCut()")
	public void afterThrowingAdvice() {
		System.err.println("afterThrowingAdvice");	
	}
	
	@AfterReturning(value = "executionOrderPointCut()")
	public void afterReturningAdvice() {
		System.err.println("afterReturningAdvice");
	}
}
