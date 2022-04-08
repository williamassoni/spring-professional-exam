package com.spring.professional.exam.core.lookup;

import javax.inject.Provider;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Component
@Setter
@Getter
@AllArgsConstructor
public abstract class SingletonBean {
	
	@Autowired
	private Provider<ProtoypeBean> localReference;
	
	@Autowired
	private ProtypeBeanUsingProxy proxy;
	
	@Autowired
	private ObjectFactory<ProtoypeBean> usingObjectFactory;
	
	@Autowired
	private ObjectProvider<ProtoypeBean> usingObjectProvider;

	@Autowired
	private ObjectProvider<String> primaryString;

	@Autowired
	private ObjectProvider<String> allBeansFromString;
	
	@Autowired
	@Qualifier("newones")
	private ObjectProvider<String> allBeansFromNew;
	
	@Lookup
	public abstract ProtoypeBean getPrototype(); 
	
	public void doSomething() {
		localReference.get();
	}
	
	public void doSomethingWithObjectFactory() {
		usingObjectFactory.getObject();
		usingObjectProvider.getIfAvailable();

		primaryString.getObject();
		System.err.println("All beans using String");
		allBeansFromString.stream().forEach(System.out::println);
		
		System.err.println("All beans using qualifier newOnes");
		allBeansFromNew.stream().forEach(System.out::println);
	}
}
