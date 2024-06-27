package com.hhplus.lecture.business.repository;

import com.hhplus.lecture.business.entity.Schedule;

import java.util.List;

public interface ScheduleRepository {

    Schedule save(Schedule schedule);

    Schedule findById(long scheduleId);

    List<Schedule> getScheduleList();
}
