package com.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rest.exception.MyCustomException;
import com.rest.exception.MyCustomExceptionErrorHandler;

@RestController
@ResponseStatus(code = HttpStatus.NO_CONTENT)
public class ResponseStatusController {

	// curl -X GET "http://localhost:8080/topmethod"
	@GetMapping(value = "/topmethod")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public String responseStatusTopMethod() {
		return "OK";
	}
	
	// curl -X GET "http://localhost:8080/replace" -v
	@GetMapping(value = "/replace")
	@ResponseStatus(code = HttpStatus.ACCEPTED, reason = "I'm replacing the content")
	public String replaceContent() {
		return "OK";
	}
		
	// curl -X GET "http://localhost:8080/exception"
	@GetMapping(value = "/exception")
	public String responseStatusException() {
		throw new MyCustomException();
	}
	
	//curl -X GET "http://localhost:8080/topcontroller"
	@GetMapping(value = "/topcontroller")
	public void responseTopController() {
	}
	
	//curl -X GET "http://localhost:8080/generalerror"
	@GetMapping(value = "/generalerror")
	public void responseGeneralError() {
		throw new MyCustomExceptionErrorHandler();
	}

	//curl -X GET "http://localhost:8080/test/overwritten"
	@ResponseStatus(code =  HttpStatus.ACCEPTED,value = HttpStatus.ACCEPTED)
	@GetMapping(value = "/test/overwritten")
	public ResponseEntity<String> responseoverwritten(){
		return new ResponseEntity<String>("OK", HttpStatus.ALREADY_REPORTED);
	}
	
	@ExceptionHandler(MyCustomExceptionErrorHandler.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "you send something wrong")
	public void somethingWentWrong(final MyCustomExceptionErrorHandler error) {
		System.err.println("");
	}
}
