package com.hhplus.lecture.repository;

import com.hhplus.lecture.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {

    Users save(Users user);

    List<Users> findAll();

    Optional<Users> findById(long userId);
}
