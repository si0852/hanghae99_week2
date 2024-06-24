package com.hhplus.lecture.service.fake;

import com.hhplus.lecture.entity.LectureHistory;

public interface LectureHistoryInfoRepository {
    LectureHistory save(LectureHistory lectureHistory);
}
