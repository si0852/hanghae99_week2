package com.hhplus.lecture.repository;

import com.hhplus.lecture.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LectureRepository extends JpaRepository<Lecture, Long> {

    Lecture save(Lecture lecture);

    List<Lecture> findAll();

    Optional<Lecture> findById(long lmId);

    void deleteAll();
}
