package com.hhplus.lecture.repository.fake;

import com.hhplus.lecture.entity.Lecture;

import java.util.List;
import java.util.Optional;

public interface LectureInfoRepository {

    Lecture save(Lecture lecture);

    Lecture findById(long lmId);

    Lecture update(long lcId, Lecture lecture);
}
