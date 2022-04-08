package com.spring.professional.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import com.spring.professional.exam.tutorial.module06.question02.SpringSecurityApplication;
import com.spring.professional.exam.tutorial.module06.question02.service.CustomerService;

@SpringJUnitWebConfig(SpringSecurityApplication.class)
public class MockTest {
	
	@Autowired
	private CustomerService service;

	@WithMockUser(username = "william", roles = "SUPER_ADMIN")
	@WithAnonymousUser
	@Test
	public void tt() {
		service.findAll();
	}
}
