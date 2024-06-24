package com.hhplus.lecture.service.fake;

import com.hhplus.lecture.entity.Apply;

import java.util.List;

public interface ApplyInfoRepository {

    Apply save(Apply apply);

    Apply findById(long applyId);

    List<Apply> findAll();
}
