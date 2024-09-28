package com.sravan.rest.webservices.restfulwebservices.exceptionhandling;

public class InvalidStatusException extends RuntimeException {
	
	@SuppressWarnings("unused")
	private String message;
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidStatusException(String message) {
		super(message);
		this.message = message;
	}
}
