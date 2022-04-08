package com.spring.professional.exam.aop.springprofessionalaop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.spring.professional.exam.aop.springprofessionalaop.beans.SpringBean2;

@Component
@Aspect
public class ExecutionAspect {

	@Pointcut("execution(!String com..*.print*(String,..) )")
	public void allMethodOfSpringBean1() {
		
	}
	
	@Pointcut("execution(* com..Spring**.*   (..) throws RuntimeException)")
	public void allMethodFromSpring1WhichThrowError() {
		
	}
	
	@Before(value = "allMethodOfSpringBean1()")
	public void printAllMethods(final JoinPoint joinPoint) {
		System.err.println("SpringBeanAspect calling "+ joinPoint.getSignature().getName());
	}
	
	@Before(value= "allMethodFromSpring1WhichThrowError()")
	public void printMethodWithRunTime(final JoinPoint joinPoint) {
		System.err.println("SpringBeanAspect calling "+ joinPoint.getSignature().getName());
	}
	

	@Before(value= "execution(* *.set*(..)) || execution(* *.*.get*())")
	public void printGetAndSet(final JoinPoint joinPoint) {
		System.err.println("set/get --> "+ joinPoint.getSignature().getName());
	}
	
	@Before(value = "execution(* com..SpringBean2.* ())")
	public void printAllMethods2(final JoinPoint joinPoint) {
		System.err.println("calling protected calling "+ joinPoint.getSignature().getName());
	}
	
	//@Before(value = "within(com.spring.professional.exam.aop.springprofessionalaop.beans.SpringBean2)")
	@Before(value = "execution(* com..get*())")
	public void printAllMethodsDefault(final JoinPoint joinPoint) {
		java.lang.reflect.Modifier.toString(joinPoint.getSignature().getModifiers());
		System.err.println("calling protected calling "+ joinPoint.getSignature().getModifiers());
	}
	/*
	@Around(value = "execution(* *.set*(..)) && args(sringbean2)")
	public void fullOfHate(final SpringBean2 sringbean2, final ProceedingJoinPoint join) throws Throwable {
		join.proceed();
	}*/
}
