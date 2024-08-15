package com.sravan.rest.webservices.restfulwebservices.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDaoService implements IUserService {
	
		
	@Autowired
	private UserRepository repository;
	
	public List<UserEntity> findAll() {
        return repository.findAll();
    }

    public UserEntity findOne(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public UserEntity save(UserEntity user) {
        return repository.save(user);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

}
