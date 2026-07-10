package com.miguel.dto_dao.controller;

import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.miguel.dto_dao.dto.CursorPageResponse;
import com.miguel.dto_dao.dto.UserDto;
import com.miguel.dto_dao.entity.User;
import com.miguel.dto_dao.service.IUserService;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

  private final IUserService iUserService;

  @GetMapping()
  public List<UserDto> list() {
    return iUserService.findAll();
  }

  @GetMapping("/list/page")
  public ResponseEntity<Page<UserDto>> listProducts(
      @RequestParam(name = "page", defaultValue = "0") int page,
      @RequestParam(name = "size", defaultValue = "10") int size) {

    Page<UserDto> products = iUserService.findAll(page, size);
    return ResponseEntity.ok(products);
  }

  @GetMapping("/list/cursor")
  public CursorPageResponse<User> list(
      @RequestParam(required = false) Long cursor,
      @RequestParam(defaultValue = "10") int size) {
    return this.iUserService.findAll(cursor, size);
  }

  @PostMapping()
  public UserDto create(@RequestBody UserDto user) {

    return iUserService.save(user);
  }

}
