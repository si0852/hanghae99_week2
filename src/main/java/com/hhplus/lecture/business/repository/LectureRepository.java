package com.hhplus.lecture.business.repository;

import com.hhplus.lecture.business.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LectureRepository extends JpaRepository<Lecture, Long> {

    Lecture save(Lecture lecture);

    Optional<Lecture> findById(long lcId) throws Exception;
}
