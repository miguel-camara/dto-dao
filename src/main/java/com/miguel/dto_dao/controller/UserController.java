package com.miguel.dto_dao.controller;

import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.miguel.dto_dao.dto.UserDto;
import com.miguel.dto_dao.service.IUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

  private final IUserService iUserService;

  @GetMapping()
  public List<UserDto> list() {
    return iUserService.findAll();
  }

  @PostMapping()
  public UserDto create(@RequestBody UserDto user) {

    return iUserService.save(user);
  }

}
