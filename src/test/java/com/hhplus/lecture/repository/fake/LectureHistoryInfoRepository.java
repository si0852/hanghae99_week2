package com.hhplus.lecture.repository.fake;

import com.hhplus.lecture.entity.LectureHistory;

import java.util.List;

public interface LectureHistoryInfoRepository {
    LectureHistory save(LectureHistory lectureHistory);

    List<LectureHistory> selectAllByHistoryId(long historyId);
}
