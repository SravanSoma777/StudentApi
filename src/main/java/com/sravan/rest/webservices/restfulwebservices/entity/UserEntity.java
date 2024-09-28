package com.sravan.rest.webservices.restfulwebservices.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.sravan.rest.webservices.restfulwebservices.annotation.AllowedStatuses;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "user")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank
	@Pattern(regexp = "^[A-Za-z]+([ '-][A-Za-z]+)*$")
	private String name;

	@JsonFormat(pattern = ("dd/MM/yyyy"))
	// @jakarta.persistence.Temporal(TemporalType.DATE)
	// @Temporal(TemporalType.DATE)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@NotNull
	private LocalDate birthDate;

	@NotNull
	@AllowedStatuses(AllowedValues = { "pending", "approved", "deleted" })
	private String status;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "user_id")
	private List<AddressEntity> addresses = new ArrayList<>();
	
	public UserEntity() {
		super();
	}

	public UserEntity(Integer id, @NotBlank @Pattern(regexp = "^[A-Za-z]+([ '-][A-Za-z]+)*$") String name,
			@NotNull LocalDate birthDate, @NotNull String status, List<AddressEntity> addresses) {
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

	@Override
	public String toString() {
		return "UserEntity [id=" + id + ", name=" + name + ", birthDate=" + birthDate + ", status=" + status
				+ ", addresses=" + addresses + "]";
	}
	
	

	/*
	 * public void addAddress(AddressEntity address) { address.setUser(this); //
	 * Ensure the relationship is set on the Address side
	 * this.addresses.add(address); }
	 * 
	 * public void removeAddress(AddressEntity address) { address.setUser(null); //
	 * Break the relationship when removing this.addresses.remove(address); }
	 */

}
