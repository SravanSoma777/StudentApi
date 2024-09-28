package com.sravan.rest.webservices.restfulwebservices.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sravan.rest.webservices.restfulwebservices.annotation.AllowedAddressTypes;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "address")
public class AddressEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String street;
	
	@NotBlank
	private String city;
	
	@NotNull
	@JsonProperty("address_type")
	@AllowedAddressTypes(addressTypes = {"home","work","school"})
	private String addressType;
	
	@NotBlank
	private String state;
	
	@NotBlank
	private String postalCode;
	
	@NotBlank
	private String country;
	
	
	public AddressEntity() {
		super();
	}

	public AddressEntity(Long id, @NotBlank String street, @NotBlank String city, @NotNull String addressType,
			@NotBlank String state, @NotBlank String postalCode, @NotBlank String country) {
		super();
		this.id = id;
		this.street = street;
		this.city = city;
		this.addressType = addressType;
		this.state = state;
		this.postalCode = postalCode;
		this.country = country;
	}

	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name = "user_id", nullable = false) private UserEntity user;
	 */
	 
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	
	/*
	 * public UserEntity getUser() { return user; }
	 * 
	 * public void setUser(UserEntity user) { this.user = user; }
	 */
	 

}
