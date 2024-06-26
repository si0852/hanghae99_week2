package com.hhplus.lecture.business.repository;

import com.hhplus.lecture.business.entity.Apply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplyRepository{

    Apply save(Apply apply);
    Apply getApplyInfo(long lcId, long userId);
}
