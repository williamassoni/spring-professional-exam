package com.spring.professional.exam.core.weblifecycle;

import javax.annotation.PreDestroy;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

@Component
@ApplicationScope
public class BeanApplicationScope implements DisposableBean {
	final org.slf4j.Logger logger = LoggerFactory.getLogger(BeanSessionScope.class);
	
	public BeanApplicationScope() {
		logger.warn("Calling ApplicationScope constructor");
	}
	
	@PreDestroy
	public void killing() {
		logger.warn("Calling @PreDestroy of ApplicationScope");
	}

	@Override
	public void destroy() throws Exception {
		logger.warn("Calling DisposableBean::destroy of ApplicationScope");
	}
	
	public void close() {
		logger.warn("Calling @Bean.close of ApplicationScope");
	}

	public String getSomethingFromApplication() {
		return "something";
	}
}
