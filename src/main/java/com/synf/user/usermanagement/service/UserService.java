package com.synf.user.usermanagement.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.synf.user.usermanagement.dao.model.User;
import com.synf.user.usermanagement.dao.model.UserImage;
import com.synf.user.usermanagement.dao.repository.UserImageRepository;
import com.synf.user.usermanagement.dao.repository.UserRepository;
import com.synf.user.usermanagement.mapper.UserImageMapper;
import com.synf.user.usermanagement.mapper.UserMapper;
import com.synf.user.usermanagement.model.UserDTO;
import com.synf.user.usermanagement.model.UserImageDTO;
import com.synf.user.usermanagement.model.UserResponseDTO;

@Service
public class UserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserImageRepository userImageRepository;
	
	@Autowired
	private ImgurService imgurService;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private UserImageMapper userImageMapper;
	
	/**
	 * This method used to save user registration details
	 * 
	 * @param reqUserDTO
	 * @return
	 */
	@Transactional
	public UserDTO saveUser(final UserDTO  reqUserDTO) {
		LOGGER.info("Entered into saveUser method with UserDTO:{}", reqUserDTO);
		User user = userMapper.dtoToEntity(reqUserDTO);
		user = userRepository.save(user);
		final UserDTO  resUserDTO = userMapper.entityToDto(user);
		LOGGER.info("Exiting from saveUser method after registering user with details:{}", resUserDTO);
		return resUserDTO;
	}
	
	/**
	 * This method used to get user details based on userName
	 * 
	 * @param userName
	 * @return
	 */
	public UserDTO getUserByUserName(String userName) {
		UserDTO userDTO = null;
		User user = userRepository.findByUserName(userName);
		if(user != null) {
			userDTO = userMapper.entityToDto(user);
		}
		return userDTO;
	}
	
	/**
	 * This method used to upload image using wrapper service of IMGUR API REST Call and make entry in user_images table
	 * @param data
	 * @throws JSONException 
	 */
	public UserImageDTO uploadImage(final String data, final String fileName, final Integer userId) {
		LOGGER.info("Entered into uploadImage method");
		
		//calling imgur service to upload image
		final String responseBody = imgurService.upload(data);
		
		//final String responseBody = "{\"data\":{\"id\":\"7GyB3Wo\",\"title\":null,\"description\":null,\"datetime\":1559536419,\"type\":\"image\\/png\",\"animated\":false,\"width\":542,\"height\":310,\"size\":8754,\"views\":0,\"bandwidth\":0,\"vote\":null,\"favorite\":false,\"nsfw\":null,\"section\":null,\"account_url\":null,\"account_id\":0,\"is_ad\":false,\"in_most_viral\":false,\"has_sound\":false,\"tags\":[],\"ad_type\":0,\"ad_url\":\"\",\"edited\":\"0\",\"in_gallery\":false,\"deletehash\":\"vKfACtjntqiRmsR\",\"name\":\"\",\"link\":\"https:\\/\\/i.imgur.com\\/7GyB3Wo.png\"},\"success\":true,\"status\":200}";
		
		final Map<String, String> responseMap = deSerializeJson(responseBody);
		
		UserImage userImage = new UserImage();
		userImage.setUserId(userId);
		userImage.setSrcImageName(fileName);
		
		//copy response attributes to UserImage entity
		copyJsonResponseToUserImage(responseMap, userImage);
		
		final UserImage savedUserImage = userImageRepository.save(userImage);
		
		final UserImageDTO userImageDTO = userImageMapper.entityToDto(savedUserImage);
		
		LOGGER.info("Exiting from uploadImage method with userImage details:{}", userImageDTO);
		
		return userImageDTO;
	}
	
	/**
	 * This method used to upload image using wrapper service of IMGUR API REST Call and make entry in user_images table
	 * @param data
	 * @throws JSONException 
	 */
	@Transactional
	public Boolean deleteImage(final String deleteHash, final Integer userId) {
		LOGGER.info("Entered into deleteImage method");
		
		boolean isDeleted = false;
		
		final String responseBody = imgurService.delete(deleteHash);
		
		try {
		
			JSONObject responseObj = new JSONObject(responseBody);
		
			if (responseObj.getInt("status") == 200) {
				
				userImageRepository.deleteByUserIdAndImgurImageDeleteHash(userId, deleteHash);
				
				isDeleted = true;
			}
			
		} catch (Exception e) {
			LOGGER.error("Exception occured while deserializing json response", e);
		}
		
		LOGGER.info("Exiting from deleteImage method with response:{}", isDeleted);
		
		return isDeleted;
	}
	
	/**
	 * This method used to upload image using wrapper service of IMGUR API REST Call and make entry in user_images table
	 * @param data
	 * @throws JSONException 
	 */
	public String getImageByImageId(final String imageId) {
		LOGGER.info("Entered into viewImage method");
		
		boolean isDeleted = false;
		
		final String responseBody = imgurService.getImageByImageId(imageId);
		
		LOGGER.info("Exiting from viewImage method with response:{}", isDeleted);
		
		return responseBody;
	}
	
	/**
	 * This method used to get user details and associated images based on userName
	 * 
	 * @param userName
	 * @return
	 */
	public UserResponseDTO getUserDetailsByUserName(final String userName) {
		
		LOGGER.info("Entered into viewUser method with userName:{}", userName);

		//get user details
		final User user = userRepository.findByUserName(userName);
		
		//get images associated with user
		final List<UserImage> userImageList = userImageRepository.findByUserId(user.getUserId());
		
		//prepare response dto
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		userResponseDTO.setUserId(user.getUserId());
		userResponseDTO.setUserName(user.getUserName());
		userResponseDTO.setEmail(user.getEmail());
		List<UserImageDTO> userImageDTOList = new ArrayList<>();
		if(userImageList != null && !userImageList.isEmpty()) {
			userImageList.forEach(i -> {
				userImageDTOList.add(userImageMapper.entityToDto(i));
			});
		}
		
		userResponseDTO.setUserImages(userImageDTOList);
		
		LOGGER.info("Exiting from viewUser method with userDetails:{} for userName:{}", userResponseDTO, userName);
		
		return userResponseDTO;
	}
	
	/**
	 * This method used for deserialize json response and store required attribute values into map from json response
	 * 
	 * @param responseBody
	 * @return
	 * @throws JSONException
	 */
	private Map<String, String> deSerializeJson(String responseBody){

		Map<String, String> responseMap = new HashMap<>();
		
		try {

			JSONObject responseObj = new JSONObject(responseBody);

			if (responseObj.getInt("status") == 200) {

				JSONObject dataObj = responseObj.getJSONObject("data");

				responseMap.put("id", dataObj.getString("id"));
				responseMap.put("title", dataObj.get("title") != null ? dataObj.get("title").toString() : null);
				responseMap.put("description", dataObj.get("description") != null ? dataObj.get("description").toString() : null);
				responseMap.put("type", dataObj.getString("type"));
				responseMap.put("deletehash", dataObj.getString("deletehash"));
				responseMap.put("link", dataObj.getString("link"));

			}
		} catch (Exception e) {
			LOGGER.error("Exception occured while deserializing json response", e);
		}
		return responseMap;
	}

	private void copyJsonResponseToUserImage(Map<String, String> responseMap, UserImage userImage) {
		
		userImage.setImgurImageId(responseMap.get("id"));
		userImage.setImgurImageTitle(responseMap.get("title"));
		userImage.setImgurImageDesc(responseMap.get("description"));
		userImage.setImgurImageType(responseMap.get("type"));
		userImage.setImgurImageLink(responseMap.get("link"));
		userImage.setImgurImageDeleteHash(responseMap.get("deletehash"));
		userImage.setUploadedDate(new Date());
	}
}
