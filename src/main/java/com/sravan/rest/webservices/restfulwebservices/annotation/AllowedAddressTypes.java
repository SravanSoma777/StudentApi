package com.sravan.rest.webservices.restfulwebservices.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import com.sravan.rest.webservices.restfulwebservices.validator.*;

@Constraint(validatedBy = AddressTypeValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface AllowedAddressTypes {
	
	String message() default "Invalid Address Type, Allowed Address Types are: {AddressTypes}.";
	Class<?>[] groups() default {};
	Class<? extends Payload> [] payload() default {};
	String [] addressTypes();

}
