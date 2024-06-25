package com.hhplus.lecture.repository;

import com.hhplus.lecture.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LectureRepository extends JpaRepository<Lecture, Long> {

    Lecture save(Lecture lecture);

    @Modifying
    @Query("UPDATE Lecture l set l.maxAttendees=:maxAttendees where l.lcId=:lcId")
    int updateLectureMaxAttendees(@Param(value="maxAttendees")int maxAttendees, @Param(value="lcId") long lcId);

    Optional<Lecture> findById(long lcId);

}
