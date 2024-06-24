package com.hhplus.lecture.service.fake.impl;

import com.hhplus.lecture.entity.Apply;
import com.hhplus.lecture.service.fake.ApplyInfoRepository;

import java.util.ArrayList;
import java.util.List;

public class FakeApplyInfoRepositoryImpl implements ApplyInfoRepository {
    private final List<Apply> table = new ArrayList<>();

    @Override
    public List<Apply> findAll() {
        return null;
    }

    @Override
    public Apply save(Apply apply) {
        return null;
    }

    @Override
    public Apply findById(long applyId) {
        return null;
    }
}
