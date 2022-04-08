package com.spring.professional.exam.core.qualifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
@Qualifier
public class SpringBean1 {
	
	//@Autowired
	//@Qualifier("random")
	private String field;
	
	
	@Autowired
	@Qualifier("random")
	public void setField(final String field, final String field2) {
		this.field = field;
	}
	
}
