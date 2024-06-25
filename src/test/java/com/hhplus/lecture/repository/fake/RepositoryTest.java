package com.hhplus.lecture.repository.fake;

import com.hhplus.lecture.entity.Apply;
import com.hhplus.lecture.entity.Lecture;
import com.hhplus.lecture.entity.LectureHistory;
import com.hhplus.lecture.entity.Users;
import com.hhplus.lecture.type.LectureType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class RepositoryTest {

    UserInfoRepository userInfoRepository = new FakeUserInfoRepositoryImpl();
    LectureInfoRepository lectureInfoRepository = new FakeLectureInfoRepositoryImpl();
    LectureHistoryInfoRepository lectureHistoryInfoRepository = new FakeLectureHistoryInfoRepository();
    ApplyInfoRepository applyInfoRepository = new FakeApplyInfoRepositoryImpl();

    private static final Logger log = LoggerFactory.getLogger(RepositoryTest.class);

    @Test
    @DisplayName("User 정보 조회")
    void get_Users() {
        //given
        long userId = 123L;
        Users users = Users.builder().userId(userId).userName("sihyun").build();
        userInfoRepository.save(users);
        //when
        Users selectUser = userInfoRepository.findById(userId);
        //then
        assertThat(users).isEqualTo(selectUser);
    }

    @Test
    @DisplayName("User 정보 조회 - null인 경우")
    void get_Users_Null() {
        //given
        long userId = 123L;
        //when
        Users selectUser = userInfoRepository.findById(userId);
        //then
        assertNull(selectUser);
    }

    @Test
    @DisplayName("Lecture 정보 조회")
    void get_Lectures() {
        //given
        long lcId = 124L;
        Date openDate = getDate(2024, 06, 20);
        Lecture lecture = Lecture.builder().lmId(lcId).lectureName("한국사").openDate(openDate).maxAttendees(30).build();
        lectureInfoRepository.save(lecture);
        //when
        Lecture selectLecture = lectureInfoRepository.findById(lcId);
        //then
        assertThat(lecture).isEqualTo(selectLecture);
    }

    @Test
    @DisplayName("Lecture 정보 조회 - null인 경우")
    void get_Lectures_Null() {
        //given
        long lcId = 124L;
        //when
        Lecture selectLecture = lectureInfoRepository.findById(lcId);
        //then
        assertNull(selectLecture);
    }

    private Date getDate(int year, int month, int date) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month-1, date);
        return new Date(cal.getTimeInMillis());
    }

    @Test
    @DisplayName("History 정보 저장")
    void save_history() {
        //given
        long historyId = UUID.randomUUID().getLeastSignificantBits();
        long userId = UUID.randomUUID().getLeastSignificantBits();
        long lmId = UUID.randomUUID().getLeastSignificantBits();
        //when
        LectureHistory lectureHistory = lectureHistoryInfoRepository.save(LectureHistory.builder().
                historyId(historyId).userId(userId).lmId(lmId).
                type(LectureType.APPLICATION).attendDate(getDate(2024, 06, 24))
                .build());
        //then
        assertThat(lectureHistory).isEqualTo(lectureHistoryInfoRepository.selectAllByHistoryId(historyId).get(0));
    }

    @Test
    @DisplayName("Apply 정보 저장")
    void save_apply() {
        //given
        long applyId = UUID.randomUUID().getLeastSignificantBits();
        long userId = UUID.randomUUID().getLeastSignificantBits();
        long lmId = UUID.randomUUID().getLeastSignificantBits();
        String attendanceYn = "Y";
        Apply apply = Apply.builder().
                        applyId(applyId).lmId(lmId).userId(userId)
                .attendDate(getDate(2024, 06, 21))
                .attendanceYn(attendanceYn)
                .build();
        //when
        Apply saveApply = applyInfoRepository.save(apply);
        log.info("saveApply : " + saveApply.toString());
        List<Apply> selectApply = applyInfoRepository.findById(lmId, userId);
        log.info("selectApply : " + selectApply.toString());
        //then
        assertThat(saveApply).isEqualTo(selectApply.get(0));
    }

}