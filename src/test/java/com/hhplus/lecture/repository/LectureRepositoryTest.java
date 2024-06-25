package com.hhplus.lecture.repository;

import com.hhplus.lecture.entity.Apply;
import com.hhplus.lecture.entity.Lecture;
import com.hhplus.lecture.entity.LectureHistory;
import com.hhplus.lecture.entity.Users;
import com.hhplus.lecture.type.LectureType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class LectureRepositoryTest {

    @Autowired
    private LectureRepository lectureRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ApplyRepository applyRepository;
    @Autowired
    private LectureHistoryRepository lectureHistoryRepository;


    private static final Logger log = LoggerFactory.getLogger(LectureRepositoryTest.class);


    @Test
    @DisplayName("저장된 강의 불러오기1")
    public void get_lecture_info() {

        // given
        Lecture insertLecture = lectureRepository.save(Lecture.builder()
                .lcId(1L)
                .lectureName("한국사")
                .openDate(new Date())
                .maxAttendees(30).build());

        // when
        Optional<Lecture> lectureInfo = lectureRepository.findById(1L);

        // then
        assertEquals(insertLecture.getLcId(), lectureInfo.get().getLcId());
        assertEquals(insertLecture.getLectureName(), lectureInfo.get().getLectureName());
    }

    @Test
    @DisplayName("저장된 강의 불러오기2")
    public void get_lecture_info2() {

        // given
        long lmId = 2L;
        Lecture insertLecture = lectureRepository.save(Lecture.builder()
                .lcId(lmId)
                .lectureName("수학")
                .openDate(new Date())
                .maxAttendees(30).build());

        // when
        Optional<Lecture> lectureInfo = lectureRepository.findById(lmId);

        // then
        assertEquals(insertLecture.getLcId(), lectureInfo.get().getLcId());
        assertEquals(insertLecture.getLectureName(), lectureInfo.get().getLectureName());
    }



    @Test
    @DisplayName("유저 정보 불러오기")
    public void get_user() {
        //given
        Users insertUser = userRepository.save(Users.builder()
                .userId(1L)
                .userName("이하이").build());

        // when
        Users userInfo = userRepository.findAll().get(0);

        // then
        assertEquals(insertUser.getUserId(), userInfo.getUserId());
        assertEquals(insertUser.getUserName(), userInfo.getUserName());
    }

    @Test
    @DisplayName("유저 정보 불러오기2")
    public void get_user2() {
        //given
        long userId = 5L;
        Users insertUser = userRepository.save(Users.builder()
                .userId(userId)
                .userName("이하이").build());

        // when
        Optional<Users> userInfo = userRepository.findById(userId);

        // then
        assertEquals(insertUser.getUserId(), userInfo.get().getUserId());
        assertEquals(insertUser.getUserName(), userInfo.get().getUserName());
    }

    @Test
    @DisplayName("신청정보 저장하기")
    public void insert_apply() {
        //given
        long applyId= 1L;
        long lmId = 2L;
        long userId = 3L;
        Date attendDate = new Date();
        String attendanceYn = "Y";

        //when
        Apply apply = applyRepository.save(Apply.builder().applyId(applyId).lcId(lmId).userId(userId).attendDate(attendDate).attendanceYn(attendanceYn).build());

        //then
        assertEquals(applyId, apply.getApplyId());
        assertEquals(lmId, apply.getLcId());
        assertEquals(userId, apply.getUserId());
        assertEquals(attendDate, apply.getAttendDate());
        assertEquals(attendanceYn, apply.getAttendanceYn());
    }

    @Test
    @DisplayName("신청정보 가져오기")
    public void get_apply() {
        //given
        long applyId= 1L;
        long lcId = 2L;
        long userId = 3L;
        Date attendDate = new Date();
        String attendanceYn = "Y";
        applyRepository.save(Apply.builder().applyId(applyId).lcId(lcId).userId(userId).attendDate(attendDate).attendanceYn(attendanceYn).build());

        //when
        Optional<Apply> applyInfo = applyRepository.findByLcIdAndUserId(lcId, userId);

        //then
        assertEquals(applyId, applyInfo.get().getApplyId());
        assertEquals(lcId, applyInfo.get().getLcId());
        assertEquals(userId, applyInfo.get().getUserId());
        assertEquals(attendDate, applyInfo.get().getAttendDate());
        assertEquals(attendanceYn, applyInfo.get().getAttendanceYn());
    }

    @Test
    @DisplayName("모든 신청정보 가져오기")
    public void get_All_apply() {
        //given
        String attendanceYn = "Y";
        applyRepository.save(Apply.builder().applyId(111L).lcId(1000L).userId(51L).attendDate(new Date()).attendanceYn(attendanceYn).build());
        applyRepository.save(Apply.builder().applyId(112L).lcId(1001L).userId(52L).attendDate(new Date()).attendanceYn(attendanceYn).build());
        applyRepository.save(Apply.builder().applyId(113L).lcId(1002L).userId(53L).attendDate(new Date()).attendanceYn(attendanceYn).build());
        applyRepository.save(Apply.builder().applyId(114L).lcId(1003L).userId(54L).attendDate(new Date()).attendanceYn(attendanceYn).build());
        applyRepository.save(Apply.builder().applyId(115L).lcId(1004L).userId(55L).attendDate(new Date()).attendanceYn(attendanceYn).build());
        applyRepository.save(Apply.builder().applyId(116L).lcId(1005L).userId(56L).attendDate(new Date()).attendanceYn(attendanceYn).build());

        //when
        List<Apply> applyInfo = applyRepository.findAll();

        //then
        assertEquals(6, applyInfo.size());
    }

    @Test
    @DisplayName("이력 추가")
    public void insert_history() {

        //given
        long lmId = 1111L;
        long userId = 1112L;
        LectureType lectureType = LectureType.APPLICATION;
        Date attendData = new Date();

        //when
        LectureHistory lectureHistory = lectureHistoryRepository.save(LectureHistory.builder()
                .lmId(lmId)
                .userId(userId)
                .type(lectureType)
                .attendDate(attendData).build());

        log.info("lectureHistory : " + lectureHistory.toString());
        //then
        assertEquals(lmId, lectureHistory.getLmId());
        assertEquals(userId, lectureHistory.getUserId());
        assertEquals(lectureType, lectureHistory.getType());
        assertEquals(attendData, lectureHistory.getAttendDate());
    }

    @Test
    @DisplayName("Lecture 정보 업데이트")
    void update_lecture() {
        //given
        long lcId = 124L;
        Date openDate = new Date();
        Lecture lecture = Lecture.builder().lcId(lcId).lectureName("한국사").openDate(openDate).maxAttendees(30).build();
        lectureRepository.save(lecture);
        //when
        Lecture selectLecture = lectureRepository.findById(lcId).get();
        int rLecture = lectureRepository.updateLectureMaxAttendees(selectLecture.getMaxAttendees()-1, selectLecture.getLcId());
        //then
        assertThat(rLecture).isEqualTo(1);
    }

}
