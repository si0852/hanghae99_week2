package com.hhplus.lecture.service;

import com.hhplus.lecture.dto.ApplyDto;
import com.hhplus.lecture.entity.Apply;

public interface ApplyService {
    Apply apply(ApplyDto applyDto) throws Exception;
}
