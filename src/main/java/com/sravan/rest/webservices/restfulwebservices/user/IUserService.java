package com.sravan.rest.webservices.restfulwebservices.user;

import java.util.List;

public interface IUserService {

	public List<UserEntity> findAll();
	
	public UserEntity findOne(Integer id);
	
	public UserEntity save(UserEntity user);
	
	public void deleteById(Integer id);
}
