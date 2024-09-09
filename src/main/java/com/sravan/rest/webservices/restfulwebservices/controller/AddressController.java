package com.sravan.rest.webservices.restfulwebservices.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sravan.rest.webservices.restfulwebservices.entity.AddressEntity;
import com.sravan.rest.webservices.restfulwebservices.service.AddressDaoService;
import com.sravan.rest.webservices.restfulwebservices.util.AddressRequest;
import com.sravan.rest.webservices.restfulwebservices.util.AddressResponse;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

	@Autowired
	private AddressDaoService addressService;

	@PostMapping
	public ResponseEntity<AddressResponse> createAddress(@RequestBody AddressRequest addressRequest) {
		AddressResponse responseDTO = addressService.createAddress(addressRequest);
		return ResponseEntity.ok(responseDTO);
	}
	
	@GetMapping("")
	public List<AddressEntity> Addresses() {
		return addressService.getAllAddresses();
	}

	@GetMapping("/{id}")
	public ResponseEntity<AddressResponse> getAddressById(@PathVariable Long id) {
		Optional<AddressResponse> responseDTO = addressService.getAddressById(id);
		return responseDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PutMapping("/{id}")
	public ResponseEntity<AddressResponse> updateAddress(@PathVariable Long id,
			@RequestBody AddressRequest addressRequest) {
		Optional<AddressResponse> responseDTO = addressService.updateAddress(id, addressRequest);
		return responseDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        boolean isDeleted = addressService.deleteAddress(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
