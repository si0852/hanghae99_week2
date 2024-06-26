package com.hhplus.lecture.business.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private Date openDate;

    public Schedule(Long scheduleId, Long lcId, Date openDate) {
        this.scheduleId = scheduleId;
        this.lcId = lcId;
        this.openDate = openDate;
    }
}
