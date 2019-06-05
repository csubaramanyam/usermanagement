package com.synf.user.usermanagement.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.synf.user.usermanagement.dao.model.User;
import com.synf.user.usermanagement.model.UserDTO;

@Mapper(componentModel = "spring")
public interface UserMapper {

	UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    UserDTO entityToDto(User user);

    User dtoToEntity(UserDTO userDTO);
	
}
