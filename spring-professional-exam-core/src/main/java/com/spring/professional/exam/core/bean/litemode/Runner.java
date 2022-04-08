package com.spring.professional.exam.core.bean.litemode;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Runner {
	
	public static void main(String[] args) {
		final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		
		context.scan("com.spring.professional.exam.core.bean.litemode");
		context.refresh();
		context.registerShutdownHook();
		
		context.getBeansOfType(BeanFactory.LocalBean.class);
	}
}
