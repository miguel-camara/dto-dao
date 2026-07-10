package com.miguel.dto_dao.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDto implements Serializable {
  private String name;
  private String username;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
