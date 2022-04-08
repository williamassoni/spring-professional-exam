package com.spring.professional.exam.core.weblifecycle;

import javax.annotation.PreDestroy;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class BeanSessionScope implements DisposableBean {
	final org.slf4j.Logger logger = LoggerFactory.getLogger(BeanSessionScope.class);
	
	public BeanSessionScope() {
		logger.warn("Calling SessionScope constructor");
	}
	
	@PreDestroy
	public void killingSession() {
		logger.warn("Calling @PreDestroy of SessionScope");
	}

	@Override
	public void destroy() throws Exception {
		logger.warn("Calling DisposableBean::destroy of SessionScope");
	}
	
	public void close() {
		logger.warn("Calling @Bean.close of SessionScope");
	}

	protected String getSomethingFromSession() {
		return "something";
	}
	
}
