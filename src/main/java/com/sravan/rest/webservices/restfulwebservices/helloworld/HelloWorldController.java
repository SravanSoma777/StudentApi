package com.sravan.rest.webservices.restfulwebservices.helloworld;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sravan.rest.webservices.restfulwebservices.user.IUserService;
import com.sravan.rest.webservices.restfulwebservices.user.UserEntity;

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
	@GetMapping(path = "/hello-world")
	public String helloWorld() {
		return "Hello World";
	}

	@GetMapping(path = "/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello World");
	}

	@GetMapping(path = "/hello-world/path-variable/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello World, %s", name));
	}

	@GetMapping("/users")
	public List<UserEntity> users() {
		log.info("Entering to get find all users");
		return service.findAll();
	}

	@GetMapping("user/{id}")
	public UserEntity getUserById(@PathVariable Integer id) {
		log.info("retrieving provided id={} details",id);
		return service.findOne(id);
		//log.info("retrieved id {} details", id);
	}

	@PostMapping("/users")
	public UserEntity createUser(@RequestBody UserEntity user) {
		return service.save(user);
	}

	@PutMapping("user/{id}")
	public UserEntity updateUser(@PathVariable Integer id, @RequestBody UserEntity user) {
		
		UserEntity existingUser = service.findOne(id);
		if (existingUser != null) {
			existingUser.setName(user.getName());
			return service.save(existingUser);
		}
		return null;
	}
	
	@DeleteMapping("/user/{id}")
	 public void deleteById(@PathVariable Integer id) {
        service.deleteById(id);
    }
	

}
