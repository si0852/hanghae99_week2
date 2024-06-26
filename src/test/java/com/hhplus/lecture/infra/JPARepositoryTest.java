package com.hhplus.lecture.infra;

import com.hhplus.lecture.business.entity.Apply;
import com.hhplus.lecture.business.entity.Lecture;
import com.hhplus.lecture.business.entity.LectureHistory;
import com.hhplus.lecture.business.entity.Users;
import com.hhplus.lecture.business.repository.ApplyRepository;
import com.hhplus.lecture.business.repository.LectureHistoryRepository;
import com.hhplus.lecture.business.repository.LectureRepository;
import com.hhplus.lecture.business.repository.UserRepository;
import com.hhplus.lecture.type.LectureType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class JPARepositoryTest {


    @Autowired
    private LectureRepository lectureRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ApplyRepository applyRepository;

    @Autowired
    private LectureHistoryRepository lectureHistoryRepository;


    private static final Logger log = LoggerFactory.getLogger(JPARepositoryTest.class);


    @Test
    @DisplayName("저장된 강의 불러오기1")
    public void get_lecture_info() throws Exception{

        // given
        Lecture insertLecture = lectureRepository.save(Lecture.builder()
                .lcId(1L)
                .lectureName("한국사")
                .openDate(new Date())
                .maxAttendees(30).build());

        // when
        Lecture lectureInfo = lectureRepository.findById(1L).orElse(null);

        // then
        assertEquals(insertLecture.getLcId(), lectureInfo.getLcId());
        assertEquals(insertLecture.getLectureName(), lectureInfo.getLectureName());
    }

    @Test
    @DisplayName("저장된 강의 불러오기2")
    public void get_lecture_info2() throws Exception{

        // given
        long lmId = 2L;
        Lecture insertLecture = lectureRepository.save(Lecture.builder()
                .lcId(lmId)
                .lectureName("수학")
                .openDate(new Date())
                .maxAttendees(30).build());

        // when
        Lecture lectureInfo = lectureRepository.findById(lmId).orElse(null);

        // then
        assertEquals(insertLecture.getLcId(), lectureInfo.getLcId());
        assertEquals(insertLecture.getLectureName(), lectureInfo.getLectureName());
    }



    @Test
    @DisplayName("유저 정보 불러오기")
    public void get_user() throws Exception {
        //given
        Users insertUser = userRepository.save(Users.builder()
                .userId(1L)
                .userName("이하이").build());

        // when
        Users userInfo = userRepository.findById(1L).orElse(null);

        // then
        assertEquals(insertUser.getUserId(), userInfo.getUserId());
        assertEquals(insertUser.getUserName(), userInfo.getUserName());
    }

    @Test
    @DisplayName("유저 정보 불러오기2")
    public void get_user2() throws Exception {
        //given
        long userId = 5L;
        Users insertUser = userRepository.save(Users.builder()
                .userId(userId)
                .userName("이하이").build());

        // when
        Users userInfo = userRepository.findById(userId).orElse(null);

        // then
        assertEquals(insertUser.getUserId(), userInfo.getUserId());
        assertEquals(insertUser.getUserName(), userInfo.getUserName());
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
    public void get_apply() throws Exception {
        //given
        long applyId= 1L;
        long lcId = 2L;
        long userId = 3L;
        Date attendDate = new Date();
        String attendanceYn = "Y";
        applyRepository.save(Apply.builder().applyId(applyId).lcId(lcId).userId(userId).attendDate(attendDate).attendanceYn(attendanceYn).build());

        //when
        Apply applyInfo = applyRepository.findByLcIdAndUserId(lcId, userId).orElse(null);

        //then
        assertEquals(applyId, applyInfo.getApplyId());
        assertEquals(lcId, applyInfo.getLcId());
        assertEquals(userId, applyInfo.getUserId());
        assertEquals(attendDate, applyInfo.getAttendDate());
        assertEquals(attendanceYn, applyInfo.getAttendanceYn());
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
    void update_lecture()  throws Exception{
        //given
        long lcId = 124L;
        Date openDate = new Date();
        Lecture lecture = Lecture.builder().lcId(lcId).lectureName("한국사").openDate(openDate).maxAttendees(30).build();
        lectureRepository.save(lecture);
        //when
        Lecture selectLecture = lectureRepository.findById(lcId).orElse(null);
        Lecture updateLecture = selectLecture.builder().lcId(selectLecture.getLcId()).lectureName(selectLecture.getLectureName())
                .openDate(selectLecture.getOpenDate()).maxAttendees(selectLecture.getMaxAttendees()-1).build();
        Lecture rLecture = lectureRepository.save(updateLecture);
        //then
        assertThat(rLecture.getLcId()).isEqualTo(updateLecture.getLcId());
        assertThat(rLecture.getMaxAttendees()).isEqualTo(updateLecture.getMaxAttendees());
    }

}
