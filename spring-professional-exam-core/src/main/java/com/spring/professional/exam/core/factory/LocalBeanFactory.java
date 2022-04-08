package com.spring.professional.exam.core.factory;

import org.springframework.beans.factory.config.AbstractFactoryBean;

public class LocalBeanFactory extends AbstractFactoryBean<LocalDTO> {
	private String weirdStuff;
	
	public LocalBeanFactory(final String weirdStuff) {
		this.setSingleton(false);
		this.weirdStuff = weirdStuff;
	}
	
	@Override
	public Class<LocalDTO> getObjectType() {
		return LocalDTO.class;
	}

	@Override
	protected LocalDTO createInstance() throws Exception {
		System.err.println("Calling .."+weirdStuff);
		
		return new LocalDTO(weirdStuff);
	}
}
