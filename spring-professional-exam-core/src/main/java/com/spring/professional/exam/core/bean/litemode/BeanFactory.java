package com.spring.professional.exam.core.bean.litemode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class BeanFactory {
	
	public class LocalBean {
		
		public void init() {
			System.err.println("");	
		}
		
		public void shutdown() {
			System.err.println("");
		}
		
	}
	
	@Bean(initMethod = "init")
	@Qualifier("public")
	public LocalBean getMethod1() {
		return new LocalBean();
	}
	
	@Bean
	@Value("${test:none}")
	private LocalBean getMethod2(final String value) {
		return new LocalBean();
	}
	
	@Bean
	public LocalBean getMethod3(@Autowired(required = false) final String string) {
		return new LocalBean();
	}
	
	@Bean
	public LocalBean getMethod4(@Qualifier("public") final LocalBean bean) {
		return new LocalBean();
	}
	
	/*
	@Bean
	@Scope("prototype")
	public LocalBean getMethod5(final InjectionPoint injectionPoint) {
		return new LocalBean();
	}*/
}
