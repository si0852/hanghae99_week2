package com.hhplus.lecture.infra;

import com.hhplus.lecture.business.entity.Schedule;
import com.hhplus.lecture.business.entity.Users;
import com.hhplus.lecture.business.repository.ScheduleRepository;
import com.hhplus.lecture.business.repository.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaScheduledRepository extends JpaRepository<Schedule, Long>, ScheduleRepository {

    @Override
    default Schedule save(Schedule schedule) {
        return save(schedule);
    }

    @Override
    default Schedule findById(long scheduleId) {
        return findById(scheduleId);
    }

    @Override
    default List<Schedule> getScheduleList() {
        return findAll();
    }

    List<Schedule> findAll();
}
