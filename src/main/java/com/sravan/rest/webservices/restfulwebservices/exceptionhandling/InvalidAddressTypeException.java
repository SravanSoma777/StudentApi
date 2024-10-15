package com.sravan.rest.webservices.restfulwebservices.exceptionhandling;

public class InvalidAddressTypeException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidAddressTypeException(String message) {
		super(message);
	}
}
