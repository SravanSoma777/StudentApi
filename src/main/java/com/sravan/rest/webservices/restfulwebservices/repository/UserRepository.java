package com.sravan.rest.webservices.restfulwebservices.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sravan.rest.webservices.restfulwebservices.entity.UserEntity;
import com.sravan.rest.webservices.restfulwebservices.model.UserResponse;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer>{

	UserResponse save(UserResponse savedUpdatedUser);

}
