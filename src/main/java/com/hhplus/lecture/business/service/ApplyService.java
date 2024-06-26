package com.hhplus.lecture.business.service;

import com.hhplus.lecture.dto.ApplyDto;
import com.hhplus.lecture.business.entity.Apply;

public interface ApplyService {
    Apply apply(ApplyDto applyDto) throws Exception;
}
