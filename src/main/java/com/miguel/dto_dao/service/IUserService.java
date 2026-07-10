package com.miguel.dto_dao.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.miguel.dto_dao.dto.CursorPageResponse;
import com.miguel.dto_dao.dto.UserDto;
// import com.miguel.dto_dao.entity.User;
import com.miguel.dto_dao.entity.User;

public interface IUserService {
  List<UserDto> findAll();

  UserDto save(UserDto user);

  Page<UserDto> findAll(int offset, int pageSize);

  CursorPageResponse<User> findAll(Long cursor, int size);
  // CursorPageResponse<UserDto> findAll(Long cursor, int size);

}
