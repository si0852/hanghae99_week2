package com.hhplus.lecture.service;


import com.hhplus.lecture.entity.Apply;
import com.hhplus.lecture.entity.Lecture;
import com.hhplus.lecture.entity.Users;
import com.hhplus.lecture.exception.*;
import com.hhplus.lecture.repository.fake.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.Date;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ApplyServiceTest {

    // fakeRepository
    UserInfoRepository userInfoRepository = new FakeUserInfoRepositoryImpl();
    LectureInfoRepository lectureInfoRepository = new FakeLectureInfoRepositoryImpl();
    LectureHistoryInfoRepository lectureHistoryInfoRepository = new FakeLectureHistoryInfoRepository();
    ApplyInfoRepository applyInfoRepository = new FakeApplyInfoRepositoryImpl();

    private static final Logger log = LoggerFactory.getLogger(ApplyServiceTest.class);

    ApplyService applyService;

    @BeforeEach
    void set_data() {
        applyService  = new StubApplyServiceImpl(userInfoRepository, lectureInfoRepository, lectureHistoryInfoRepository, applyInfoRepository);
        Users user = Users.builder().userId(1L).userName("sihyun1").build();
        userInfoRepository.save(user);
        Users user2 = Users.builder().userId(2L).userName("sihyun2").build();
        userInfoRepository.save(user2);
        Users user3 = Users.builder().userId(3L).userName("sihyun3").build();
        userInfoRepository.save(user3);
        Users user4 = Users.builder().userId(4L).userName("sihyun4").build();
        userInfoRepository.save(user4);
        Users user5 = Users.builder().userId(5L).userName("sihyun5").build();
        userInfoRepository.save(user5);
        Users user6 = Users.builder().userId(6L).userName("sihyun6").build();
        userInfoRepository.save(user6);
        Users user7 = Users.builder().userId(7L).userName("sihyun7").build();
        userInfoRepository.save(user7);
        Users user8 = Users.builder().userId(8L).userName("sihyun8").build();
        userInfoRepository.save(user8);

        Lecture lecture = Lecture.builder().lmId(11L).lectureName("한국사")
                .openDate(getDate(2024, 06, 14, 13, 0)).maxAttendees(30).build();
        lectureInfoRepository.save(lecture);
        Lecture lecture2 = Lecture.builder().lmId(12L).lectureName("세계사")
                .openDate(getDate(2024, 06, 11, 13, 0)).maxAttendees(30).build();
        lectureInfoRepository.save(lecture2);

        Lecture lecture3 = Lecture.builder().lmId(13L).lectureName("알고리즘")
                .openDate(getDate(2024, 06, 10, 13, 0)).maxAttendees(0).build();
        lectureInfoRepository.save(lecture3);

        Lecture lecture4 = Lecture.builder().lmId(14L).lectureName("Spring")
                .openDate(getDate(2024, 06, 30, 15, 17)).maxAttendees(30).build();
        lectureInfoRepository.save(lecture4);

        Lecture lecture5 = Lecture.builder().lmId(15L).lectureName("TDD")
                .openDate(getDate(2024, 06, 18, 16, 0)).maxAttendees(0).build();
        lectureInfoRepository.save(lecture5);

        Lecture lecture6 = Lecture.builder().lmId(16L).lectureName("영어")
                .openDate(getDate(2024, 06, 07, 11, 0)).maxAttendees(30).build();
        lectureInfoRepository.save(lecture6);

        Lecture lecture7 = Lecture.builder().lmId(17L).lectureName("영어1")
                .openDate(getDate(2024, 06, 07, 11, 0)).maxAttendees(30).build();
        lectureInfoRepository.save(lecture7);

        Apply apply = Apply.builder().lmId(16L).userId(5L).attendDate(new Date()).attendanceYn("Y").build();
        applyInfoRepository.save(apply);

        Apply apply2 = Apply.builder().lmId(15L).userId(5L).attendDate(new Date()).attendanceYn("N").build();
        applyInfoRepository.save(apply2);
    }

    @Test
    @DisplayName("강의 수강 신청 - user 정보가 없으면 실패")
    void apply_selectUser() throws Exception{
        //given
        long userId = 9L;
        long lcId = 8L;
        //when
        //then
        assertThrows(NoUserInfoException.class, () -> {applyService.apply(lcId, userId);});
    }

    @Test
    @DisplayName("강의 수강 신청 - Lecture 정보가 없으면 실패")
    void apply_selectLecture() throws Exception{
        //given
        long userId = 3L;
        long lcId = 8L;
        //when
        //then
        assertThrows(NoLectureInfoException.class, () -> {applyService.apply(lcId, userId);});
    }

    @Test
    @DisplayName("강의 수강 신청 - Lecture 자리가 꽉찼을 경우 실패")
    void apply_fullOfLecture() throws Exception{
        //given
        long userId = 5L;
        long lcId = 15L;
        //when
        //then
        assertThrows(FullOfPeopleException.class, () -> {applyService.apply(lcId, userId);});
    }

    @Test
    @DisplayName("강의 수강 신청 - Lecture 오픈 시간보다 전이면 실패")
    void apply_open_date() throws Exception{
        //given
        long userId = 5L;
        long lcId = 14L;
        //when
        //then
        assertThrows(NoOpenDateException.class, () -> {applyService.apply(lcId, userId);});
    }

    @Test
    @DisplayName("강의 수강 신청 - 이미 수강신청한 경우")
    void apply_applied_user() throws Exception{
        //given
        long userId = 5L;
        long lcId = 16l;
        //when
//        Apply apply = applyService.apply(lcId, userId);

        //then
//        assertThat(apply).isNotNull();
        assertThrows(NotExistApplyInfoException.class, () -> {
            applyService.apply(lcId, userId);});
    }

    @Test
    @DisplayName("강의 수강 신청 - 수강신청은 했었지만 취소한경우")
    void apply_applied_cancel() throws Exception{
        //given
        long userId = 5L;
        long lcId = 17l;
        //when
        Apply apply = applyService.apply(lcId, userId);

        //then
        assertThat(apply).isNotNull();
    }

    @Test
    @DisplayName("강의 수강 신청 - 수강신청 안한 경우")
    void apply_no_applied_user() throws Exception{
        //given
        long userId = 5L;
        long lcId = 11l;
        //when
        Apply apply = applyService.apply(lcId, userId);

        //then
        assertThat(apply).isNotNull();
    }

    @Test
    @DisplayName("강의 수강 신청")
    void apply() throws Exception{
        //given
        long userId = 5L;
        long lcId = 11l;
        //when
        applyService.apply(lcId, userId);
        Apply apply = applyInfoRepository.findById(lcId, userId).get(0);
        log.info("applyList: ", apply);
        Lecture lecture = lectureInfoRepository.findById(lcId);

        //then
        assertEquals(29 ,lecture.getMaxAttendees());
        assertEquals(userId, apply.getUserId());
        assertEquals(lcId, apply.getLmId());

    }



    private Date getDate(int year, int month, int date, int time, int minute) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month-1, date, time, minute);
        return new Date(cal.getTimeInMillis());
    }
}
