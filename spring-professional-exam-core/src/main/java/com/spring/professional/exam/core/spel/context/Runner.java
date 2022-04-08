package com.spring.professional.exam.core.spel.context;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class Runner {
	
	@Configuration
	@ComponentScan
	static class ApplicationConfiguration {
		
	}
	
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        context.registerShutdownHook();

        StandardEvaluationContext evaluationContext = new StandardEvaluationContext();
        evaluationContext.setBeanResolver(new BeanFactoryResolver(context));

        ExpressionParser parser = new SpelExpressionParser();

        System.out.println(
                parser.parseExpression("@springBean1.streetName").getValue(evaluationContext)
        );
        
        System.out.println(
                parser.parseExpression("2!=5").getValue(evaluationContext)
        );
        
        System.out.println(
                parser.parseExpression("{1,2,3,4}").getValue(evaluationContext)
        );
        
        System.out.println(
                parser.parseExpression("@springBean1.accountBalance").getValue(evaluationContext)
        );
        /*
        System.out.println(
                parser.parseExpression("@springBean1.casesMap.get('caseA')").getValue(evaluationContext)
        );*/

    }
}
