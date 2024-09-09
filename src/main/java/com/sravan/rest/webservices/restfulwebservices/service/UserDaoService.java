package com.sravan.rest.webservices.restfulwebservices.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sravan.rest.webservices.restfulwebservices.entity.UserEntity;
import com.sravan.rest.webservices.restfulwebservices.repository.UserRepository;
import com.sravan.rest.webservices.restfulwebservices.util.UserRequest;
import com.sravan.rest.webservices.restfulwebservices.util.UserResponse;
import com.sravan.rest.webservices.restfulwebservices.util.UserTransformer;

import jakarta.transaction.Transactional;

@Service
public class UserDaoService implements IUserService {
	
		
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private UserTransformer userTransformer;
	

	@Override
	public List<UserEntity> findAll() {
	System.out.println(((List<UserEntity>) repository.findAll()).size());
	
	return (List<UserEntity>) repository.findAll();
	}

	@Override
	public Optional<UserResponse> findOne(Integer id) {
		//return repository.findById(id).map(userMapper::toResponseDTO);
	
		return Optional.empty();
	}

	@Override
	@Transactional
	public UserResponse save(UserRequest userRequest) {
		 
		UserEntity userEntity =  userTransformer.mapUserRequestToEntity(userRequest);
		UserEntity savedUserEntity =  repository.save(userEntity);
		 
		return userTransformer.mapEntityToUserResponse(savedUserEntity);
	}

	@Override
	public boolean deleteById(Integer id) {
		if (repository.existsById(id)) {
			repository.deleteById(id);
			return true;
		}
		return false;
	}
	
	/*
	 * public List<UserEntity> findAll() { return repository.findAll(); }
	 * 
	 * public UserEntity findOne(Integer id) { return
	 * repository.findById(id).orElse(null); }
	 * 
	 * public UserEntity save(UserEntity user) { return repository.save(user); }
	 * 
	 * public void deleteById(Integer id) { repository.deleteById(id); }
	 */

}
