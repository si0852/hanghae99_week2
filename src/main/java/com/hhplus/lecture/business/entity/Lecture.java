package com.hhplus.lecture.business.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Lecture {
    @Id
    private long lcId;
    private String lectureName;
    private Date openDate;
    private int maxAttendees;

    @Builder
    public Lecture (long lcId, String lectureName, Date openDate, int maxAttendees) {
        this.lcId = lcId;
        this.lectureName = lectureName;
        this.openDate = openDate;
        this.maxAttendees = maxAttendees;
    }
}
