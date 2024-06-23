package com.hhplus.lecture.entity;

import com.hhplus.lecture.type.LectureType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LectureHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long lmId;

    private String lectureName;

    private Long userId;

    private String userName;

    @Enumerated(EnumType.STRING)
    private LectureType type;

    @Temporal(TemporalType.TIMESTAMP)
    private Date attendDate;
}
