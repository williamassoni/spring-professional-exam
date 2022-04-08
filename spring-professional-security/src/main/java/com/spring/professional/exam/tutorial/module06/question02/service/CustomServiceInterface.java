package com.spring.professional.exam.tutorial.module06.question02.service;

import java.util.Optional;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

import com.spring.professional.exam.tutorial.module06.question02.ds.Customer;

public interface CustomServiceInterface {

	@Secured("ROLE_SUPER_ADMIN")
	@PreAuthorize("hasPermission(#id, 'ROLE_CUSTOMER_READ')")
	//@PreAuthorize("@cujuck.hasPower()")
	@PostAuthorize("hasPermission(returnObject.get(), 'ROLE_CUSTOMER_READ')")
	Optional<Customer> findByOne(final Integer id);
}
