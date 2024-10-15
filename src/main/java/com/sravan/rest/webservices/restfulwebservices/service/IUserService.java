package com.sravan.rest.webservices.restfulwebservices.service;

import java.util.List;

import com.sravan.rest.webservices.restfulwebservices.entity.UserEntity;
import com.sravan.rest.webservices.restfulwebservices.model.UserRequest;
import com.sravan.rest.webservices.restfulwebservices.model.UserResponse;

public interface IUserService {

	public List<UserEntity> findAll();

	public UserResponse findOne(Integer id);

	public UserResponse findOneAndUpdate(Integer id, UserRequest userRequest);

	public UserResponse save(UserRequest userRequest);

	public boolean deleteById(Integer id);

}
