package com.synf.user.usermanagement.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.synf.user.usermanagement.dao.model.UserImage;
import com.synf.user.usermanagement.model.UserImageDTO;

@Mapper(componentModel = "spring")
public interface UserImageMapper {

	UserImageMapper userImageMapper = Mappers.getMapper(UserImageMapper.class);

    UserImageDTO entityToDto(UserImage userImage);

    UserImage dtoToEntity(UserImageDTO userImageDTO);
	
}
