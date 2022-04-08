package com.spring.professional.exam.core.qualifier;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

//@Component
//@Qualifier("sample_interface2")
public class SampleImpl implements SampleInterface2{

	public SampleImpl() {
		System.err.println("");
	}
}