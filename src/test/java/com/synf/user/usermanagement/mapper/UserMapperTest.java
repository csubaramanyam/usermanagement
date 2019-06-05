package com.synf.user.usermanagement.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.synf.user.usermanagement.dao.model.User;
import com.synf.user.usermanagement.model.UserDTO;

public class UserMapperTest {

	private static final String USER_NAME = "subbuchinnam";
    private static final Integer USER_ID = 100;
    private static final String PASSWORD = "123";
    private static final String EMAIL = "sub@abc.com";

    @Test
    public void entityToDto() {
        //Given
        final User user = new User();
        user.setUserName(USER_NAME);
        user.setPassword(PASSWORD);
        user.setEmail(EMAIL);
        user.setUserId(USER_ID);

        //when
        final UserDTO userDTO = UserMapper.userMapper.entityToDto(user);

        //then
        assertNotNull("User DTO is null", userDTO);
        assertEquals("User name not matching", USER_NAME, userDTO.getUserName());
        assertEquals("User Id not matching", USER_ID, userDTO.getUserId());
        assertEquals("Password not matching", PASSWORD, userDTO.getPassword());
        assertEquals("EMAIL not matching", EMAIL, userDTO.getEmail());

    }
    
    @Test
    public void entityToDto_NullTest() {
        //when
        final UserDTO userDTO = UserMapper.userMapper.entityToDto(null);

        //then
        assertNull("User DTO is not null", userDTO);

    }
    
    @Test
    public void dtoToEntity() {
        //Given
        final UserDTO userDTO = new UserDTO();
        userDTO.setUserName(USER_NAME);
        userDTO.setPassword(PASSWORD);
        userDTO.setEmail(EMAIL);
        userDTO.setUserId(USER_ID);

        //when
        final User user = UserMapper.userMapper.dtoToEntity(userDTO);

        //then
        assertNotNull("User Entity is null", user);
        assertEquals("User name not matching", USER_NAME, user.getUserName());
        assertEquals("User Id not matching", USER_ID, user.getUserId());
        assertEquals("Password not matching", PASSWORD, user.getPassword());
        assertEquals("EMAIL not matching", EMAIL, user.getEmail());
    }

    @Test
    public void dtoToEntity_NullTest() {
        //when
    	final User user = UserMapper.userMapper.dtoToEntity(null);

        //then
        assertNull("User Entity is not null", user);
    }
}
