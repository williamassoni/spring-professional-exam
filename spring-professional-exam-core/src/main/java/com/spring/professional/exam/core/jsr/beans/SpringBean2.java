package com.spring.professional.exam.core.jsr.beans;

import javax.annotation.ManagedBean;
import javax.annotation.Resource;

@ManagedBean
public class SpringBean2 {
	
	private String type2;
	private String type3;

	@Resource
	public void setType3(String type3) {
		//this.type2 = type2;
	}
}
