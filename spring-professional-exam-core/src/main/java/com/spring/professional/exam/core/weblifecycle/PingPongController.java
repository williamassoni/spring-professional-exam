package com.spring.professional.exam.core.weblifecycle;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingPongController {
	
	@Autowired
	private BeanApplicationScope application;
	
	@Autowired
	private BeanRequestScope request;
	
	@Autowired
	private BeanSessionScope session;
	

	@GetMapping("/ping")
	public Map<String, String> pong(){
		return Map.of("request", request.getSomethingFromRequest2(), 
				      "session", session.getSomethingFromSession(), 
				      "application", application.getSomethingFromApplication());
	}
}
