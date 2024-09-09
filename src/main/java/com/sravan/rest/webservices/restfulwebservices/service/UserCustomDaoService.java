package com.sravan.rest.webservices.restfulwebservices.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import com.sravan.rest.webservices.restfulwebservices.entity.UserEntity;
import com.sravan.rest.webservices.restfulwebservices.util.UserRequest;
import com.sravan.rest.webservices.restfulwebservices.util.UserResponse;


public class UserCustomDaoService implements IUserService {

	private static List<UserEntity> users = new ArrayList<>();

	private static int usersCount = 0;

	@Override
	public List<UserEntity> findAll() {
		return users;
	}
	
	@Override
	public Optional<UserResponse> findOne(Integer id) {
    	Predicate<? super UserEntity> predicate = user -> user.getId().equals(id); 
    	return Optional.empty();
    }
    
	@Override
    public UserResponse save(UserRequest userRequest) {
		return null;
    	
    }
    
	@Override
    public boolean deleteById(Integer id) {
    	Predicate<? super UserEntity> predicate = user -> user.getId().equals(id); 
    	return users.removeIf(predicate);
    }


}
