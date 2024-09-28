package com.sravan.rest.webservices.restfulwebservices.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sravan.rest.webservices.restfulwebservices.entity.UserEntity;
import com.sravan.rest.webservices.restfulwebservices.exceptionhandling.UserNotFoundException;
import com.sravan.rest.webservices.restfulwebservices.model.UserRequest;
import com.sravan.rest.webservices.restfulwebservices.model.UserResponse;
import com.sravan.rest.webservices.restfulwebservices.repository.UserRepository;
import com.sravan.rest.webservices.restfulwebservices.util.UserTransformer;

import jakarta.transaction.Transactional;

@Service
public class UserDaoService implements IUserService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private UserTransformer userTransformer;

	public UserDaoService(UserTransformer userTransformer2) {
	}

	@Override
	public List<UserEntity> findAll() {
		System.out.println(((List<UserEntity>) repository.findAll()).size());

		return (List<UserEntity>) repository.findAll();
	}

	@Override
	public UserResponse findOne(Integer id) {

		Optional<UserEntity> optUserEntity = repository.findById(id);
		if (optUserEntity.isEmpty()) {

			throw new UserNotFoundException(String.format("provided id %d not found", id));
		}
		System.out.println(optUserEntity.toString());

		UserEntity user = optUserEntity.get();
		return userTransformer.mapEntityToUserResponse(user);
	}

	@Override
	@Transactional
	public UserResponse save(UserRequest userRequest) {

		UserEntity userEntity = userTransformer.mapUserRequestToEntity(userRequest, null);
		UserEntity savedUserEntity = repository.save(userEntity);

		return userTransformer.mapEntityToUserResponse(savedUserEntity);
	}

	@Override
	public boolean deleteById(Integer id) {
		
		boolean existsById = repository.existsById(id);
		if (existsById) {
			repository.deleteById(id);
		} else {
			throw new UserNotFoundException("user with Id " + id + " not found");
		}
		return existsById;

	}

	@Override
	public UserResponse findOneAndUpdate(Integer id, UserRequest userRequest) {

		Optional<UserEntity> userToBeUpdated = repository.findById(id);

		if (userToBeUpdated.isPresent()) {

			UserEntity mapUserRequestToEntity = userTransformer.mapUserRequestToEntity(userRequest, id);
			UserEntity updatedUser = repository.save(mapUserRequestToEntity);

			return userTransformer.mapEntityToUserResponse(updatedUser);
		} else {
			throw new RuntimeException("User not found with id: " + id);
		}
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
