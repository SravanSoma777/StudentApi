package com.sravan.rest.webservices.restfulwebservices.service;

import java.util.List;
import java.util.Optional;

import com.sravan.rest.webservices.restfulwebservices.entity.UserEntity;
import com.sravan.rest.webservices.restfulwebservices.util.UserRequest;
import com.sravan.rest.webservices.restfulwebservices.util.UserResponse;

public interface IUserService {
	
	public List<UserEntity> findAll();
	
    public Optional<UserResponse> findOne(Integer id);

    public UserResponse save(UserRequest userRequest);
    
    public boolean deleteById(Integer id);
    


}
