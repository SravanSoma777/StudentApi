package com.sravan.rest.webservices.restfulwebservices.util;

import org.springframework.stereotype.Component;

import com.sravan.rest.webservices.restfulwebservices.entity.UserEntity;
import com.sravan.rest.webservices.restfulwebservices.model.UserRequest;
import com.sravan.rest.webservices.restfulwebservices.model.UserResponse;

@Component
public class UserTransformer {

	public UserEntity mapUserRequestToEntity(UserRequest userRequest, Integer id) {

		UserEntity userEntity = new UserEntity();

		if (userRequest != null) {
			if (id != null) {
				userEntity.setId(id);
			}
			userEntity.setName(userRequest.getName());
			userEntity.setBirthDate(userRequest.getBirthDate());
			userEntity.setStatus(userRequest.getStatus());
			userEntity.setAddresses(userRequest.getAddresses());
		}
		return userEntity;

	}

	public UserResponse mapEntityToUserResponse(UserEntity userEntity) {

		UserResponse userResponse = new UserResponse();

		if (userEntity != null) {
			userResponse.setId(userEntity.getId());
			userResponse.setName(userEntity.getName());
			userResponse.setBirthDate(userEntity.getBirthDate());
			userResponse.setStatus(userEntity.getStatus());
			userResponse.setAddresses(userEntity.getAddresses());
		}
		return userResponse;
	}

	/*
	 * public UserEntity updateUserFromRequest(UserRequest userRequest, UserEntity
	 * userEntity){
	 * 
	 * if (userRequest.getName() != null) {
	 * userEntity.setName(userRequest.getName()); } if (userRequest.getBirthDate()
	 * != null) { userEntity.setBirthDate(userRequest.getBirthDate()); } if
	 * (userRequest.getStatus() != null) {
	 * userEntity.setStatus(userRequest.getStatus()); }
	 * 
	 * return userEntity; }
	 */

	/*
	 * UserResponse userResponse = new UserResponse();
	 * 
	 * if (userRequest != null) { userResponse.setName(userRequest.getName());
	 * userResponse.setBirthDate(userRequest.getBirthDate());
	 * userResponse.setStatus(userRequest.getStatus());
	 * userResponse.setAddresses(userRequest.getAddresses()); } return userResponse;
	 * }
	 */

}
