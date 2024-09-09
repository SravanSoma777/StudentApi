package com.sravan.rest.webservices.restfulwebservices.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sravan.rest.webservices.restfulwebservices.entity.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer>{

}
