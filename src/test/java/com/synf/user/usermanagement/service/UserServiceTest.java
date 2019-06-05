package com.synf.user.usermanagement.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import com.synf.user.usermanagement.dao.model.User;
import com.synf.user.usermanagement.dao.model.UserImage;
import com.synf.user.usermanagement.dao.repository.UserImageRepository;
import com.synf.user.usermanagement.dao.repository.UserRepository;
import com.synf.user.usermanagement.exception.MethodFailureException;
import com.synf.user.usermanagement.mapper.UserImageMapper;
import com.synf.user.usermanagement.mapper.UserMapper;
import com.synf.user.usermanagement.model.UserDTO;
import com.synf.user.usermanagement.model.UserImageDTO;
import com.synf.user.usermanagement.model.UserResponseDTO;

/**
 * This class contains Mockito Junit test cases for UserService.java file
 * 
 * @author Subbu
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceTest.class);
	
	@InjectMocks
	private UserService userService;
	
	@Mock
	private UserRepository userRepository;
	
	@Mock
	private UserImageRepository userImageRepository;
	
	@Mock
	private ImgurService imgurService;
	
	@Mock
	private UserMapper userMapper;
	
	@Mock
	private UserImageMapper userImageMapper;
	
	private UserDTO userDTO;
	
	private User user;
	
	private UserImage userImage;
	
	private UserImageDTO userImageDTO;
	
	@Before
	public void setUp() {
		userDTO = new UserDTO(null, "subbuchinnam", "123", "sub@abc.com");
		user = new User(1, "subbuchinnam", "123", "sub@abc.com");
		userImage = new UserImage();
		userImage.setUserImageId(1);
		userImage.setUserId(1);
		userImage.setUploadedDate(new Date());
		
		userImageDTO = new UserImageDTO();
		userImageDTO.setUserImageId(1);
		userImageDTO.setUserId(1);
		
	}
	
	@Test
	public void testSaveUser() {
		
		when(userMapper.dtoToEntity(any(UserDTO.class))).thenReturn(user);
		when(userRepository.save(any(User.class))).thenReturn(user);
		when(userMapper.entityToDto(any(User.class))).thenReturn(userDTO);
		
		UserDTO resDTO = userService.saveUser(userDTO);
		
		Assert.assertNotNull(resDTO);
	}

	@Test
	public void testGetUserByUserName() {
		
		when(userRepository.findByUserName(anyString())).thenReturn(user);
		when(userMapper.entityToDto(any(User.class))).thenReturn(userDTO);
		
		UserDTO resDTO = userService.getUserByUserName("subbuchinnam");
		
		Assert.assertNotNull(resDTO);
	}
	
	@Test
	public void testGetUserByUserNameWhenUserNameNotExists() {
		
		when(userRepository.findByUserName(anyString())).thenReturn(null);
		
		UserDTO resDTO = userService.getUserByUserName("abc");
		
		Assert.assertNull(resDTO);
	}

	@Test
	public void testUploadImage() {
		
		final String responseBody = "{\"data\":{\"id\":\"7GyB3Wo\",\"title\":null,\"description\":null,\"datetime\":1559536419,\"type\":\"image\\/png\",\"animated\":false,\"width\":542,\"height\":310,\"size\":8754,\"views\":0,\"bandwidth\":0,\"vote\":null,\"favorite\":false,\"nsfw\":null,\"section\":null,\"account_url\":null,\"account_id\":0,\"is_ad\":false,\"in_most_viral\":false,\"has_sound\":false,\"tags\":[],\"ad_type\":0,\"ad_url\":\"\",\"edited\":\"0\",\"in_gallery\":false,\"deletehash\":\"vKfACtjntqiRmsR\",\"name\":\"\",\"link\":\"https:\\/\\/i.imgur.com\\/7GyB3Wo.png\"},\"success\":true,\"status\":200}";
		
		when(imgurService.upload(anyString())).thenReturn(responseBody);
		when(userImageRepository.save(Mockito.any(UserImage.class))).thenReturn(userImage);
		when(userImageMapper.entityToDto(any(UserImage.class))).thenReturn(userImageDTO);
		
		UserImageDTO uerImageDTO = userService.uploadImage("subbuchinnam", "abc.jpg", 1);
		
		Assert.assertNotNull(uerImageDTO);
	}
	
	@Test(expected = MethodFailureException.class)
	public void testUploadImageWhenImgurServiceThrowsException() {
		
		when(imgurService.upload(anyString())).thenThrow(new MethodFailureException(HttpStatus.BAD_REQUEST.toString()));
		
		userService.uploadImage("subbuchinnam", "abc.jpg", 1);
		
	}
	
	@Test
	public void testDeleteImage() {
		
		final String responseBody = "{\"data\":true,\"success\":true,\"status\":200}";
		
		when(imgurService.delete(anyString())).thenReturn(responseBody);
		when(userImageRepository.deleteByUserIdAndImgurImageDeleteHash(anyInt(), anyString())).thenReturn(1);
		
		Boolean isDeleted = userService.deleteImage("gLFMAGvQCPMFWw0",  1);
		
		Assert.assertTrue(isDeleted);
	}
	
	@Test
	public void testDeleteImageWhenStatusInvalid() {
		
		final String responseBody = "{\"data\":true,\"success\":false,\"status\":400}";
		
		when(imgurService.delete(anyString())).thenReturn(responseBody);

		Boolean isDeleted = userService.deleteImage("gLFMAGvQCPMFWw0",  1);
		
		Assert.assertFalse(isDeleted);
	}
	
	@Test
	public void testGetImageByImageId() {
		
		final String responseBody = "{\"data\":{\"id\":\"7GyB3Wo\",\"title\":null,\"description\":null,\"datetime\":1559536419,\"type\":\"image\\/png\",\"animated\":false,\"width\":542,\"height\":310,\"size\":8754,\"views\":0,\"bandwidth\":0,\"vote\":null,\"favorite\":false,\"nsfw\":null,\"section\":null,\"account_url\":null,\"account_id\":0,\"is_ad\":false,\"in_most_viral\":false,\"has_sound\":false,\"tags\":[],\"ad_type\":0,\"ad_url\":\"\",\"edited\":\"0\",\"in_gallery\":false,\"deletehash\":\"vKfACtjntqiRmsR\",\"name\":\"\",\"link\":\"https:\\/\\/i.imgur.com\\/7GyB3Wo.png\"},\"success\":true,\"status\":200}";
		
		when(imgurService.getImageByImageId(anyString())).thenReturn(responseBody);
		
		String response = userService.getImageByImageId("gLFMAGvQCPMFWw0");
		
		Assert.assertNotNull(response);
	}
	
	@Test
	public void testGetUserDetailsByUserName() {
		
		List<UserImage> images = new ArrayList<>();
		images.add(userImage);
		
		when(userRepository.findByUserName(anyString())).thenReturn(user);
		when(userImageRepository.findByUserId(anyInt())).thenReturn(images);
		
		UserResponseDTO userResponseDTO = userService.getUserDetailsByUserName("subbuchinnam");
		
		Assert.assertNotNull(userResponseDTO);
	}
}
