package com.miguel.dto_dao.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.miguel.dto_dao.dto.CursorPageResponse;
import com.miguel.dto_dao.dto.UserDto;
import com.miguel.dto_dao.entity.User;
// import com.miguel.dto_dao.entity.User;
import com.miguel.dto_dao.mapper.UserMapper;
import com.miguel.dto_dao.repository.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserServiceImpl implements IUserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;

  @Transactional(readOnly = true)
  @Override
  public List<UserDto> findAll() {
    return this.userRepository.findAll().stream()
        .map(user -> new UserDto(user.getName(), user.getName(), user.getCreatedAt(), user.getUpdatedAt()))
        .collect(Collectors.toList());
    // return this.userRepository.findAll().stream().map(user -> new
    // UserDto(user.getName())).toList();
  }

  @Transactional(readOnly = true)
  @Override
  public Page<UserDto> findAll(int offset, int pageSize) {
    return this.userRepository.findAll(PageRequest.of(offset, pageSize)).map(user -> {
      // user.setUsername(user.getName());
      user.setUsername(user.getName().concat(" - ").concat(UUID.randomUUID().toString()));
      // user.setCreatedAt();
      // user.setUpdatedAt();
      return userMapper.toDto(user);
    });
  }

  @Transactional(readOnly = true)
  @Override
  public CursorPageResponse<User> findAll(Long cursor, int size) {

    // default page = 0, size = 10 [0-9]
    Pageable pageable = PageRequest.of(0, size);

    // fetch next page records
    List<User> users = userRepository.fetchNextPage(cursor, pageable);
    // List<UserDto> users = userRepository.fetchNextPage(cursor,
    // pageable).stream().map(user ->
    // userMapper.toDto(user)).collect(Collectors.toList());
    // List<User> users = userRepository.fetchNextPage(cursor, pageable);

    // check if we have more records
    boolean hasNext = users.size() == size;

    // determine the next cursor

    Long nextCursor = hasNext
        ? Long.valueOf(users.size())
        : null;

    return new CursorPageResponse<User>(
        users,
        size,
        nextCursor,
        hasNext);
  }

  @Transactional
  @Override
  public UserDto save(UserDto dto) {
    var user = this.userMapper.toEntity(dto);
    // User user = new User();
    // user.setName(userDto.getName());
    var res = this.userRepository.save(user);
    res.setUsername(res.getName().concat(" - ").concat(UUID.randomUUID().toString()));
    return this.userMapper.toDto(res);
    // return UserDto.builder().name(res.getName()).build();
  }

}
