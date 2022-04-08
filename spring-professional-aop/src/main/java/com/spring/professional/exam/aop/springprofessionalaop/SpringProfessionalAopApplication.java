package com.spring.professional.exam.aop.springprofessionalaop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.spring.professional.exam.aop.springprofessionalaop.beans.AdviceExecutionOrderBean;
import com.spring.professional.exam.aop.springprofessionalaop.beans.BaseInterface;
import com.spring.professional.exam.aop.springprofessionalaop.beans.Food;
import com.spring.professional.exam.aop.springprofessionalaop.beans.SpringBean1;
import com.spring.professional.exam.aop.springprofessionalaop.beans.SpringBean2;
import com.spring.professional.exam.aop.springprofessionalaop.beans.SpringBean3;
import com.spring.professional.exam.aop.springprofessionalaop.beans.SpringBean4;
import com.spring.professional.exam.aop.springprofessionalaop.beans.SpringBean5;
import com.spring.professional.exam.aop.springprofessionalaop.obj.CustomObject;

public class SpringProfessionalAopApplication {
	
	@Configuration
	@ComponentScan
	@EnableAspectJAutoProxy
	static class LocalConfiguration {
		
	}
	
	static class Reference {

		@Bean
		public String okInternal() {
			return "return";
		}
	}
	
	public static void main(String[] args) {
		final AnnotationConfigApplicationContext configuration = new AnnotationConfigApplicationContext(LocalConfiguration.class, Reference.class);
		
		configuration.registerShutdownHook();
		
		SpringBean1 bean1 = configuration.getBean(SpringBean1.class);
		
		bean1.printSomething("..");
		bean1.printSomething("var1", "var2");
		
		bean1.throwAnException();
		
		SpringBean2 bean2 = configuration.getBean(SpringBean2.class);
		bean2.setName("OK");
		
		SpringBean3 bean3 = configuration.getBean(SpringBean3.class);
		bean3.setSpringBean2(bean2);
		bean3.calculateValue(1L, 2L);
		bean3.execute();
		
		Food food = configuration.getBean(Food.class);
		food.eat();
		
		SpringBean4 bean4 = configuration.getBean(SpringBean4.class);
		//bean4.doSomething();
		bean4.doSomethingElse(new CustomObject());
		
		SpringBean5 bean5 = configuration.getBean(SpringBean5.class);
		bean5.setName("OK");
		bean5.getName();
		
		
		configuration.getBean("okInternal");
		
		//https://github.com/spring-projects/spring-framework/issues/22311
		configuration.getBean(BaseInterface.class).doSomething();
		configuration.getBean(BaseInterface.class).someThingElse();
		
		configuration.getBean(AdviceExecutionOrderBean.class).execute();
		
	}

}
