package com.spring.professional.exam.core.qualifier;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan
@EnableAspectJAutoProxy
public class Runner {

	@Bean
	@Qualifier("sample-candidate")
	public SampleInterface sampleInterface1() {
		return new SampleInterface() {
			
			@Override
			public void doSomething() {
				System.err.println("Running dosomething sample1");				
			}
		};
	}
	
	@Bean
	@Qualifier("sample-candidate")
	public SampleInterface sampleInterface2() {
		return new SampleInterface() {
			
			@Override
			public void doSomething() {
				System.err.println("Running dosomething sample2");				
			}
		};
	}
	
	@Bean
	public SampleInterface sampleInterface3() {
		return new SampleInterface() {
			
			@Override
			public void doSomething() {
				System.err.println("Running dosomething sample3");				
			}
		};
	}
	
	@Bean
	public String justShowSomething(final @Qualifier("sampleInterface1") SampleInterface sample) {
		sample.doSomething();
		
		return "OK";
	}
	
	@Bean
	public String justShowSomethingElse(final @Qualifier("sample-candidate") List<SampleInterface> sample) {
		sample.stream().forEach(SampleInterface::doSomething);
		
		return "OK";
	}
	
	@Bean
	@Qualifier
	public String random() {
		return "not possible";
	}
	
	@Bean
	@Qualifier("sample_interface2")
	public SampleInterface2 interface2() {
		return new SampleInterface2() {
		};
	}
	
	@Bean
	public String interface3(@Qualifier("sample_interface2") final SampleInterface2 it) {
		System.err.println();
		
		return "OK";
	}
	
	@Bean
	public String test006(@Qualifier("sampleImpl2") final SampleInterface it) {
		return "";
	}
	
	@Bean
	@Qualifier
	public SampleInterface sampleImpl2() {
		return new SampleImpl2();
	}
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Runner.class);
		
		context.registerShutdownHook();
		
		context.getBean("random");
		context.getBean(SpringBean1.class);
	}
}
