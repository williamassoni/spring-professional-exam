package com.rest.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.rest.ds.Customer;

public interface CustomersRepository extends CrudRepository<Customer, Integer> {
    
	List<Customer> findByFirstNameLike(String firstNamePattern);
}
