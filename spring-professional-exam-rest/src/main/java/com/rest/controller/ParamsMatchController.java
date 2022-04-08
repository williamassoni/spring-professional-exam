package com.rest.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rest.fakemodel.Customer;
import com.rest.fakemodel.Customers;

@RestController
public class ParamsMatchController {
	
	//curl -X GET "http://localhost:8080/match?pr1=a" -v
	@GetMapping(value = "/match", params = "pr1")
	public String match1(@RequestParam(name = "pr1") final String param1) {
		return "match1";
	}
	
	@GetMapping(value = "/match", params = "pr1", consumes = "application/json")
	public Customer matchJson(@RequestParam(name = "pr1") final String param1) {
		return new Customer("test");
	}
	
	@GetMapping(value = "/match")
	public String fallback() {
		return "fallback";
	}
	
	@GetMapping(value = "/resttemplate/list")
	public Customers getAll(){
		return  new Customers(List.of(new Customer("ok1"), new Customer("ok2")));
	}
	
}
