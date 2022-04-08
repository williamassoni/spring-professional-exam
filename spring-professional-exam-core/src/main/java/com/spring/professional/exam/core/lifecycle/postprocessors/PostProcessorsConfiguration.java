package com.spring.professional.exam.core.lifecycle.postprocessors;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PostProcessorsConfiguration {

	@Bean
	public static BeanPostProcessor getCustomBeanPostProcessor() {
		return new BeanPostProcessor() {
			@Override
			public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
				System.err.println("calling posinitilization "+ beanName);
				return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
			}
			
			@Override
			public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
				System.err.println("calling preinitilization "+ beanName);
				return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
			}
		};
	}
	
	
	@Bean
	public static BeanFactoryPostProcessor getBeanFactoryPostProcessor() {
		return new BeanFactoryPostProcessor() {
			
			@Override
			public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
				System.err.println("Calling bean postprocessor");
			}
		};
	}
}
