package com.spring.professional.exam.core.lifecycle.beans;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.InitializingBean;

public class PotatoBean implements InitializingBean{
	
	public PotatoBean() {
        System.out.println("\nCreating " + getClass().getSimpleName());
	}

    @PostConstruct
    public void postConstruct() throws Exception {
        System.out.println("@PostConstruct " + getClass().getSimpleName());
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("@PreDestroy " + getClass().getSimpleName());
    }

    
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean::afterPropertiesSet " + getClass().getSimpleName());
    }

    /*
    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean::destroy " + getClass().getSimpleName());
    }*/

    private void initMethod() {
        System.out.println("@Bean(initMethod) " + getClass().getSimpleName());
    }

    private void destroyMethod() {
        System.out.println("@Bean(destroyMethod) " + getClass().getSimpleName());
    }

    public void shutdown() {
        System.out.println("@Bean(close.......destroyMethod) " + getClass().getSimpleName());
    }
    
}
