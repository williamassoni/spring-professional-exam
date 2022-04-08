package com.spring.professional.exam.tutorial.module06.question02.service;

import java.util.List;
import java.util.Optional;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.spring.professional.exam.tutorial.module06.question02.config.CustomAnnotation;
import com.spring.professional.exam.tutorial.module06.question02.dao.CustomersDao;
import com.spring.professional.exam.tutorial.module06.question02.ds.Customer;

//@Secured("ROLE_CUSTOMER_READ")
@Service
public class CustomerService implements CustomServiceInterface{
	
	@Autowired
	private CustomersDao customersDao;
	 
	@Secured({"ROLE_CUSTOMER_CREATE", "ROLE_SUPER_ADMIN"})
	@RolesAllowed("ROLE_CUSTOMER_CREATE")
	@PreAuthorize("hasRole('ROLE_CUSTOMER_CREATE') || hasRole('ROLE_SUPER_ADMIN')")
	@PreFilter("hasRole('ROLE_SUPER_ADMIN') || filterObject.code.equals('QA')")
	public void save(List<Customer> customer) {
		customersDao.saveAll(customer);
	}

	@Secured({"ROLE_CUSTOMER_DELETE", "ROLE_SUPER_ADMIN"})
	@RolesAllowed(value = "ROLE_CUSTOMER_DELETE")
	@PreAuthorize("hasRole('ROLE_CUSTOMER_DELETE') || hasRole('ROLE_SUPER_ADMIN')")
	public void deleteById(Integer id) {
		customersDao.deleteById(id);
	}

	//@CustomAnnotation
	//@RolesAllowed(value = {"ROLE_CUSTOMER_READ", "ROLE_SUPER_ADMIN"})
	//@Secured({"ROLE_CUSTOMER_READ", "ROLE_SUPER_ADMIN"})
	@PreAuthorize("hasRole('ROLE_CUSTOMER_READ') || hasRole('ROLE_SUPER_ADMIN')")
	//@PostFilter("hasAuthority('ROLE_SUPER_ADMIN') || (filterObject.code.equals('QA') || filterObject.code.equals('TI'))")
	@PostFilter("filterObject.code.equals('QA')")
	public Iterable<Customer> findAll() {
		SecurityContextHolder.getContext().getAuthentication();
		return customersDao.findAll();
	}
	
	@Override
	public Optional<Customer> findByOne(final Integer id) {
		return customersDao.findById(1);
	}
}