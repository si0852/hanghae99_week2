package com.hhplus.lecture.repository.fake;

import com.hhplus.lecture.entity.Users;

import java.util.*;

public class FakeUserInfoRepositoryImpl implements UserInfoRepository {
    private final Map<Long, Users> table = new HashMap<>();
    @Override
    public Users save(Users user) {
        table.put(user.getUserId(), user);
        return user;
    }

    @Override
    public Users findById(Long userId) {
        return table.getOrDefault(userId, null);
    }

}
