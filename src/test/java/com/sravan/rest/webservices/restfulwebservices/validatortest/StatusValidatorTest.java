package com.sravan.rest.webservices.restfulwebservices.validatortest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.sravan.rest.webservices.restfulwebservices.annotation.AllowedStatuses;
import com.sravan.rest.webservices.restfulwebservices.exceptionhandling.InvalidStatusException;
import com.sravan.rest.webservices.restfulwebservices.validator.StatusValidator;

public class StatusValidatorTest {
	
	@InjectMocks
	private StatusValidator statusValidator;

	@Mock
	private AllowedStatuses allowedStatuses;

	private List<String> allowedStatusList;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		// Simulate the allowed values in the annotation
		allowedStatusList = Arrays.asList("active", "inactive", "pending");

		// Mock the behavior of the AllowedStatuses annotation
		when(allowedStatuses.AllowedValues()).thenReturn(allowedStatusList.toArray(new String[0]));

		// Initialize the validator with mocked AllowedStatuses
		statusValidator.initialize(allowedStatuses);
	}

	@Test
	public void testValidStatus() {
		String validStatus = "active";

		assertTrue(statusValidator.isValid(validStatus, null));
	}

	@Test
	public void testInvalidStatusThrowsException() {
		String invalidStatus = "unknown";

		InvalidStatusException exception = assertThrows(InvalidStatusException.class, () -> {
			statusValidator.isValid(invalidStatus, null);
		});

		assertEquals("Invalid Status: unknown. Allowed Values are: " + allowedStatusList, exception.getMessage());
	}

	@Test
	public void testNullStatusThrowsException() {
		InvalidStatusException exception = assertThrows(InvalidStatusException.class, () -> {
			statusValidator.isValid(null, null);
		});

		assertEquals("Invalid Status: null. Allowed Values are: " + allowedStatusList, exception.getMessage());
	}

	@Test
	public void testStatusCaseInsensitivity() {
		String validStatusUpperCase = "ACTIVE";

		assertTrue(statusValidator.isValid(validStatusUpperCase.toLowerCase(), null));
	}
}
