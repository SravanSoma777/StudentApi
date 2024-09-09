package com.sravan.rest.webservices.restfulwebservices.service;

import java.util.List;
import java.util.Optional;

import com.sravan.rest.webservices.restfulwebservices.entity.AddressEntity;
import com.sravan.rest.webservices.restfulwebservices.util.AddressRequest;
import com.sravan.rest.webservices.restfulwebservices.util.AddressResponse;

public interface IAddressService {
	
	public AddressResponse createAddress(AddressRequest addressRequestDTO);
	
	public List<AddressEntity> getAllAddresses();
	
	public Optional<AddressResponse> getAddressById(Long id);
	
	public Optional<AddressResponse> updateAddress(Long id, AddressRequest addressRequestDTO);
	
	public boolean deleteAddress(Long id);

}
