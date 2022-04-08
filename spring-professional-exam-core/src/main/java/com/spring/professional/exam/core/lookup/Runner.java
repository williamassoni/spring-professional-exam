package com.spring.professional.exam.core.lookup;

import java.lang.reflect.Method;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

public class Runner {
	
	@ComponentScan
	@Configuration
	static class LocalConfiguration {
		
		@Bean
		public ProtypeBeanUsingProxy ok() {
			 Enhancer enhancer = new Enhancer();
	        enhancer.setCallback(new MethodInterceptor() {
				
				@Override
				public Object intercept(Object object, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
					System.err.println("before " + method.getName());
			        Object result = methodProxy.invokeSuper(object, args);
			        System.err.println("after " + method.getName());
					return result;
				}
			});
	        enhancer.setSuperclass(ProtypeBeanUsingProxy.class);
	        
	        return (ProtypeBeanUsingProxy) enhancer.create();
		}
		
		@Bean
		@Scope("prototype")
		public String getA() {
			return "A";
		}
		
		@Bean
		@Scope("prototype")
		@Primary
		public String getB() {
			return "B";
		}
		
		@Bean
		@Scope("prototype")
		public String getC() {
			return "C";
		}
		
		@Bean
		@Scope("prototype")
		@Qualifier("newones")
		public String getD() {
			return "D";
		}
		
		@Bean
		@Scope("prototype")
		@Qualifier("newones")
		public String getE() {
			return "E";
		}
		
	}
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(LocalConfiguration.class);
		
		
		context.registerShutdownHook();
		
		context.getBean(SingletonBean.class).getPrototype();
		context.getBean(SingletonBean.class).doSomething();
		context.getBean(SingletonBean.class).doSomethingWithObjectFactory();
	}
}
