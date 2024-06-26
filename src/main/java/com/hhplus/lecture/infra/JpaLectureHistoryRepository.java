package com.hhplus.lecture.infra;

import com.hhplus.lecture.business.entity.LectureHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaLectureHistoryRepository extends JpaRepository<LectureHistory, Long> {
    LectureHistory save(LectureHistory lectureHistory);
}
