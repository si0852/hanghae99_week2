package com.hhplus.lecture.service.fake;

import com.hhplus.lecture.entity.Lecture;

import java.util.List;
import java.util.Optional;

public interface LectureInfoRepository {

    Lecture save(Lecture lecture);

    List<Lecture> findAll();

    Optional<Lecture> findById(long lmId);

    void deleteAll();
}
