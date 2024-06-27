package com.hhplus.lecture.business.service.impl;

import com.hhplus.lecture.business.entity.*;
import com.hhplus.lecture.business.repository.*;
import com.hhplus.lecture.business.service.ApplyService;
import com.hhplus.lecture.dto.ApplyDto;
import com.hhplus.lecture.exception.*;
import com.hhplus.lecture.response.Response;
import com.hhplus.lecture.type.LectureType;
import com.hhplus.lecture.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ApplyServiceImpl implements ApplyService {
    private static final Logger log = LoggerFactory.getLogger(ApplyServiceImpl.class);

    private final ApplyRepository applyRepository;
    private final LectureRepository lectureRepository;
    private final UserRepository userRepository;
    private final LectureHistoryRepository lectureHistoryRepository;
    private final ScheduleRepository scheduleRepository;

    public ApplyServiceImpl(ApplyRepository applyRepository, LectureRepository lectureRepository, UserRepository userRepository, LectureHistoryRepository lectureHistoryRepository, ScheduleRepository scheduleRepository) {
        this.applyRepository = applyRepository;
        this.lectureRepository = lectureRepository;
        this.userRepository = userRepository;
        this.lectureHistoryRepository = lectureHistoryRepository;
        this.scheduleRepository = scheduleRepository;
    }

    @Transactional
    @Override
    public Apply apply(ApplyDto applyDto) throws Exception{
        Date today = new Date();
        try{
            Users userInfo = userRepository.findByUserId(applyDto.userId());
            if (userInfo == null) throw new NoUserInfoException(new Response(500, "유저정보가 없습니다."));

            Schedule scheduleInfo = scheduleRepository.findById(applyDto.scheduleId());
            if (scheduleInfo == null) throw new NoScheduleInfoException(new Response(500, "등록된 스케쥴이 없습니다."));
            if (!scheduleInfo.isMaxAttendees()) throw new FullOfPeopleException(new Response(500, "인원이 가득찼습니다."));


            if(scheduleInfo.isOpenDate(today)) throw new NoOpenDateException(new Response(500, "오픈일자가 아닙니다."));

            Lecture lectureInfo = lectureRepository.findById(scheduleInfo.getLcId());
            if(lectureInfo == null) throw new NoLectureInfoException(new Response(500, "등록된 강의가 없습니다."));

            Apply applyInfo = applyRepository.getApplyInfo(applyDto.scheduleId(), applyDto.userId());
            if(applyInfo != null && applyInfo.getAttendanceYn().equals("Y")) throw new NotExistApplyInfoException(new Response(500, "신청정보가 존재합니다."));

            Apply newApplyInfo = new Apply(applyDto.scheduleId(), applyDto.userId(), today, "Y");
            Apply saveApply = applyRepository.save(newApplyInfo);

            scheduleInfo.decrease();
            scheduleRepository.save(scheduleInfo);

            return saveApply;
        }finally {
            lectureHistoryRepository.save(new LectureHistory(applyDto.scheduleId(), applyDto.userId(), LectureType.APPLICATION, today));
        }
    }

    @Override
    public Boolean isApplied(long scheduleId, long userId) throws Exception{
        Users userInfo = userRepository.findByUserId(userId);
        if (userInfo == null) {
            throw new NoUserInfoException(new Response(500, "유저정보가 없습니다."));
        }
        Schedule scheduleInfo = scheduleRepository.findById(scheduleId);
        if (scheduleInfo == null) {
            throw new NoScheduleInfoException(new Response(500, "등록된 스케쥴이 없습니다."));
        }
        Apply applyInfo = applyRepository.getApplyInfo(scheduleId, userId);
        return applyInfo == null ? false : true;
    }

    @Override
    public List<Schedule> getScheduledList() throws Exception {
        List<Schedule> scheduleList = scheduleRepository.getScheduleList();
        if (scheduleList.size() == 0) {
            throw new NoScheduleInfoException(new Response(500, "등록된 스케쥴이 없습니다."));
        }
        return scheduleList;
    }

    private void validationCheck(Apply apply)throws Exception {
        Validator.validate(Validator.applyOpenValue(apply));
    }
}
