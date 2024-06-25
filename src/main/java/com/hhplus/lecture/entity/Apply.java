package com.hhplus.lecture.entity;

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
    private Long lmId;
    private Long userId;
    private Date attendDate;
    private String attendanceYn;

    @Builder
    public Apply(Long applyId, Long lmId, Long userId, Date attendDate, String attendanceYn) {
        this.applyId = applyId;
        this.lmId = lmId;
        this.userId = userId;
        this.attendDate = attendDate;
        this.attendanceYn = attendanceYn;
    }
}
