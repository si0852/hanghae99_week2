package com.hhplus.lecture.service.impl;

import com.hhplus.lecture.dto.ApplyDto;
import com.hhplus.lecture.entity.Apply;
import com.hhplus.lecture.entity.Lecture;
import com.hhplus.lecture.entity.Users;
import com.hhplus.lecture.exception.NoLectureInfoException;
import com.hhplus.lecture.exception.NoUserInfoException;
import com.hhplus.lecture.exception.NotExistApplyInfoException;
import com.hhplus.lecture.repository.ApplyRepository;
import com.hhplus.lecture.repository.LectureHistoryRepository;
import com.hhplus.lecture.repository.LectureRepository;
import com.hhplus.lecture.repository.UserRepository;
import com.hhplus.lecture.response.Response;
import com.hhplus.lecture.service.ApplyService;
import com.hhplus.lecture.validation.Validator;

import java.util.Date;
import java.util.Optional;

public class ApplyServiceImpl implements ApplyService {

    private final ApplyRepository applyRepository;
    private final LectureRepository lectureRepository;
    private final UserRepository userRepository;
    private final LectureHistoryRepository lectureHistoryRepository;

    public ApplyServiceImpl(ApplyRepository applyRepository, LectureRepository lectureRepository, UserRepository userRepository, LectureHistoryRepository lectureHistoryRepository) {
        this.applyRepository = applyRepository;
        this.lectureRepository = lectureRepository;
        this.userRepository = userRepository;
        this.lectureHistoryRepository = lectureHistoryRepository;
    }

    @Override
    public Apply apply(ApplyDto applyDto) throws Exception{
        userRepository.findById(applyDto.userId()).orElseThrow(() -> new NoUserInfoException(new Response(500, "유저정보가 없습니다.")));
        Lecture lectureInfo = lectureRepository.findById(applyDto.lcId()).orElseThrow(() -> new NoLectureInfoException(new Response(500, "강의정보가 없습니다.")));
        Apply applyInfo = applyRepository.findByLcIdAndUserId(applyDto.lcId(), applyDto.userId()).orElseThrow(() -> new NotExistApplyInfoException(new Response(500, "강의정보가 없습니다.")));
        Apply insertApply = Apply.builder().lcId(applyDto.lcId()).applyId(applyDto.userId()).attendDate(new Date()).attendanceYn("Y").build();
        applyRepository.save(insertApply);
        int maxAttendees = lectureInfo.getMaxAttendees() - 1;
//        Lecture updateLecture = Lecture.builder().lcId(applyDto.lcId())
        validationCheck(lectureInfo, applyInfo);
        return null;
    }

    private void validationCheck(Lecture lectureInfo, Apply apply)throws Exception {
        Validator.validate(Validator.openDateCompareToApplyDate(lectureInfo.getOpenDate(), new Date()));
        Validator.validate(Validator.applyOpenValue(apply));
    }
}
