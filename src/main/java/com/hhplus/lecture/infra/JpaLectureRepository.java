package com.hhplus.lecture.infra;

import com.hhplus.lecture.business.entity.Lecture;
import com.hhplus.lecture.business.repository.LectureRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface JpaLectureRepository extends JpaRepository<Lecture, Long>, LectureRepository {
    @Override
    default Lecture save(Lecture lecture) {
        return save(lecture);
    }

    @Override
    default Lecture findById(long lcId) {
        return findById(lcId);
    }
}
