package com.hhplus.lecture.business.entity;

import com.hhplus.lecture.type.LectureType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class LectureHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long historyId;

    private Long lmId;

    private Long userId;

    @Enumerated(EnumType.STRING)
    private LectureType type;

    private Date attendDate;

    @Builder
    public LectureHistory(Long historyId, Long lmId, Long userId, LectureType type, Date attendDate) {
        this.historyId = historyId;
        this.lmId = lmId;
        this.userId = userId;
        this.type = type;
        this.attendDate = attendDate;
    }
}
