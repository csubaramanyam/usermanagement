package com.synf.user.usermanagement.controller;

import java.io.IOException;
import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.synf.user.usermanagement.model.ImageRequestDTO;
import com.synf.user.usermanagement.model.UserDTO;
import com.synf.user.usermanagement.model.UserImageDTO;
import com.synf.user.usermanagement.model.UserResponseDTO;
import com.synf.user.usermanagement.service.UserService;
import com.synf.user.usermanagement.validator.UserValidator;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/user")
@Api(value = "user", description = "REST API for User Management", tags = {"user"})
public class UserController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserValidator userValidator;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@RequestMapping(value="/registration", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "User Registration")
	public UserDTO registration(@RequestBody UserDTO userDTO, BindingResult errors) throws BindException {
		LOGGER.info("Entered into registration method");
		
		//validating request
		userValidator.validate(userDTO, errors);
		
		if(errors.hasErrors()) {
			throw new BindException(errors);
		}
		
		//encrypted password
		userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		
		LOGGER.debug("user registration request:{}", userDTO);
		
		//call registration service
		UserDTO registeredUserDTO = userService.saveUser(userDTO);
		
		LOGGER.info("Exiting from registration method with details :{}", registeredUserDTO);
		
		return registeredUserDTO;
	}
	
	@RequestMapping(value="/{userName}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get a user by username")
	public UserResponseDTO getUserDetailsByUserName(@PathVariable String userName) {
		
		LOGGER.info("Entered into viewUser method with userName:{}", userName);
		
		//validating user
		userValidator.validateUser(userName);
		
		final UserResponseDTO userResponseDTO = userService.getUserDetailsByUserName(userName);
		
		LOGGER.info("Exiting from viewUser method with userDetails:{} for userName:{}", userResponseDTO, userName);
		
		return userResponseDTO;
	}

	//Start - image related API
	@RequestMapping(value = "/image", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "upload image")
	public UserImageDTO uploadImage(@RequestParam("file") MultipartFile file, @RequestParam("userName") String userName,
			@RequestParam("password") String password) throws IOException{
		
		LOGGER.info("Entered into upload image method with userName:{}", userName);
		
		//validating user
		UserDTO userDTO = userValidator.validateUserAndPassword(userName, password);
		
		//converting file into bytes and then encoded to support with imgur API
		byte[] bytes = file.getBytes();
		String data = Base64.getMimeEncoder().encodeToString(bytes);
		LOGGER.debug("Converted image into encoded string..");
		
		final UserImageDTO userImageDTO = userService.uploadImage(data, file.getOriginalFilename(), userDTO.getUserId());
		
		return userImageDTO;
	}
	
	@RequestMapping(value = "/image", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "delete image")
	public Boolean delete(@RequestBody ImageRequestDTO requestDTO) throws IOException{
		
		LOGGER.info("Entered into delete image method with userName:{}", requestDTO.getUserName());
		
		//validating user
		UserDTO userDTO = userValidator.validateUserAndPassword(requestDTO.getUserName(), requestDTO.getPassword());
		
		boolean isDeleted = userService.deleteImage(requestDTO.getHash(), userDTO.getUserId());
		
		LOGGER.info("Exiting from delete image {} method for userName:{}", requestDTO.getHash(), requestDTO.getUserName());
		
		return isDeleted;
	}

	
	@RequestMapping(value = "/image/getImage", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "get image by imageId")
	public String getImageByImageId(@RequestBody ImageRequestDTO requestDTO) throws IOException{
		
		LOGGER.info("Entered into view image method with userName:{}", requestDTO.getUserName());
		
		//validating user
		userValidator.validateUserAndPassword(requestDTO.getUserName(), requestDTO.getPassword());
		
		String response = userService.getImageByImageId(requestDTO.getHash());
		
		LOGGER.info("Exiting from view image {} method for userName:{}", response, requestDTO.getUserName());
		
		return response;
	}
	
	// END - image related API
}
