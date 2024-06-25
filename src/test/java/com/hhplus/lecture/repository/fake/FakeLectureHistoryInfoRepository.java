package com.hhplus.lecture.repository.fake;

import com.hhplus.lecture.entity.LectureHistory;

import java.util.ArrayList;
import java.util.List;

public class FakeLectureHistoryInfoRepository implements LectureHistoryInfoRepository {
    private final List<LectureHistory> table = new ArrayList<>();
    private long cursor = 1;

    @Override
    public LectureHistory save(LectureHistory lectureHistory) {
        table.add(lectureHistory);
        return lectureHistory;
    }

    @Override
    public List<LectureHistory> selectAllByHistoryId(long historyId) {
        return table.stream().filter(history -> history.getHistoryId() == historyId).toList();
    }
}
