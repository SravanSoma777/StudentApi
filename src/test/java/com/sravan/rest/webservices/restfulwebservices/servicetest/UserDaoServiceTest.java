package com.sravan.rest.webservices.restfulwebservices.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.sravan.rest.webservices.restfulwebservices.entity.UserEntity;
import com.sravan.rest.webservices.restfulwebservices.exceptionhandling.UserNotFoundException;
import com.sravan.rest.webservices.restfulwebservices.model.UserRequest;
import com.sravan.rest.webservices.restfulwebservices.model.UserResponse;
import com.sravan.rest.webservices.restfulwebservices.repository.UserRepository;
import com.sravan.rest.webservices.restfulwebservices.service.UserDaoService;
import com.sravan.rest.webservices.restfulwebservices.util.UserTransformer;

public class UserDaoServiceTest {

	
	@Mock
    private UserRepository repository;

	@Mock
	private UserTransformer userTransformer;
	
    @InjectMocks
    private UserDaoService userService;

    private UserEntity user1;
    private UserEntity user2;
    
    private UserEntity userEntity;
    private UserResponse userResponse;
    
    private UserRequest userRequest;
    private UserEntity userEntityForSave;
    private UserEntity savedUserEntity;
    private UserResponse userResponseForSave;
    
    private UserRequest userRequestForUpdate;
    private UserEntity existingUserEntity;
    private UserEntity updatedUserEntity;
    private UserResponse userResponseForUpdate;


	
	  @BeforeEach
	  void setUp() {
		  
		  MockitoAnnotations.openMocks(this);
	  
	  }
	 

    @Test
    void testFindAll() {
    	
        MockitoAnnotations.openMocks(this);
        
    	user1 = new UserEntity(1, "sravan", null, "Pending", null);  
        user2 = new UserEntity(2, "reddy", null, "Approved", null);
    	
        List<UserEntity> userList = Arrays.asList(user1, user2);
        when(repository.findAll()).thenReturn(userList);

        List<UserEntity> result = userService.findAll();

        assertEquals(2, result.size());
        assertEquals("sravan", result.get(0).getName());
        assertEquals("reddy", result.get(1).getName());
    }
    
    @Test
    void testFindOne() {
    	
    	MockitoAnnotations.openMocks(this);

        userEntity = new UserEntity(1, "sravan", null, "Approved", null);
        userResponse = new UserResponse(1, "sravan", null, "Approved", null);
        
        when(repository.findById(1)).thenReturn(Optional.of(userEntity));

        when(userTransformer.mapEntityToUserResponse(userEntity)).thenReturn(userResponse);

        UserResponse result = userService.findOne(1);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("sravan", result.getName());
        assertEquals("Approved", result.getStatus());
        
        when(repository.findById(2)).thenReturn(Optional.empty());
        
        UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> {
            userService.findOne(2);
        });

        verify(repository, times(1)).findById(2);
        assertEquals("provided id 2 not found", exception.getMessage());
    }
    
    @Test
    void testSave() {
    	
    	MockitoAnnotations.openMocks(this); 

        userRequest = new UserRequest("sravan", null, "Approved", null);
        userEntityForSave = new UserEntity(null,"sravan", null, "Approved", null);
        savedUserEntity = new UserEntity(1,"sravan", null, "Approved", null);
        userResponseForSave = new UserResponse(1, "sravan", null, "Approved", null);

        when(userTransformer.mapUserRequestToEntity(userRequest, null)).thenReturn(userEntityForSave);

        when(repository.save(userEntityForSave)).thenReturn(savedUserEntity);

        when(userTransformer.mapEntityToUserResponse(savedUserEntity)).thenReturn(userResponseForSave);

        UserResponse result = userService.save(userRequest);

        verify(userTransformer, times(1)).mapUserRequestToEntity(userRequest, null);
        verify(repository, times(1)).save(userEntityForSave);
        verify(userTransformer, times(1)).mapEntityToUserResponse(savedUserEntity);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("sravan", result.getName());
        assertEquals("Approved", result.getStatus());
    }
    
    @Test
    void testDeleteById() {
    	
    	MockitoAnnotations.openMocks(this); 

    	when(repository.existsById(1)).thenReturn(true);

        boolean result = userService.deleteById(1);

        verify(repository, times(1)).existsById(1);
        verify(repository, times(1)).deleteById(1);

        assertTrue(result);
        
        reset(repository);

        when(repository.existsById(2)).thenReturn(false);

        UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> {
            userService.deleteById(2);
        });

        verify(repository, times(1)).existsById(2);
        verify(repository, never()).deleteById(anyInt());
        assertEquals("user with Id 2 not found", exception.getMessage());
    }
    
    @Test
    void testFindOneAndUpdate() {
    	
    	MockitoAnnotations.openMocks(this);

        userRequestForUpdate = new UserRequest("UpdatedName", null, "UpdatedStatus", null);
        existingUserEntity = new UserEntity(1, "ExistingName", null, "existingstatus", null);
        updatedUserEntity = new UserEntity(1, "UpdatedName", null, "UpdatedStatus", null);
        userResponseForUpdate = new UserResponse(1, "UpdatedName", null, "UpdatedStatus", null);
        
        
        when(repository.findById(1)).thenReturn(Optional.of(existingUserEntity));
        when(userTransformer.mapUserRequestToEntity(userRequestForUpdate, 1)).thenReturn(updatedUserEntity);
        when(repository.save(updatedUserEntity)).thenReturn(updatedUserEntity);
        when(userTransformer.mapEntityToUserResponse(updatedUserEntity)).thenReturn(userResponseForUpdate);

        UserResponse result = userService.findOneAndUpdate(1, userRequestForUpdate);

        verify(repository, times(1)).findById(1);
        verify(userTransformer, times(1)).mapUserRequestToEntity(userRequestForUpdate, 1);
        verify(repository, times(1)).save(updatedUserEntity);
        verify(userTransformer, times(1)).mapEntityToUserResponse(updatedUserEntity);

        assertNotNull(result);
        assertEquals("UpdatedName", result.getName());
        assertEquals("UpdatedStatus", result.getStatus());

        reset(repository);
        reset(userTransformer);

        when(repository.findById(2)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userService.findOneAndUpdate(2, userRequestForUpdate);
        });

        verify(repository, times(1)).findById(2);
        verify(repository, never()).save(any(UserEntity.class));
        assertEquals("User not found with id: 2", exception.getMessage());
    }
}

