package com.miguel.dto_dao.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CursorPageResponse<T> {
  private List<T> data;
  private int pageSize;
  // private Long nextCursor;
  private boolean hasNext;
}
