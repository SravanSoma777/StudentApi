package com.sravan.rest.webservices.restfulwebservices.validatortest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.sravan.rest.webservices.restfulwebservices.annotation.AllowedAddressTypes;
import com.sravan.rest.webservices.restfulwebservices.exceptionhandling.InvalidAddressTypeException;
import com.sravan.rest.webservices.restfulwebservices.validator.AddressTypeValidator;

import jakarta.validation.ConstraintValidatorContext;

public class AddressTypeValidatorTest {

	
	private AddressTypeValidator addressTypeValidator;  // Assuming this is your class name
	 
	private AllowedAddressTypes allowedAddressTypesMock;
	
    private ConstraintValidatorContext contextMock;

	    @BeforeEach
	    void setUp() {
	        addressTypeValidator = new AddressTypeValidator();
	        allowedAddressTypesMock = Mockito.mock(AllowedAddressTypes.class);
	        
	        contextMock = mock(ConstraintValidatorContext.class);

	        // Initialize the listOfAllowedAddressTypes with sample data
	        when(allowedAddressTypesMock.addressTypes()).thenReturn(new String[]{"home","work","school"});
	        addressTypeValidator.initialize(allowedAddressTypesMock);
	    }

	    @Test
	    void testInitialize() {

	    	when(allowedAddressTypesMock.addressTypes()).thenReturn(new String[]{"home","work","school"});

	        addressTypeValidator.initialize(allowedAddressTypesMock);

	        List<String> expectedAddressTypes = Arrays.asList("home","work","school");
	        assertEquals(expectedAddressTypes, addressTypeValidator.getListOfAllowedAddressTypes());

	        assertFalse(addressTypeValidator.getListOfAllowedAddressTypes().isEmpty());
	        assertTrue(addressTypeValidator.getListOfAllowedAddressTypes().contains("work"));
	    }
	    
	    @Test
	    void testIsValid_ValidAddressType() {

	    	String validType = "home";
	        boolean result = addressTypeValidator.isValid(validType, contextMock);
	        assertTrue(result, "Expected valid type to return true");
	    }

	    @Test
	    void testIsValid_InvalidAddressType() {

	    	String invalidType = "invalid";
	        
	        Exception exception = assertThrows(InvalidAddressTypeException.class, () -> {
	            addressTypeValidator.isValid(invalidType, contextMock);
	        });

	        String expectedMessage = "Invalid Address Type";
	        assertTrue(exception.getMessage().contains(expectedMessage));
	    }

	    @Test
	    void testIsValid_NullValue() {
	    	
	    	String nullType=null;
	    	
	    	Exception exception = assertThrows(InvalidAddressTypeException.class, () -> {
	            addressTypeValidator.isValid(nullType, contextMock);
	        });

	        String expectedMessage = "Address Type is null";
	        assertFalse(exception.getMessage().contains(expectedMessage));
			/*
			 * String nullValue = null; boolean result =
			 * addressTypeValidator.isValid(nullValue, contextMock); assertFalse(result,
			 * "Expected null value to return false");
			 */
	    }

	    
}
