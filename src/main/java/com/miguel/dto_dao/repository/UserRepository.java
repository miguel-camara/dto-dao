package com.miguel.dto_dao.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.miguel.dto_dao.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
  @Query("""
          SELECT u FROM User u
          WHERE (:cursor IS NULL OR u.id > :cursor)
          ORDER BY u.createdAt ASC
      """)

  public List<User> fetchNextPage(@Param("cursor") Long cursor, Pageable pageable);
  // SELECT *
  // FROM product
  // WHERE id > 5
  // ORDER BY id ASC
  // LIMIT 5;
}
