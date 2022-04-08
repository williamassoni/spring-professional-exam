package com.spring.professional.exam.core.spel.valueusage;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;

public class Runner {

	@Configuration
	@ComponentScan
	static class LocalConfiguration {
		
		@Bean
	    public ConversionService conversionService() {
	        return new DefaultConversionService();
	    }
	}
	
	public static void main(String[] args) {
		final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(LocalConfiguration.class);
		
		context.registerShutdownHook();
		
		final ScalarLiteralBean scalarBean = context.getBean(ScalarLiteralBean.class);
		
		System.err.println(scalarBean.getName());
		System.err.println(scalarBean.isAccountExists());
		System.err.println(scalarBean.getIdNumber());
		
		System.err.println(scalarBean.getFixedValue());
		System.err.println(scalarBean.getAccountBalance());
		System.err.println(scalarBean.getDepartmentName());
		System.err.println(scalarBean.getIdNumberDouble());
		
		System.err.println(scalarBean.getResult());
		
	}
}
