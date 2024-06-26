package com.hhplus.lecture.business.repository;

import com.hhplus.lecture.business.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {

}
