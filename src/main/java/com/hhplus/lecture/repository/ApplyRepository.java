package com.hhplus.lecture.repository;

import com.hhplus.lecture.entity.Apply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApplyRepository extends JpaRepository<Apply, Long> {
    Apply save(Apply apply);

    Optional<Apply> findByLcIdAndUserId(long lcId, long userId);

}
