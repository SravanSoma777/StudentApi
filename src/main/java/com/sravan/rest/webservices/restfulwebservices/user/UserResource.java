package com.sravan.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;


public class UserResource {

	@Autowired
	private IUserService service;

	//public UserResource(UserDaoService service) {
		//this.service = service;
	//}

	@GetMapping("/users")
	public List<UserEntity> retrieveAllUsers() {
		return service.findAll();
	}

	@GetMapping("/user/{id}")
	public UserEntity retrieveUser(@PathVariable int id) {
		return service.findOne(id);
	}

	@PostMapping("users")
	public ResponseEntity<UserEntity> createUser(@Valid  @RequestBody UserEntity user) {
		UserEntity savedUser = service.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/user/{id}")
	public void deleteUser(@PathVariable int id) {
		 service.deleteById(id);
	}
}
