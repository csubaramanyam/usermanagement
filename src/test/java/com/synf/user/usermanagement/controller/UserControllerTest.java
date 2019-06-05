package com.synf.user.usermanagement.controller;

import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import com.synf.user.usermanagement.model.ImageRequestDTO;
import com.synf.user.usermanagement.model.UserDTO;
import com.synf.user.usermanagement.service.UserService;
import com.synf.user.usermanagement.validator.UserValidator;

/**
 * This class contains Mockito Junit test cases for UserController.java file
 * 
 * @author Subbu
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserControllerTest.class);
	
	@InjectMocks
	private UserController userController;
	
	@Mock
	private UserService userService;
	
	@Mock
	private UserValidator userValidator;
	
	@Mock
	private PasswordEncoder passwordEncoder;
	
	@Mock
	private BindingResult mockErrors;
	
	@Before
	public void setUp() {
		
	}
	
	@Test
	public void testRegistration() {

		try {

			when(mockErrors.hasErrors()).thenReturn(false);
			
			UserDTO userDTO = mockUserDTO();

			userController.registration(userDTO, mockErrors);
		} catch (Exception e) {
			LOGGER.info("Exception @testRegistration", e);
		}

	}
	
	@Test
	public void testRegistrationWhenFailed() {

		try {

			when(mockErrors.hasErrors()).thenReturn(true);
			
			UserDTO userDTO = mockUserDTO();

			userController.registration(userDTO, mockErrors);
		} catch (Exception e) {
			LOGGER.info("Exception @testRegistration", e);
		}

	}

	@Test
	public void testUploadImage() {

		try {
			
			MultipartFile file = Mockito.mock(MultipartFile.class);

			when(userValidator.validateUserAndPassword(Mockito.anyString(), Mockito.anyString())).thenReturn(new UserDTO());

			userController.uploadImage(file, "subbuchinnam", "123");
		} catch (Exception e) {
			LOGGER.info("Exception @testRegistrationWhenValidationFailed", e);
		}

	}
	

	@Test
	public void testdeleteImage() {

		try {
			
			ImageRequestDTO imageRequestDTO = new ImageRequestDTO();
			
			userController.delete(imageRequestDTO);
		} catch (Exception e) {
			LOGGER.info("Exception @testdeleteImage", e);
		}

	}

	@Test
	public void testViewUser() {

		try {
			
			ImageRequestDTO imageRequestDTO = new ImageRequestDTO();
			
			userController.getUserDetailsByUserName(imageRequestDTO.getUserName());
		} catch (Exception e) {
			LOGGER.info("Exception @testViewUser", e);
		}

	}


	@Test
	public void testView() {

		try {
			
			ImageRequestDTO imageRequestDTO = new ImageRequestDTO();
			
			userController.getImageByImageId(imageRequestDTO);
		} catch (Exception e) {
			LOGGER.info("Exception @testView", e);
		}

	}

	private static UserDTO mockUserDTO() {
		
		UserDTO userDTO = new UserDTO(1,"subbuchinnam", "123", "sub@abc.com");
		
		return userDTO;
	}

}
