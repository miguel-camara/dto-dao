package com.miguel.dto_dao.controller;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.miguel.dto_dao.dto.UserDto;
import com.miguel.dto_dao.entity.User;
import com.miguel.dto_dao.service.IUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/users")
public class UserController {

  @Autowired
  private IUserService iUserService;

  @GetMapping()
  public List<UserDto> list() {
    return iUserService.findAll();
  }

  @PostMapping()
  public UserDto create(@RequestBody UserDto user) {

    return iUserService.save(user);
  }

}
