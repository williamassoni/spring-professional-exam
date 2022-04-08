package com.spring.professional.exam.core.componentscan;

import java.util.stream.Stream;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.AnnotationScopeMetadataResolver;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ScopeMetadata;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.type.ClassMetadata;
import org.springframework.stereotype.Component;

import com.spring.professional.exam.core.componentscan.config.Bean5;
import com.spring.professional.exam.core.componentscan.config.BeanIgnoreAspect;
import com.spring.professional.exam.core.componentscan.config.HateAnnotation;

class LocalNameGenerator implements BeanNameGenerator {

	@Override
	public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
		return "extra - " + definition.getBeanClassName();
	}
	
}

class CustomScopeResolver extends AnnotationScopeMetadataResolver {
	
	@Override
	public ScopeMetadata resolveScopeMetadata(BeanDefinition definition) {
		return super.resolveScopeMetadata(definition);
	}
}

class CustomFilter extends org.springframework.core.type.filter.AbstractClassTestingTypeFilter {
	
	@Override
	protected boolean match(ClassMetadata metadata) {
		return metadata.getClassName().equalsIgnoreCase(BeanIgnoreAspect.class.getName());
	}
}

@Configuration
@ComponentScan(includeFilters  = @Filter(type = FilterType.ANNOTATION, classes = Component.class), 
			   excludeFilters = {@Filter(type = FilterType.ANNOTATION, classes = HateAnnotation.class),
					   			 @Filter(type = FilterType.REGEX, pattern = ".*[4]"),
					   			 @Filter(type = FilterType.CUSTOM, classes = CustomFilter.class),
					   			 //@Filter(type = FilterType.ASPECTJ, pattern = "com.spring.professional.exam.core.componentscan.config.*")
			   },
			   nameGenerator = LocalNameGenerator.class,
			   scopeResolver = CustomScopeResolver.class,
			   useDefaultFilters = false,
			   scopedProxy = ScopedProxyMode.INTERFACES
			   )

public class ApplicatioScanner {
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicatioScanner.class);
		
		context.registerShutdownHook();
		Bean5 bean5 = context.getBean(com.spring.professional.exam.core.componentscan.config.Bean5.class);
		
		context.getBean("extra - com.spring.professional.exam.core.componentscan.config.Bean1");
		context.getBeansWithAnnotation(Component.class);
		
		
		Stream.of(context.getBeanDefinitionNames()).forEach(n -> System.err.println(n));
	}
}
