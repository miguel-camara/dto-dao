package com.miguel.dto_dao.mapper;

import org.springframework.stereotype.Component;

import com.miguel.dto_dao.dto.UserDto;
import com.miguel.dto_dao.entity.User;

@Component
public class UserMapper {

  public UserDto toDto(User entity) {
    if (entity == null) {
      return null;
    }
    return UserDto.builder().name(entity.getName()).username(entity.getUsername()).build();
  }

  public User toEntity(UserDto dto) {
    if (dto == null) {
      return null;
    }
    var user = new User();
    user.setName(dto.getName());
    user.setUsername(dto.getUsername());
    return user;
  }
}
