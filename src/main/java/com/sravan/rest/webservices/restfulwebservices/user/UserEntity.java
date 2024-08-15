package com.sravan.rest.webservices.restfulwebservices.user;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity(name = "user_details")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank
	@Pattern(regexp = "^[A-Za-z]+([ '-][A-Za-z]+)*$")
	private String name;

	@JsonFormat(pattern = "dd/MM/yyyy")
	// @jakarta.persistence.Temporal(TemporalType.DATE)
	// @Temporal(TemporalType.DATE)
	@JsonProperty("birth_date")
	@NotNull
	private LocalDate birthDate;
	
	@NotNull
	@AllowedStatuses(AllowedValues = { "pending","approved","deleted" })
	private String status;

	public UserEntity() {
		super();
	}

	public UserEntity(Integer id, String name, LocalDate birthDate, String status) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
		this.status = status;
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

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", birthDate=" + birthDate + ", status=" + status + "]";
	}

	

}
