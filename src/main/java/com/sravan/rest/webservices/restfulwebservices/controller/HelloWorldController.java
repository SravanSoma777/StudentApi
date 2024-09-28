package com.sravan.rest.webservices.restfulwebservices.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sravan.rest.webservices.restfulwebservices.entity.UserEntity;
import com.sravan.rest.webservices.restfulwebservices.exceptionhandling.UserNotFoundException;
import com.sravan.rest.webservices.restfulwebservices.model.UserRequest;
import com.sravan.rest.webservices.restfulwebservices.model.UserResponse;
import com.sravan.rest.webservices.restfulwebservices.service.IUserService;

@RestController
//@RequestMapping("")
public class HelloWorldController {

	Logger log = LoggerFactory.getLogger(HelloWorldController.class);

	@Autowired
	@Qualifier("userDaoService")
	private IUserService service;

	// public HelloWorldController(UserDaoService service1) {
	// this.service = service1;
	// }
	/*
	 * @GetMapping(path = "/hello-world") public String helloWorld() { return
	 * "Hello World"; }
	 */

	@GetMapping("/users")
	public List<UserEntity> users() {
		log.info("Entering to get find all users");
		return service.findAll();
	}

	@GetMapping("user/{id}")
	public UserResponse getUserById(@PathVariable Integer id) {
		log.info("retrieving provided id={} details", id);
		return service.findOne(id);
		// log.info("retrieved id {} details", id);
	}

	@PostMapping("/users")
	public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest) {
		UserResponse responseDTO = service.save(userRequest);
		return ResponseEntity.ok(responseDTO);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PutMapping("user/{id}")
	public ResponseEntity<UserResponse> updateUser(@PathVariable Integer id, @RequestBody UserRequest userRequest) {

		try {
			UserResponse updatedUser = service.findOneAndUpdate(id, userRequest);
			log.info("updated user "+ updatedUser.toString());
			return ResponseEntity.ok(updatedUser);
		} catch (UserNotFoundException e) {
			/*
			 * ResponseEntity<> responseCatch = ResponseEntity.status(HttpStatus.NOT_FOUND)
			 * .body("User not found at given id " + id);
			 */
			 return new ResponseEntity("User not found at given id " + id, HttpStatusCode.valueOf(HttpStatus.NOT_FOUND.value()));
		}

		/*
		 * UserResponse existingUser = service.findOne(id);
		 * 
		 * if (existingUser != null) { existingUser.setName(userRequest.getName());
		 * return service.save(existingUser); }
		 * 
		 * return null;
		 * 
		 */

	}

	@DeleteMapping("/user/{id}")
	public ResponseEntity<String> deleteById(@PathVariable Integer id) {
	    try {
	        service.deleteById(id);
	        return ResponseEntity.status(HttpStatus.OK).build();
	    } catch (UserNotFoundException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                             .body("User not found at given id " + id);  
	    }
	 }
}
