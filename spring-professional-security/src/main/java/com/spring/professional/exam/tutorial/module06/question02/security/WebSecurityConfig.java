package com.spring.professional.exam.tutorial.module06.question02.security;

import static com.spring.professional.exam.tutorial.module06.question02.security.SecurityRoles.CUSTOMERS_PAG_VIEW;
import static com.spring.professional.exam.tutorial.module06.question02.security.SecurityRoles.CUSTOMERS_READ;
import static com.spring.professional.exam.tutorial.module06.question02.security.SecurityRoles.DEPARTMENTS_CREATE;
import static com.spring.professional.exam.tutorial.module06.question02.security.SecurityRoles.DEPARTMENTS_PAG_VIEW;
import static com.spring.professional.exam.tutorial.module06.question02.security.SecurityRoles.DEPARTMENTS_READ;
import static com.spring.professional.exam.tutorial.module06.question02.security.SecurityRoles.EMPLOYEES_READ;
import static com.spring.professional.exam.tutorial.module06.question02.security.SecurityRoles.SUPER_ADMIN;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.authentication.logout.LogoutFilter;

import com.spring.professional.exam.tutorial.module06.question02.config.CustomFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private RoleHierarchy roleHierarchy;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http
    		.addFilterBefore(new CustomFilter(), LogoutFilter.class)
    		.authorizeRequests()
    		.expressionHandler(expressionHandler())
			.mvcMatchers("/", "/home").permitAll()
			.mvcMatchers("/auditevents/**").permitAll()
			.mvcMatchers("/actuator/**").permitAll()
			//.mvcMatchers("/employees/**").access("@cujuck.hasPower()")
			.antMatchers("/employees*").hasRole(SecurityRoles.EMPLOYEES_PAG_VIEW)
//    			.antMatchers("/employees").hasRole(SecurityRoles.EMPLOYEES_PAG_VIEW)
	//		.mvcMatchers("/departments").hasRole(SecurityRoles.DEPARTMENTS_PAG_VIEW)
	//		.mvcMatchers("/customers").hasRole(SecurityRoles.CUSTOMERS_PAG_VIEW)
			.anyRequest().authenticated()
    		.and()
    		.formLogin()
	    		.loginPage("/login")
	    		.failureUrl("/login-error")
	    		.permitAll()
    		.and()
    		.logout()
	    		.logoutUrl("/logout")
	    		.logoutSuccessUrl("/login")
	    		.permitAll()
	    	.and()
	    		.requiresChannel()
	    			.anyRequest()
	    			.requiresInsecure();
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        auth.inMemoryAuthentication()
                .withUser("admin")
                .password(encoder.encode("admin"))
                .roles(SUPER_ADMIN)
                .and()
                .withUser("emma")
                .password(encoder.encode("emma"))
                .roles(EMPLOYEES_READ)
                .and()
                .withUser("william")
                .password(encoder.encode("william"))
                .roles(DEPARTMENTS_PAG_VIEW, DEPARTMENTS_READ, DEPARTMENTS_CREATE)
                .and()
                .withUser("lucas")
                .password(encoder.encode("lucas"))
                .roles(CUSTOMERS_PAG_VIEW, CUSTOMERS_READ)
                .and()
                .withUser("tom")
                .password(encoder.encode("tom"))
                .roles();
    }

    private DefaultWebSecurityExpressionHandler expressionHandler() {
        DefaultWebSecurityExpressionHandler expressionHandler = new DefaultWebSecurityExpressionHandler();
        expressionHandler.setRoleHierarchy(roleHierarchy);
        return expressionHandler;
    }
}
