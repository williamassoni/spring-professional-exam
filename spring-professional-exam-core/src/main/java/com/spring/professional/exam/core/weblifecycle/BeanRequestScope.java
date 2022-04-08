package com.spring.professional.exam.core.weblifecycle;

import javax.annotation.PreDestroy;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class BeanRequestScope implements DisposableBean {
	final org.slf4j.Logger logger = LoggerFactory.getLogger(BeanSessionScope.class);
	
	public BeanRequestScope() {
		logger.warn("Calling RequestScope constructor");
	}
	
	@PreDestroy
	public void killingSession() {
		logger.warn("Calling @PreDestroy of RequestScope");
	}

	@Override
	public void destroy() throws Exception {
		logger.warn("Calling DisposableBean::destroy of RequestScope");
	}
	
	public void close() {
		logger.warn("Calling @Bean.close of RequestScope");
	}

	public String getSomethingFromRequest() {
		return "something";
	}

	String getSomethingFromRequest2() {
		return "something";
	}
}
