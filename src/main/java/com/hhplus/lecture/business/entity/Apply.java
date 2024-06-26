package com.hhplus.lecture.business.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Apply {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long applyId;
    private Long lcId;
    private Long userId;
    private Date attendDate;
    private String attendanceYn;

    @Builder
    public Apply(Long applyId, Long lcId, Long userId, Date attendDate, String attendanceYn) {
        this.applyId = applyId;
        this.lcId = lcId;
        this.userId = userId;
        this.attendDate = attendDate;
        this.attendanceYn = attendanceYn;
    }
}
