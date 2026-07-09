package com.miguel.dto_dao.service;

import java.util.List;

import com.miguel.dto_dao.dto.UserDto;
// import com.miguel.dto_dao.entity.User;

public interface IUserService {
  List<UserDto> findAll();

  UserDto save(UserDto user);

}
