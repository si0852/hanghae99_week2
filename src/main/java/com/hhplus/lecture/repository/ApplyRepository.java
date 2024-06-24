package com.hhplus.lecture.repository;

import com.hhplus.lecture.entity.Apply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplyRepository extends JpaRepository<Apply, Long> {
    Apply save(Apply apply);

    Apply findById(long applyId);

    List<Apply> findAll();
}
