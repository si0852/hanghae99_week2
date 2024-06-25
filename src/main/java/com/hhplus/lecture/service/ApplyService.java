package com.hhplus.lecture.service;

import com.hhplus.lecture.entity.Apply;

public interface ApplyService {
    Apply apply(long lcId, long userId) throws Exception;
}
