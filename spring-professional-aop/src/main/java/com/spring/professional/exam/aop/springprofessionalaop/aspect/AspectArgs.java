package com.spring.professional.exam.aop.springprofessionalaop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.spring.professional.exam.aop.springprofessionalaop.beans.SpringBean2;

@Aspect
@Component
public class AspectArgs {

	@Pointcut("execution(* com.spring..SpringBean3.*(..))")
	public void printAllMethods() {
		
	}
	
	@Pointcut("args(springBean2)")
	public void printInjection(final SpringBean2 springBean2) {
		
	}
	
	//@Pointcut("args(Long,Long)")
	//@Pointcut("args(Long,..)")
	//@Pointcut("args(..)")
	@Pointcut("args(*,*)")
	public void printParamFromCalculate() {
		
	}
	
	@Before(value = "printAllMethods() && printInjection(springBean2)")
	public void advice(final JoinPoint joinPoint, final SpringBean2 springBean2) {
		System.err.println("SpringAspectArgs injectionpoint "+ joinPoint.getSignature().getName());
	}
	
	@Before(value = "printAllMethods() && printParamFromCalculate()")
	public void advice2(final JoinPoint joinPoint) {
		System.err.println("SpringAspectArgs injectionpoint "+ joinPoint.getSignature().getName());
	}
}
