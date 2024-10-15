package com.sravan.rest.webservices.restfulwebservices.model;

public class AddressRequest {

	private String street;
	private String city;
	private String addressType;
	private String state;
	private String postalCode;
	private String country;

	public AddressRequest() {
		super();
	}

	public AddressRequest(String street, String city, String addressType, String state, String postalCode,
			String country) {
		super();
		this.street = street;
		this.city = city;
		this.addressType = addressType;
		this.state = state;
		this.postalCode = postalCode;
		this.country = country;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddressType() {
		return addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
