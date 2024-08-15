package com.sravan.rest.webservices.restfulwebservices.user;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = StatusValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface AllowedStatuses {
	
 
	String message() default "Invalid Status, Allowed Statuses are: {AllowedValues}.";
	Class<?>[] groups() default {};
	Class<? extends Payload> [] payload() default {};
	String [] AllowedValues();
}
