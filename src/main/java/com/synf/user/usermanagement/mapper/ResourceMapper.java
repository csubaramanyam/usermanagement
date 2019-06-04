package com.synf.user.usermanagement.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.synf.user.usermanagement.dao.model.User;
import com.synf.user.usermanagement.dao.model.UserImage;
import com.synf.user.usermanagement.model.UserDTO;
import com.synf.user.usermanagement.model.UserImageDTO;
import com.synf.user.usermanagement.model.UserResponseDTO;

import ma.glasnost.orika.MapperFacade;

public class ResourceMapper {

	@Autowired
	private MapperFacade mapperFacade;
	
	public UserDTO convertToUserDTO(User user) {
		return mapperFacade.map(user, UserDTO.class);
	}
	
	public User convertToUser(UserDTO userDTO) {
		return mapperFacade.map(userDTO, User.class);
	}
	
	public UserImageDTO convertToUserImageDTO(UserImage userImage) {
		
		UserImageDTO userImageDTO = new UserImageDTO();
		userImageDTO.setUserImageId(userImage.getUserImageId());
		userImageDTO.setUserId(userImage.getUserId());
		userImageDTO.setSrcImageName(userImage.getSrcImageName());
		userImageDTO.setImgurImageId(userImage.getImgurImageId());
		userImageDTO.setImgurImageTitle(userImage.getImgurImageTitle());
		userImageDTO.setImgurImageDesc(userImage.getImgurImageDesc());
		userImageDTO.setImgurImageType(userImage.getImgurImageType());
		userImageDTO.setImgurImageLink(userImage.getImgurImageLink());
		userImageDTO.setImgurImageDeleteHash(userImage.getImgurImageDeleteHash());
		userImageDTO.setUploadedDate(userImage.getUploadedDate());
		
		return userImageDTO;
		
	}
	
	public UserResponseDTO convertToUserResponseDTO(User user, List<UserImage> userImages) {
		
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		userResponseDTO.setUserId(user.getUserId());
		userResponseDTO.setUserName(user.getUserName());
		userResponseDTO.setEmail(user.getEmail());
		List<UserImageDTO> userImageDTOList = new ArrayList<>();
		if(userImages != null && !userImages.isEmpty()) {
			userImages.forEach(i -> {
				userImageDTOList.add(convertToUserImageDTO(i));
			});
		}
		
		userResponseDTO.setUserImages(userImageDTOList);
		
		return userResponseDTO;
	}
}
