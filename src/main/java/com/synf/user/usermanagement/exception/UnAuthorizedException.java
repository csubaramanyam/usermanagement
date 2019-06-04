package com.synf.user.usermanagement.exception;

public class UnAuthorizedException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public UnAuthorizedException() {
		super();
	}

	public UnAuthorizedException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnAuthorizedException(String message) {
		super(message);
	}
}
