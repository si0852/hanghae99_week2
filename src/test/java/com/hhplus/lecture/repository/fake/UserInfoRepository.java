package com.hhplus.lecture.repository.fake;

import com.hhplus.lecture.entity.Users;


public interface UserInfoRepository {
    Users save(Users user);

    Users findById(Long userId);

}
