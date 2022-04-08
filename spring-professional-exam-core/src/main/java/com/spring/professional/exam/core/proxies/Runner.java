package com.spring.professional.exam.core.proxies;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.spring.professional.exam.core.lookup.ProtypeBeanUsingProxy;

class SaladInvokation implements InvocationHandler{

	private SaladImpl impl;
	
	public SaladInvokation(final  SaladImpl saladImpl) {
		this.impl = saladImpl;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.err.println("JDK do something");
		Object result = method.invoke(impl, args);
		
		return result;
	}
	
}

class PotatoInterceptor implements MethodInterceptor {

	@Override
    public Object intercept(Object object, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.err.println("before " + method.getName());
        Object result = methodProxy.invokeSuper(object, args);
        System.err.println("after " + method.getName());
        return result;
    }
	
}

@Configuration
public class Runner {

	@Bean
	public Food salad() {
		return (Food) Proxy.newProxyInstance(
				Food.class.getClassLoader(), SaladImpl.class.getInterfaces(),
                new SaladInvokation(
                        new SaladImpl()
                )
        );
	}

	@Bean
	public Food potato() {
        Enhancer enhancer = new Enhancer();
        enhancer.setCallback(new PotatoInterceptor());
        enhancer.setSuperclass(SaladImpl.class);
        
        return (Food) enhancer.create();
	}
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Runner.class);
		
		context.registerShutdownHook();
		
		((Food) context.getBean("salad")).eat();
		((Food) context.getBean("potato")).eat();
	}
}