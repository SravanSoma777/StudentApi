package com.sravan.rest.webservices.restfulwebservices.user.exceptionhandling;

public class InvalidStatusException extends RuntimeException {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidStatusException(String message) {
		super(message);
	}
}
