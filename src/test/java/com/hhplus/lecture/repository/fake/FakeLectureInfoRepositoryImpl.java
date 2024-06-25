package com.hhplus.lecture.repository.fake;

import com.hhplus.lecture.entity.Lecture;

import java.util.HashMap;

public class FakeLectureInfoRepositoryImpl implements LectureInfoRepository {
    private final HashMap<Long, Lecture> table = new HashMap<>();

    @Override
    public Lecture save(Lecture lecture) {
        table.put(lecture.getLcId(), lecture);
        return lecture;
    }

    @Override
    public Lecture findById(long userId) {
        return table.getOrDefault(userId, null);
    }

    @Override
    public Lecture update(long lcId, Lecture lecture) {
        table.put(lecture.getLcId(), lecture);
        return lecture;
    }

}
