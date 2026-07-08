package com.miguel.dto_dao.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.miguel.dto_dao.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

}
