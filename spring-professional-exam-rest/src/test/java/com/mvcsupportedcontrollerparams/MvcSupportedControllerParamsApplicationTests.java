package com.mvcsupportedcontrollerparams;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import java.io.IOException;
import java.net.URI;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import com.rest.RestApplication;
import com.rest.fakemodel.Customer;
import com.rest.fakemodel.Customers;

@RestController
class FakeController {
	
	/*
	@ResponseStatus(code = HttpStatus.I_AM_A_TEAPOT)
	@GetMapping(path = "/justheaders")
	public void justReturnHeader() {
		
	}
	*/
	@ResponseStatus(code = HttpStatus.I_AM_A_TEAPOT)
	@GetMapping(path = "/justheaders")
	public HttpHeaders justReturnHeader() {
		HttpHeaders headers = new HttpHeaders();
		headers.addIfAbsent("count", "3");
		
		return headers; 
	}
}

@Configuration
class Config {
	
	@Bean
	public FakeController getFakeController() {
		return new FakeController();
	}
}

@SpringBootTest(webEnvironment = RANDOM_PORT, classes = {RestApplication.class, Config.class})
class MvcSupportedControllerParamsApplicationTests {

	@Autowired
    private TestRestTemplate restTemplate;
	
	@Test
	void shouldUseGetForObjectToFetchData() {
		final String withQueryParam = restTemplate.getForObject("/match?pr1={pr1}", String.class, Map.of("pr1", "ok"));
		assertEquals(withQueryParam, "match1");
		
		
		final String withoutQueryParam = restTemplate.getForObject("/match", String.class);
		assertEquals(withoutQueryParam, "fallback");
		
		final Customers customerData = restTemplate.getForObject("/resttemplate/list", Customers.class);
		assertFalse(customerData.getCustomers().isEmpty());
	}

	@Test
	void shouldUseGetForEntityToFetchData() {
		final ResponseEntity<String> withQueryparam = restTemplate.getForEntity("/match?pr1={pr1}", String.class , Map.of("pr1", "ok"));
		
		assertTrue(withQueryparam.getStatusCode().is2xxSuccessful());
		assertTrue(withQueryparam.getStatusCodeValue() == 200);
		
		
		final ResponseEntity<String> method202 = restTemplate.getForEntity("/topmethod", String.class);
		
		assertTrue(method202.getStatusCode().is2xxSuccessful());
		assertTrue(method202.getStatusCodeValue() == 202);
		assertTrue(method202.getBody().equalsIgnoreCase("OK"));
		
		
		ResponseEntity<String> method404 = restTemplate.getForEntity("/exception", String.class);
		assertTrue(method404.getStatusCode().is4xxClientError());
		assertTrue(method404.getStatusCodeValue() == 400);
	}
	
	@Test
	void shouldUseHeadForHeaders() {
		org.springframework.http.HttpHeaders httpHeaders = restTemplate.headForHeaders("/justheaders");
		
		assertTrue(httpHeaders.getFirst("count").equalsIgnoreCase("4"));
	}

	@Test
	void shouldPostForObject() {
		final Customer customerCreated = restTemplate.postForObject("/customers", new Customer("potato"), Customer.class);
		assertTrue(customerCreated != null);
		
		final URI entitiyLocation = restTemplate.postForLocation("/customers/location", new Customer("potato"));
		assertTrue(entitiyLocation.getHost() != null);
		
		final ResponseEntity<Customer> entityCreated = restTemplate.postForEntity("/customers/empty", new Customer("potato"), Customer.class);
		
		assertTrue(entityCreated.getStatusCode().is2xxSuccessful());
		assertTrue(entityCreated.getStatusCodeValue() == 204);
	}
	
	@Test
	void shouldPutForObject() {
		restTemplate.put("/customers/{id}", new Customer("potato"), Map.of("id", "1"));
	}
	
	@Test
	void shouldPatchForObject() {
		final Customer customer = restTemplate.patchForObject("/customers/{id}/ff", new Customer("potato"), Customer.class, Map.of("id", "1"));
		assertTrue(customer != null);
	}
	
	@Test
	void shouldDeleteObject() {
		restTemplate.delete("/customers/{id}", Map.of("id", "1"));
	}
	
	@Test
	void shouldUseExecute() {
		HttpStatus response = 
		restTemplate.execute("/customers/{id}", HttpMethod.DELETE, new RequestCallback() {
			
			@Override
			public void doWithRequest(ClientHttpRequest request) throws IOException {
				System.err.println("custom");
				
			}
		}, ClientHttpResponse::getStatusCode, Map.of("id", "1"));
		
		assertTrue(response.is2xxSuccessful());
		assertTrue(response.compareTo(HttpStatus.NO_CONTENT) == 0);
		
		
		ResponseEntity<Customer> cus = restTemplate.execute("/customers", HttpMethod.POST, new RestTemplate().httpEntityCallback(new Customer("name")), new RestTemplate().responseEntityExtractor(Customer.class));
		assertTrue(cus != null);
	}
	
	@Test
	void shouldUseExchange() {
		ResponseEntity<Customer> cus = restTemplate.exchange("/customers", HttpMethod.POST, new HttpEntity<>(new Customer("potato")), Customer.class);
		assertTrue(cus != null);
		
		ResponseEntity<Customer> cus2 = restTemplate.exchange("/customers/{id}", HttpMethod.DELETE, null, Customer.class, Map.of("id", "1"));
		
		assertTrue(cus2.getBody() == null);
		assertTrue(cus2.getStatusCodeValue() == 204);
		
	}


	@Test
	void shouldUseExchangeOptions() {
		restTemplate.optionsForAllow("/customers");
		
	}
	
}
