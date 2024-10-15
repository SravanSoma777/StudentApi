package com.sravan.rest.webservices.restfulwebservices.controllertest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sravan.rest.webservices.restfulwebservices.controller.HelloWorldController;
import com.sravan.rest.webservices.restfulwebservices.entity.UserEntity;
import com.sravan.rest.webservices.restfulwebservices.exceptionhandling.UserNotFoundException;
import com.sravan.rest.webservices.restfulwebservices.model.UserRequest;
import com.sravan.rest.webservices.restfulwebservices.model.UserResponse;
import com.sravan.rest.webservices.restfulwebservices.service.UserDaoService;

public class HelloWorldControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Mock
	private UserDaoService service;

	@InjectMocks
	private HelloWorldController userController;
	
	private ObjectMapper objectMapper;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
		
		objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
	}

	@Test
	public void testGetAllUsers() throws Exception {

		UserEntity user1 = new UserEntity(1, "sravan", null, null, null);
		UserEntity user2 = new UserEntity(2, "reddy", null, null, null);
		List<UserEntity> users = Arrays.asList(user1, user2);
		when(service.findAll()).thenReturn(users);

		mockMvc.perform(get("/users"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$[0].name").value("sravan"))
			.andExpect(jsonPath("$[1].name").value("reddy"));

		verify(service, times(1)).findAll();
	}

	@Test
	public void testGetUserById() throws Exception {

		Integer userId = 1;
		UserResponse userResponse = new UserResponse(userId, "sravan", null, null, null);
		when(service.findOne(userId)).thenReturn(userResponse);

		mockMvc.perform(get("/user/{id}", userId))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id").value(userId))
			.andExpect(jsonPath("$.name").value("sravan"));

		verify(service, times(1)).findOne(userId);
	}
	
	@Test
    void testCreateUser() throws Exception {

		UserRequest userRequest = new UserRequest("sravan",LocalDate.of(1999, 1, 1) , "Approved", Collections.emptyList());
        UserResponse userResponse = new UserResponse(1, "sravan", LocalDate.of(1999, 1, 1), "Approved", Collections.emptyList());

        when(service.save(any(UserRequest.class))).thenReturn(userResponse);
        
        System.out.println("Serialized JSON: " + objectMapper.writeValueAsString(userRequest));

        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userRequest)))
        		.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("sravan"))
                .andExpect(jsonPath("$.birthDate").value("01/01/1999"))
                .andExpect(jsonPath("$.status").value("Approved"));
		 

        verify(service, times(1)).save(any(UserRequest.class));
    }
	
	@Test
    void testUpdateUser_Success() throws Exception {

		UserRequest userRequest = new UserRequest();
        userRequest.setName("John Doe");
        userRequest.setBirthDate(LocalDate.of(1999, 1, 1));
        userRequest.setStatus("Active");
        userRequest.setAddresses(Collections.emptyList());

        UserResponse updatedUserResponse = new UserResponse();
        updatedUserResponse.setId(1);
        updatedUserResponse.setName("sravan");
        updatedUserResponse.setBirthDate(LocalDate.of(1999, 1, 1));
        updatedUserResponse.setStatus("Active");
        updatedUserResponse.setAddresses(Collections.emptyList());

        when(service.findOneAndUpdate(eq(1), any(UserRequest.class))).thenReturn(updatedUserResponse);

        mockMvc.perform(put("/user/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userRequest))) 
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))  
                .andExpect(jsonPath("$.name").value("sravan"))
                .andExpect(jsonPath("$.birthDate").value("01/01/1999"))
                .andExpect(jsonPath("$.status").value("Active"))
                .andExpect(jsonPath("$.addresses").isEmpty())
                .andDo(print()); 
    }

	
	  @Test void testUpdateUser_NotFound() throws Exception {
	  
	  UserRequest userRequest = new UserRequest(); userRequest.setName("sravan");
	  userRequest.setBirthDate(LocalDate.of(1990, 1, 1));
	  userRequest.setStatus("Active");
	  userRequest.setAddresses(Collections.emptyList());
	  
	  when(service.findOneAndUpdate(eq(1), any(UserRequest.class))) .thenThrow(new
	  UserNotFoundException("User not found"));
	  
	  mockMvc.perform(put("/user/{id}", 1) .contentType(MediaType.APPLICATION_JSON)
	  .content(objectMapper.writeValueAsString(userRequest)))
	  .andExpect(status().isNotFound())
	  .andExpect(content().string("User not found at given id 1")) .andDo(print());
	  }
	 
    
	@Test
    void testDeleteUser_Success() throws Exception {
        Integer userId = 1;

        when(service.deleteById(userId)).thenReturn(true);

        mockMvc.perform(delete("/user/{id}", userId))
                .andExpect(status().isOk())  
                .andDo(print());

        verify(service).deleteById(userId);
    }
	
	@Test
    void testDeleteUser_NotFound() throws Exception {
		
        Integer userId = 2;
        String errorMessage = "User not found at given id " + userId;

        when(service.deleteById(userId)).thenThrow(new UserNotFoundException(errorMessage));
       
        mockMvc.perform(delete("/user/{id}", userId))
                .andExpect(status().isNotFound())  
                .andExpect(content().string(errorMessage)) 
                .andDo(print())
                .andReturn();
        
        verify(service).deleteById(userId);
    }
	
	
}
