package com.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/sample")
public class SampleForOptionsController {

	@PostMapping
	public String doSomething1() {
		return "OK";
	}
	
	@PatchMapping
	public String doSomethingElse2() {
		return "OK";
	}
	
	@RequestMapping
	public String doSomethingElse3() {
		return "";
	}
	
	@GetMapping
	public String doSomething() {
		return "OK";
	}
	
	@GetMapping("/{id}")
	public String doSomethingById(@PathVariable("id") final long id) {
		return "OK";
	}
}
