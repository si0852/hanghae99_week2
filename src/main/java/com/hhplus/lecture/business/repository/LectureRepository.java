package com.hhplus.lecture.business.repository;

import com.hhplus.lecture.business.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LectureRepository{

    Lecture save(Lecture lecture);

    Lecture findById(long lcId);
}
