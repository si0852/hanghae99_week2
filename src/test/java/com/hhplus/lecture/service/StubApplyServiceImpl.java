package com.hhplus.lecture.service;

import com.hhplus.lecture.dto.ApplyDto;
import com.hhplus.lecture.entity.Apply;
import com.hhplus.lecture.entity.Lecture;
import com.hhplus.lecture.entity.Users;
import com.hhplus.lecture.repository.fake.*;
import com.hhplus.lecture.validation.Validator;

import java.util.Date;
import java.util.List;

public class StubApplyServiceImpl implements ApplyService {

    private final UserInfoRepository userInfoRepository;
    private final LectureInfoRepository lectureInfoRepository;
    private final LectureHistoryInfoRepository lectureHistoryInfoRepository;
    private final ApplyInfoRepository applyInfoRepository;

    public StubApplyServiceImpl(UserInfoRepository userInfoRepository, LectureInfoRepository lectureInfoRepository, LectureHistoryInfoRepository lectureHistoryInfoRepository, ApplyInfoRepository applyInfoRepository) {
        this.userInfoRepository = userInfoRepository;
        this.lectureInfoRepository = lectureInfoRepository;
        this.lectureHistoryInfoRepository = lectureHistoryInfoRepository;
        this.applyInfoRepository = applyInfoRepository;
    }

    @Override
    public Apply apply(ApplyDto applyDto) throws Exception{

        try{
            Users userInfo = userInfoRepository.findById(applyDto.userId());
            Lecture lectureInfo = lectureInfoRepository.findById(applyDto.lcId());
            List<Apply> apply = applyInfoRepository.findById(applyDto.lcId(), applyDto.userId());
            validationCheck(userInfo, lectureInfo, apply.size()>0 ? apply.get(0) : null);
            Apply insertApply = null;
            if (apply.size() == 0) {
                 insertApply = Apply.builder().lcId(applyDto.lcId()).userId(applyDto.userId()).attendDate(new Date()).attendanceYn("Y").build();
                applyInfoRepository.save(insertApply);
                int maxAttendees = lectureInfo.getMaxAttendees() - 1;
                Lecture updateLecture = Lecture.builder()
                        .lcId(lectureInfo.getLcId())
                        .lectureName(lectureInfo.getLectureName())
                        .openDate(lectureInfo.getOpenDate())
                        .maxAttendees(maxAttendees).build();
                lectureInfoRepository.update(updateLecture.getLcId(), updateLecture);
            }

            return insertApply;
        }finally {

        }
    }

    private void validationCheck(Users userInfo, Lecture lectureInfo, Apply apply)throws Exception {
        Validator.validate(Validator.getUserInfoValue(userInfo));
        Validator.validate(Validator.getLectureInfoValue(lectureInfo));
        Validator.validate(Validator.openDateCompareToApplyDate(lectureInfo.getOpenDate(), new Date()));
        Validator.validate(Validator.applyOpenValue(apply));
    }

}
