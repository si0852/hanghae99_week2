package com.hhplus.lecture.service.fake;

import com.hhplus.lecture.entity.Users;

import java.util.List;
import java.util.Optional;

public interface UserInfoRepository {
    Users save(Users user);

    List<Users> findAll();

    Optional<Users> findById(long userId);
}
