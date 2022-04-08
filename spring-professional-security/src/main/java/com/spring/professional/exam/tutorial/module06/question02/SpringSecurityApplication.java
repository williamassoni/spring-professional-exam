package com.spring.professional.exam.tutorial.module06.question02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.audit.AuditEventRepository;
import org.springframework.boot.actuate.audit.InMemoryAuditEventRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringSecurityApplication implements ApplicationRunner{

	@Autowired
	private ApplicationContext context;
	
    // Spring Security Code Places to analyze:
    // DelegatingFilterProxy.doFilter
    // FilterChainProxy.doFilterInternal
    // ProviderManager.authenticate
    // AffirmativeBased.decide
    // RoleVoter.vote
    public static void main(String[] args) {
    	SpringApplication.run(SpringSecurityApplication.class, args);
    }
    
    @Bean
    public AuditEventRepository auditEventRepository() {
    	return new InMemoryAuditEventRepository(5000);
    }

	@Override
	public void run(ApplicationArguments args) throws Exception {
		context.getBean("springSecurityFilterChain");
		
	}
}
