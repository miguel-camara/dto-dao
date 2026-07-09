package com.miguel.dto_dao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.miguel.dto_dao.dto.UserDto;
import com.miguel.dto_dao.entity.User;
import com.miguel.dto_dao.repository.UserRepository;

@Service
public class UserServiceImpl implements IUserService {

  @Autowired
  private UserRepository userRepository;

  @Transactional(readOnly = true)
  @Override
  public List<UserDto> findAll() {
    return this.userRepository.findAll().stream().map(user -> new UserDto(user.getName())).toList();
  }

  @Transactional
  @Override
  public UserDto save(UserDto userDto) {
    User user = new User();
    user.setName(userDto.getName());
    var res = this.userRepository.save(user);
    return UserDto.builder().name(res.getName()).build();
  }

}
