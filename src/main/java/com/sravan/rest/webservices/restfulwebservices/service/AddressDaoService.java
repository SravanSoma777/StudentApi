package com.sravan.rest.webservices.restfulwebservices.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sravan.rest.webservices.restfulwebservices.entity.AddressEntity;
import com.sravan.rest.webservices.restfulwebservices.repository.AddressRepository;
import com.sravan.rest.webservices.restfulwebservices.util.AddressRequest;
import com.sravan.rest.webservices.restfulwebservices.util.AddressResponse;

@Service
public class AddressDaoService implements IAddressService {

	@Override
	public AddressResponse createAddress(AddressRequest addressRequestDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AddressEntity> getAllAddresses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<AddressResponse> getAddressById(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<AddressResponse> updateAddress(Long id, AddressRequest addressRequest) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public boolean deleteAddress(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * @Autowired private AddressRepository addressRepository;
	 * 
	 * @Autowired private Object addressMapper;
	 * 
	 * @Override public AddressResponseDTO createAddress(AddressRequestDTO
	 * addressRequestDTO) { AddressEntity address =
	 * addressMapper.toEntity(addressRequestDTO); AddressEntity savedAddress =
	 * addressRepository.save(address); return
	 * addressMapper.toResponseDTO(savedAddress); }
	 * 
	 * @Override public Optional<AddressResponseDTO> getAddressById(Long id) {
	 * return addressRepository.findById(id).map(addressMapper::toResponseDTO); }
	 * 
	 * @Override public List<AddressEntity> getAllAddresses() { return
	 * addressRepository.findAll(); }
	 * 
	 * @Override public Optional<AddressResponseDTO> updateAddress(Long id,
	 * AddressRequestDTO addressRequestDTO) { return
	 * addressRepository.findById(id).map(existingAddress -> {
	 * existingAddress.setStreet(addressRequestDTO.getStreet());
	 * existingAddress.setCity(addressRequestDTO.getStreet());
	 * existingAddress.setAddressType(addressRequestDTO.getStreet());
	 * existingAddress.setState(addressRequestDTO.getStreet());
	 * existingAddress.setPostalCode(addressRequestDTO.getStreet());
	 * existingAddress.setCountry(addressRequestDTO.getStreet()); AddressEntity
	 * updatedAddress = addressRepository.save(existingAddress); return
	 * addressMapper.toResponseDTO(updatedAddress); }); }
	 * 
	 * @Override public boolean deleteAddress(Long id) { if
	 * (addressRepository.existsById(id)) { addressRepository.deleteById(id); return
	 * true; } return false; }
	 */

}
