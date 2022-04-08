package com.spring.professional.exam.core.lookup;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

@Component
public abstract class Teste {

	//@Lookup
	public abstract ProtoypeBean getPrototype(); 

}
