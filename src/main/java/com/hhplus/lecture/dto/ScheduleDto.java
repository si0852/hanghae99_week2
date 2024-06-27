package com.hhplus.lecture.dto;

import lombok.Getter;

import java.util.Date;

@Getter
public class ScheduleDto {

    private Long scheduleId;
    private String lcName;
    private Date openDate;
    private int maxAttendees;

    public ScheduleDto(Long scheduleId, String lcName, Date openDate, int maxAttendees) {
        this.scheduleId = scheduleId;
        this.lcName = lcName;
        this.openDate = openDate;
        this.maxAttendees = maxAttendees;
    }
}
