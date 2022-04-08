package com.spring.professional.exam.core.jsr.beans;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;

import lombok.Getter;
import lombok.Setter;

//@ManagedBean
@Named("springBean1")
@Setter
@Getter
public class SpringBean1 {

	@Inject
	@Named("potato")
	private List<String> showAllStrings;
	
	@Resource
	private String type1;
	
}
