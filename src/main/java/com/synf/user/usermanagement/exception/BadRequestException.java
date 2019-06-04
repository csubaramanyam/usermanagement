package com.synf.user.usermanagement.exception;

import org.springframework.validation.FieldError;

public class BadRequestException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public BadRequestException(FieldError fieldError) {
		super(fieldError.getDefaultMessage());
	}
	
}
