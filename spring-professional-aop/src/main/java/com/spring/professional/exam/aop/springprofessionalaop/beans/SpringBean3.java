package com.spring.professional.exam.aop.springprofessionalaop.beans;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Setter
@Getter
public class SpringBean3 {
	
	private SpringBean2 springBean2;
	
	public Long calculateValue(final Long value1, final Long value2) {
		return value1+value2;
	}
	
	public void execute() {
		springBean2.execute();
		springBean2.execute2();
	}
}
