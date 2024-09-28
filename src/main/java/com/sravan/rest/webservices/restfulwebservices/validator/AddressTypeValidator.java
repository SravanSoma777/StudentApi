package com.sravan.rest.webservices.restfulwebservices.validator;

import java.util.Arrays;
import java.util.List;

import com.sravan.rest.webservices.restfulwebservices.annotation.AllowedAddressTypes;
import com.sravan.rest.webservices.restfulwebservices.exceptionhandling.InvalidAddressTypeException;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AddressTypeValidator implements ConstraintValidator<AllowedAddressTypes, String>{

	private List<String> listOfAllowedAddressTypes;

	public List<String> getListOfAllowedAddressTypes() {
		return listOfAllowedAddressTypes;
	}

	public void setListOfAllowedAddressTypes(List<String> listOfAllowedAddressTypes) {
		this.listOfAllowedAddressTypes = listOfAllowedAddressTypes;
	}

	@Override
	public void initialize(AllowedAddressTypes allowedAddressTypes) {
		listOfAllowedAddressTypes = Arrays.asList(allowedAddressTypes.addressTypes());
		System.out.println("Address types List Initiated");
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		
		if (value == null || !listOfAllowedAddressTypes.contains(value.toLowerCase())) {
			throw new InvalidAddressTypeException("Invalid Address Type: " + value + ". Allowed Values are: " + listOfAllowedAddressTypes);
		}
		return listOfAllowedAddressTypes.contains(value.toLowerCase());
	}

		
}
