package com.hhplus.lecture.service.fake.impl;

import com.hhplus.lecture.entity.LectureHistory;
import com.hhplus.lecture.entity.Users;
import com.hhplus.lecture.repository.LectureHistoryRepository;
import com.hhplus.lecture.service.fake.LectureHistoryInfoRepository;
import com.hhplus.lecture.service.fake.UserInfoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FakeLectureHistoryInfoRepository implements LectureHistoryInfoRepository {
    private final List<Users> table = new ArrayList<>();

    @Override
    public LectureHistory save(LectureHistory lectureHistory) {
        return null;
    }
}
