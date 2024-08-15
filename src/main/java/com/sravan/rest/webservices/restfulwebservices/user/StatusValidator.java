package com.sravan.rest.webservices.restfulwebservices.user;

import java.util.Arrays;
import java.util.List;

import com.sravan.rest.webservices.restfulwebservices.user.exceptionhandling.InvalidStatusException;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StatusValidator implements ConstraintValidator<AllowedStatuses, String> {

	private List<String> AllowedList;

	@Override
	public void initialize(AllowedStatuses AllowedStatuses) {
		AllowedList = Arrays.asList(AllowedStatuses.AllowedValues());
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null || !AllowedList.contains(value.toLowerCase())) {
			throw new InvalidStatusException("Invalid Status: " + value + ". Allowed Values are: " + AllowedList);
		}
		return true;
	}

}
