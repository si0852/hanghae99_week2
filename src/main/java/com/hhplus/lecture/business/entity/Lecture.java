package com.hhplus.lecture.business.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotBlank
    private String lectureName;

    public Lecture (long lcId, String lectureName) {
        this.lcId = lcId;
        this.lectureName = lectureName;
    }
}
