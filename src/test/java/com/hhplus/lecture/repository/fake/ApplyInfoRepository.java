package com.hhplus.lecture.repository.fake;

import com.hhplus.lecture.entity.Apply;

import java.util.List;


public interface ApplyInfoRepository {

    Apply save(Apply apply);

    List<Apply> findById(long lcId, long userId);

}
