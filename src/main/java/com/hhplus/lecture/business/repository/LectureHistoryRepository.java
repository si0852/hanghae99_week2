package com.hhplus.lecture.business.repository;

import com.hhplus.lecture.business.entity.LectureHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureHistoryRepository extends JpaRepository<LectureHistory, Long> {
    LectureHistory save(LectureHistory lectureHistory);
}
