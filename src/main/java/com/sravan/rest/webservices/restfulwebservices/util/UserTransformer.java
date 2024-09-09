package com.sravan.rest.webservices.restfulwebservices.util;

import org.springframework.stereotype.Component;

import com.sravan.rest.webservices.restfulwebservices.entity.UserEntity;

@Component
public class UserTransformer {
	
	public UserEntity mapUserRequestToEntity(UserRequest userRequest) {
		UserEntity userEntity = new UserEntity();

		if(userRequest != null) {
			userEntity.setName(userRequest.getName());
			userEntity.setBirthDate(userRequest.getBirthDate());
			userEntity.setStatus(userRequest.getStatus());
			userEntity.setAddresses(userRequest.getAddresses());
		}
		return userEntity;
		
	}
	
	public UserResponse mapEntityToUserResponse(UserEntity userEntity) {
		
		UserResponse userResponse = new UserResponse();
		if(userEntity != null) {
			userResponse.setId(userEntity.getId());
			userResponse.setName(userEntity.getName());
			userResponse.setBirthDate(userEntity.getBirthDate());
			userResponse.setStatus(userEntity.getStatus());
			userResponse.setAddresses(userEntity.getAddresses());
		}
		return userResponse;
	}

}
