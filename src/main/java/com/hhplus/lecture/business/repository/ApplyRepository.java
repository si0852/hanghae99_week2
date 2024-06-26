package com.hhplus.lecture.business.repository;

import com.hhplus.lecture.business.entity.Apply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplyRepository extends JpaRepository<Apply, Long> {

    Apply save(Apply apply);
    Optional<Apply> findByLcIdAndUserId(long lcId, long userId);
}
