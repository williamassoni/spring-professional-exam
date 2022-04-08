package com.spring.professional.exam.core.lifecycle;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.spring.professional.exam.core.lifecycle.beans.BeanConfiguration;
import com.spring.professional.exam.core.lifecycle.beans.PrototypeBean;
import com.spring.professional.exam.core.lifecycle.postprocessors.PostProcessorsConfiguration;

public class SpringBootRunner {
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

		context.register(BeanConfiguration.class);
		context.register(PostProcessorsConfiguration.class);

		context.refresh();
		
		context.registerShutdownHook();
		
		context.getBean(PrototypeBean.class);
	}
	
}
