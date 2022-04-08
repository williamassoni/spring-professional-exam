package com.rest.controller;

import java.net.URI;

import org.springframework.boot.CommandLineRunner;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rest.fakemodel.Customer;
import com.rest.repository.CustomersRepository;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class CustomerController {

	private CustomersRepository customersRepository;
	private final EntityLinks entityLinks;
	 
	@PostMapping(value = "/customers")
	public HttpEntity<Customer> createCustomer( @RequestBody final Customer customer){
		return ResponseEntity.ok()
				.body(customer);
	}
	
	@GetMapping(path = "/customers")
	public CollectionModel<com.rest.ds.Customer> findAll(){
		Iterable<com.rest.ds.Customer> allCustomers = customersRepository.findAll();
		
		CollectionModel<com.rest.ds.Customer> result = CollectionModel.of(allCustomers);
		
		/* return ResponseEntity //
			      .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
			      .body(entityModel);*/
		 
		return result;
	}
	
	@PostMapping(value = "/customers/location")
	public HttpEntity<Customer> createCustomerLocation(@RequestBody final Customer customer){
		return ResponseEntity.accepted().location(URI.create("/api/customers/" + 1)).body(customer);
	}
	
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@PostMapping(value  = "/customers/empty")
	public void createCustomerEmpty( @RequestBody final Customer customer){
	}
	
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@PutMapping(value = "/customers/{id}")
	public void updateCustomer(@PathVariable("id") final String id) {
	}
	
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@RequestMapping(path = "/customers/{id}/ff", method =  RequestMethod.PATCH)
	public Customer updateCustomerPartially(@PathVariable("id") final String id) {
		return new Customer("OK");
	}
	
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping(path = "/customers/{id}")
	public void deleteCustomer(@PathVariable("id") final String id) {
	}

	//curl -X OPTIONS "http://localhost:8080/customer/op"
	@RequestMapping(path = "/customer/op", method = RequestMethod.OPTIONS)
	public ResponseEntity<String> useOptions(){
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
}
