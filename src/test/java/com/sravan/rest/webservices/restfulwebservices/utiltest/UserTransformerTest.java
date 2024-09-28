package com.sravan.rest.webservices.restfulwebservices.utiltest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import com.sravan.rest.webservices.restfulwebservices.entity.UserEntity;
import com.sravan.rest.webservices.restfulwebservices.model.UserRequest;
import com.sravan.rest.webservices.restfulwebservices.model.UserResponse;
import com.sravan.rest.webservices.restfulwebservices.util.UserTransformer;

public class UserTransformerTest {

	/*
	 * @Mock private UserRepository userRepository;
	 * 
	 * @Mock private UserTransformer userTransformer;
	 * 
	 * @InjectMocks private UserDaoService userService;
	 */
	
	private final UserTransformer userTransformer = new UserTransformer();

	/*
	 * @BeforeEach void setUp() { MockitoAnnotations.openMocks(this); }
	 */

	@Test
    void testMapUserRequestToEntity_WithId() {
		
		UserRequest userRequest = new UserRequest("sravan", LocalDate.of(1990, 1, 1), "Approved", Collections.emptyList());
        Integer id = 1;

        UserEntity result = userTransformer.mapUserRequestToEntity(userRequest, id);

        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals("sravan", result.getName());
        assertEquals(LocalDate.of(1990, 1, 1), result.getBirthDate());
        assertEquals("Approved", result.getStatus());
        assertEquals(Collections.emptyList(), result.getAddresses());
    }

    @Test
    void testMapUserRequestToEntity_WithoutId() {
    	
    	UserRequest userRequest = new UserRequest("sravan", LocalDate.of(1985, 5, 15), "pending", Collections.emptyList());
        Integer id = null;

        UserEntity result = userTransformer.mapUserRequestToEntity(userRequest, id);

        assertNotNull(result);
        assertNull(result.getId()); 
        assertEquals("sravan", result.getName());
        assertEquals(LocalDate.of(1985, 5, 15), result.getBirthDate());
        assertEquals("pending", result.getStatus());
        assertEquals(Collections.emptyList(), result.getAddresses());
    }
    
    @Test
    public void testMapUserRequestToEntity_NullRequest() {
        // When
        UserEntity result = userTransformer.mapUserRequestToEntity(null,1);

        // Then
        assertNotNull(result); // Entity is returned, but with no values
        assertNull(result.getName());
        assertNull(result.getBirthDate());
        assertNull(result.getStatus());
        assertTrue(result.getAddresses().isEmpty());
        assertNull(result.getId()); // No id is set if the request is null
    }
    
    @Test
    void testMapEntityToUserResponse() {

    	UserEntity userEntity = new UserEntity();
        userEntity.setId(1);
        userEntity.setName("sravan");
        userEntity.setBirthDate(LocalDate.of(1990, 1, 1));
        userEntity.setStatus("Approved");
        userEntity.setAddresses(Collections.emptyList());

        UserResponse result = userTransformer.mapEntityToUserResponse(userEntity);

        assertNotNull(result);
        assertEquals(userEntity.getId(), result.getId());
        assertEquals(userEntity.getName(), result.getName());
        assertEquals(userEntity.getBirthDate(), result.getBirthDate());
        assertEquals(userEntity.getStatus(), result.getStatus());
        assertEquals(userEntity.getAddresses(), result.getAddresses());
    }

    @Test
    void testMapEntityToUserResponse_NullEntity() {

    	UserEntity userEntity = null;

        UserResponse result = userTransformer.mapEntityToUserResponse(userEntity);

        assertNotNull(result);
        assertNull(result.getId());
        assertNull(result.getName());
        assertNull(result.getBirthDate());
        assertNull(result.getStatus());
        assertNull(result.getAddresses());
    }
}
