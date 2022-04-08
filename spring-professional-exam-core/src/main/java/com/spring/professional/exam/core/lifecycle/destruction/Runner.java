package com.spring.professional.exam.core.lifecycle.destruction;

import javax.annotation.PreDestroy;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class PostProcess implements BeanPostProcessor {
	
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.err.println("Calling postProcessAfterInitialization");
		
		return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
	}
	
}

class MyBean implements DisposableBean{
	
	@Override
	public void destroy() throws Exception {
		System.err.println("Calling destroy from DisposableBean");
	}
	
	public void customDestroy() {
		System.err.println("Calling custom Destroy");	
	}
	
	@PreDestroy
	public void callingJsrDestroy() {
		System.err.println("Calling @PreDestroy Destroy");	
	}
}

public class Runner {
	
	@org.springframework.context.annotation.Configuration
	static class Configuration {
		
		@Bean(destroyMethod = "customDestroy")
		public MyBean ok() {
			return new MyBean();
		}
		
		@Bean
		public static PostProcess getPostProcess() {
			return new PostProcess();
		}
	}
	
	public static void main(String[] args) {
		final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Configuration.class);
		
		context.registerShutdownHook();
		context.close();
		
		//context.getBean(MyBean.class);
	}
}
