package com.hhplus.lecture.service.fake.impl;

import com.hhplus.lecture.entity.Lecture;
import com.hhplus.lecture.entity.Users;
import com.hhplus.lecture.service.fake.LectureInfoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FakeLectureInfoRepositoryImpl implements LectureInfoRepository {
    private final List<Users> table = new ArrayList<>();
    @Override
    public Lecture save(Lecture lecture) {
        return null;
    }

    @Override
    public List<Lecture> findAll() {
        return null;
    }

    @Override
    public Optional<Lecture> findById(long userId) {
        return Optional.empty();
    }

    @Override
    public void deleteAll() {

    }
}
