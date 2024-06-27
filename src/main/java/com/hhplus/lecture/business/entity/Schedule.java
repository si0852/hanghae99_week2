package com.hhplus.lecture.business.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long scheduleId;
    @NotNull
    private Long lcId;
    @NotBlank
    private String lectureName;
    private Date openDate;
    @Min(1)
    private int maxAttendees;

    public Schedule(Long lcId, Date openDate,int maxAttendees, String lectureName) {
        this.lcId = lcId;
        this.openDate = openDate;
        this.maxAttendees = maxAttendees;
        this.lectureName = lectureName;
    }

    public boolean isMaxAttendees() {
        if (this.maxAttendees < 1) return false;
        return true;
    }

    public boolean isOpenDate(Date applyDate) {
        return applyDate.before(this.openDate);
    }

    public void decrease() {
        this.maxAttendees -= 1;
    }
}
