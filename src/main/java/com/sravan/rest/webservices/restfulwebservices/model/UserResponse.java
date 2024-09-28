package com.sravan.rest.webservices.restfulwebservices.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.sravan.rest.webservices.restfulwebservices.entity.AddressEntity;

public class UserResponse {
	
	private Integer id;
	private String name;
	
	@JsonFormat(pattern = ("dd/MM/yyyy"))
	// @jakarta.persistence.Temporal(TemporalType.DATE)
	// @Temporal(TemporalType.DATE)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate birthDate;
	private String status;
	private List<AddressEntity> addresses;
	
	public UserResponse() {
		super();
	}

	public UserResponse(Integer id, String name, LocalDate birthDate, String status, List<AddressEntity> addresses) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
		this.status = status;
		this.addresses = addresses;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<AddressEntity> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<AddressEntity> addresses) {
		this.addresses = addresses;
	}
	
	
}
