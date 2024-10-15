package com.sravan.rest.webservices.restfulwebservices.exceptionhandling;

public class ErrorResponse {
	
	private int statusCode;
	private String message;
	private String description;

	public ErrorResponse(int statusCode, String message, String description) {
		super();
		this.statusCode = statusCode;
		this.message = message;
		this.description = description;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
