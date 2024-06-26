package com.hhplus.lecture.business.service;

import com.hhplus.lecture.business.repository.ApplyRepository;
import com.hhplus.lecture.business.repository.LectureHistoryRepository;
import com.hhplus.lecture.business.repository.LectureRepository;
import com.hhplus.lecture.business.repository.UserRepository;
import com.hhplus.lecture.dto.ApplyDto;
import com.hhplus.lecture.business.entity.Apply;
import com.hhplus.lecture.business.entity.Lecture;
import com.hhplus.lecture.business.entity.Users;
import com.hhplus.lecture.exception.NoLectureInfoException;
import com.hhplus.lecture.exception.NoUserInfoException;
import com.hhplus.lecture.response.Response;
import com.hhplus.lecture.validation.Validator;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
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
        Lecture lecture = lectureRepository.findById(applyDto.lcId()).orElseThrow(() -> new NoLectureInfoException(new Response(500, "강의정보가 없습니다.")));
        Apply applyInfo = applyRepository.findByLcIdAndUserId(applyDto.lcId(), applyDto.userId()).orElse(null);
        validationCheck(applyInfo);
        return null;
    }

    private void validationCheck(Apply apply)throws Exception {
        Validator.validate(Validator.applyOpenValue(apply));
    }
}
