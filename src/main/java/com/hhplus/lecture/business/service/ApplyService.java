package com.hhplus.lecture.business.service;

import com.hhplus.lecture.business.entity.Schedule;
import com.hhplus.lecture.dto.ApplyDto;
import com.hhplus.lecture.business.entity.Apply;

import java.util.List;

public interface ApplyService {
    Apply apply(ApplyDto applyDto) throws Exception;

    Boolean isApplied(long scheduleId, long userId) throws Exception;

    List<Schedule> getScheduledList() throws Exception;
}
