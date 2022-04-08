package com.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "deu merda")
public class MyCustomException extends RuntimeException {
	private static final long serialVersionUID = 1L;

}
