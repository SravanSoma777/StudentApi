package com.sravan.rest.webservices.restfulwebservices.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;


public class UserCustomDaoService implements IUserService {

	private static List<UserEntity> users = new ArrayList<>();

	private static int usersCount = 0;

	static {
	//	users.add(new User(++usersCount, "Adam", LocalDate.now().minusYears(30)));
		//users.add(new User(++usersCount, "Eve", LocalDate.now().minusYears(25)));
	//	users.add(new User(++usersCount, "Jim", LocalDate.now().minusYears(20)));
	//	users.add(new User(++usersCount, "Adam1", LocalDate.now().minusYears(30)));
	//	users.add(new User(++usersCount, "Eve1", LocalDate.now().minusYears(25)));
	//	users.add(new User(++usersCount, "Jim1", LocalDate.now().minusYears(20)));
	}

	@Override
	public List<UserEntity> findAll() {
		return users;
	}
	
	@Override
	public UserEntity findOne(Integer id) {
    	Predicate<? super UserEntity> predicate = user -> user.getId().equals(id); 
    	return users.stream().filter(predicate).findFirst().get();
    }
    
	@Override
    public UserEntity save(UserEntity user) {
    	user.setId(++usersCount);
    	users.add(user);
    	return user;
    }
    
	@Override
    public void deleteById(Integer id) {
    	Predicate<? super UserEntity> predicate = user -> user.getId().equals(id); 
    	users.removeIf(predicate);
    }

}
