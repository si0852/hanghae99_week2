package com.hhplus.lecture.service.fake.impl;

import com.hhplus.lecture.entity.Users;
import com.hhplus.lecture.service.fake.UserInfoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FakeUserInfoRepositoryImpl implements UserInfoRepository {
    private final List<Users> table = new ArrayList<>();
    @Override
    public Users save(Users user) {
        return null;
    }

    @Override
    public List<Users> findAll() {
        return null;
    }

    @Override
    public Optional<Users> findById(long userId) {
        return Optional.empty();
    }
}
