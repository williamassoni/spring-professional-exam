package com.spring.professional.exam.core.autowired;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.spring.professional.exam.core.autowired.config.GenericBean;
import com.spring.professional.exam.core.autowired.config.GenericBeanExtension;
import com.spring.professional.exam.core.autowired.movie.Format;
import com.spring.professional.exam.core.autowired.movie.MovieCatalog;
import com.spring.professional.exam.core.autowired.movie.MovieType;

@ComponentScan
@Configuration
public class Runner {

	@Bean
	@Qualifier("bean1")
	public GenericBean getGenericBean1() {
		return new GenericBean() {
			
			@Override
			public void doSomething() {
				System.err.println("Running dosomething bean1");
			}
		};
	}
	
	@Bean
	@Primary
	@Qualifier("bean1")
	public GenericBean getGenericBean2() {
		return new GenericBean() {
			
			@Override
			public void doSomething() {
				System.err.println("Running dosomething bean2");
			}
		};
	}

	@Bean(autowireCandidate = false, name = "bean3")
	public GenericBean getGenericBean3() {
		return new GenericBean() {
			
			@Override
			public void doSomething() {
				System.err.println("Running dosomething bean1");
			}
		};
	}
	
	@Bean
	public GenericBeanExtension beanextension3() {
		return new GenericBeanExtension() {
			
			@Override
			public void doSomethingElse() {
				System.err.println("Running dosomething bean3");
			}
		};
	}
	
	@Bean
	public GenericBeanExtension beanextension4() {
		return new GenericBeanExtension() {
			
			@Override
			public void doSomethingElse() {
				System.err.println("Running dosomething bean4");
			}
		};
	}
	
	@Bean
	@MovieType(format = Format.VHS, genre = "Action")
	public MovieCatalog catalogVHS() {
		return new MovieCatalog() {
			
			@Override
			public void print() {
				System.err.println("VHS");
			}
		};
	}
	
	@Bean
	@MovieType(format = Format.DVD, genre = "Action")
	public MovieCatalog catalogDVD() {
		return new MovieCatalog() {
			
			@Override
			public void print() {
				System.err.println("DVD");
			}
		};
		
	}
	
	@Bean
	public String returnCatalog(@MovieType(format = Format.VHS, genre = "Action") final MovieCatalog vhs,
								@MovieType(format = Format.DVD, genre = "Action") final MovieCatalog dvd) {
		vhs.print();
		dvd.print();
		
		return "..";
	}
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Runner.class);

		//context.getBeanFactory().getSingletonCount();
		//context.getBeanFactory().registerSingleton("potato", "AAA");
		context.registerShutdownHook();
		
		context.getBean("bean3");
		context.getBean("potato");
	}
}
