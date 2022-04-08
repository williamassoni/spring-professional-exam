package com.spring.professional.exam.tutorial.module06.question02.security;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.AfterInvocationProvider;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.intercept.AfterInvocationManager;
import org.springframework.security.access.intercept.RunAsManager;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.RoleHierarchyVoter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

class CUJUCK {
	
	public boolean hasPower() {
		return SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
	}
}

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class GlobalMethodSecurityConfig extends GlobalMethodSecurityConfiguration {

	@Autowired
	private CustomPermissionEvaluator customEvaluator;
	
    @Autowired
    private RoleHierarchy roleHierarchy;

    @Override
    protected AccessDecisionManager accessDecisionManager() {
        AffirmativeBased affirmativeBased = (AffirmativeBased) super.accessDecisionManager();
        affirmativeBased.getDecisionVoters().add(
                new RoleHierarchyVoter(roleHierarchy)
        );
        return affirmativeBased;
    }
    
    @Override
	protected MethodSecurityExpressionHandler createExpressionHandler() {
    	//super.createExpressionHandler();
		DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();
		expressionHandler.setPermissionEvaluator(new CustomPermissionEvaluator());
		return expressionHandler;
	}
    
    
    @Override
    protected AfterInvocationManager afterInvocationManager() {
    	return super.afterInvocationManager();
		/*AfterInvocationProviderManager invocationProviderManager = new AfterInvocationProviderManager();
		ExpressionBasedPostInvocationAdvice postAdvice = new ExpressionBasedPostInvocationAdvice(
				getExpressionHandler());
		PostInvocationAdviceProvider postInvocationAdviceProvider = new PostInvocationAdviceProvider(postAdvice);
		List<AfterInvocationProvider> afterInvocationProviders = new ArrayList<>();
		afterInvocationProviders.add(postInvocationAdviceProvider);
		afterInvocationProviders.add(myTest());
		
		invocationProviderManager.setProviders(afterInvocationProviders);
		return invocationProviderManager;*/
    }
    
    @Bean
    public AfterInvocationProvider myTest() {
    	return new AfterInvocationProvider() {
			
			@Override
			public boolean supports(Class<?> clazz) {
				return true;
			}
			
			@Override
			public boolean supports(ConfigAttribute attribute) {
				return true;
			}
			
			@Override
			public Object decide(Authentication authentication, Object object, Collection<ConfigAttribute> attributes,
					Object returnedObject) throws AccessDeniedException {
				return returnedObject;
			}
		};
    }
    
    @Bean
	public CUJUCK cujuck() {
		return new CUJUCK();
	}
    
    @Override
    protected RunAsManager runAsManager() {
    	return new RunAsManager() {
			
			@Override
			public boolean supports(Class<?> clazz) {
				return true;
			}
			
			@Override
			public boolean supports(ConfigAttribute attribute) {
				return true;
			}
			
			@Override
			public Authentication buildRunAs(Authentication authentication, Object object,
					Collection<ConfigAttribute> attributes) {
				return authentication;
			}
		};
    }
}
