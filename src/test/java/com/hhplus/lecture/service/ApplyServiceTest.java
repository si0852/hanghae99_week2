package com.hhplus.lecture.service;


import com.hhplus.lecture.business.entity.*;
import com.hhplus.lecture.business.repository.*;
import com.hhplus.lecture.business.service.impl.ApplyServiceImpl;
import com.hhplus.lecture.dto.ApplyDto;
import com.hhplus.lecture.exception.*;
import com.hhplus.lecture.type.LectureType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.Date;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
public class ApplyServiceTest {

    private static final Logger log = LoggerFactory.getLogger(ApplyServiceImpl.class);
    @InjectMocks
    ApplyServiceImpl applyService;

    @Mock
    private ApplyRepository applyRepository;
//
    @Mock
    private LectureRepository lectureRepository;

    @Mock
    private ScheduleRepository scheduleRepository;
//
    @Mock
    private LectureHistoryRepository lectureHistoryRepository;

    @Mock
    UserRepository userRepository;


    // 특강목록 API Test 1) 스케쥴 목록에 없으면 등록된 스케줄 정보가 없습니다 Exception
    // 특상 신청 완료 여부 조회 API 1) user 정보 확인 2) schedule 정보 확인 3) 신청목록에 있는지 확인


    @DisplayName("유저 정보 없을 경우 Exception")
    @Test
    void no_user() throws Exception{
        //given
        ApplyDto applyDto = new ApplyDto(1L, 2L);
        when(userRepository.findByUserId(applyDto.userId())).thenReturn(null);

        //when && then
        assertThrows(NoUserInfoException.class, () ->
            applyService.apply(applyDto)
        );
    }

    @DisplayName("스케쥴 정보가 없을 경우 Exception")
    @Test
    void no_schedule() throws Exception{
        //given
        ApplyDto applyDto = new ApplyDto(1L, 2L);
        when(userRepository.findByUserId(applyDto.userId())).thenReturn(new Users(2L, "sihyun"));
        when(scheduleRepository.findById(applyDto.scheduleId())).thenReturn(null);

        //when && then
        assertThrows(NoScheduleInfoException.class, () ->
                applyService.apply(applyDto)
        );
    }

    @DisplayName("스케쥴정보 - 인원이 가득찼을 경우")
    @Test
    void schedule_full_of_people() throws Exception{
        //given
        ApplyDto applyDto = new ApplyDto(1L, 2L);
        when(userRepository.findByUserId(applyDto.userId())).thenReturn(new Users(2L, "sihyun"));
        Schedule schedule = new Schedule(3L, getDate(2024, 06,26, 13, 10), 0, "한국사");
        when(scheduleRepository.findById(applyDto.scheduleId())).thenReturn(schedule);

        //when && then
        assertThrows(FullOfPeopleException.class, () ->
                applyService.apply(applyDto)
        );
    }

    @DisplayName("스케쥴정보 - 오픈전에 신청할 경우")
    @Test
    void schedule_before_openDate() throws Exception{
        //given
        ApplyDto applyDto = new ApplyDto(1L, 2L);
        when(userRepository.findByUserId(applyDto.userId())).thenReturn(new Users(2L, "sihyun"));
        Schedule schedule = new Schedule(3L, getDate(2024, 06,27, 13, 10), 30, "한국사");
        when(scheduleRepository.findById(applyDto.scheduleId())).thenReturn(schedule);

        //when && then
        assertThrows(NoOpenDateException.class, () ->
                applyService.apply(applyDto)
        );
    }

    @DisplayName("강의정보 - 강의 정보가 없을 경우")
    @Test
    void no_lecture() throws Exception{
        //given
        ApplyDto applyDto = new ApplyDto(1L, 2L);
        when(userRepository.findByUserId(applyDto.userId())).thenReturn(new Users(2L, "sihyun"));
        log.info("openDate: " + getDate(2024, 06,27, 10, 00));
        log.info("applyDate: " + new Date());
        Schedule schedule = new Schedule(3L, getDate(2024, 06,27, 10, 00), 30, "한국사");
        when(scheduleRepository.findById(applyDto.scheduleId())).thenReturn(schedule);
        when(lectureRepository.findById(3L)).thenReturn(null);

        //when && then
        assertThrows(NoLectureInfoException.class, () ->
                applyService.apply(applyDto)
        );
    }

    @DisplayName("신청정보 - 신청정보가 null 일경우")
    @Test
    void apply_null() throws Exception{
        //given
        long applyId = 123123L;
        ApplyDto applyDto = new ApplyDto(1L, 2L);
        when(userRepository.findByUserId(applyDto.userId())).thenReturn(new Users(2L, "sihyun"));
        Schedule schedule = new Schedule( 3L, getDate(2024, 06,27, 10, 00), 30, "한국사");
        when(scheduleRepository.findById(applyDto.scheduleId())).thenReturn(schedule);
        when(lectureRepository.findById(3L)).thenReturn(new Lecture(3L, "한국사"));
        when(applyRepository.getApplyInfo(1L, 2L)).thenReturn(new Apply(1L, 2L, new Date(), "Y"));

        //when && then
        assertThrows(NotExistApplyInfoException.class, () ->
                applyService.apply(applyDto)
        );
    }

    @DisplayName("신청정보 - 신청정보 참석여부가 N일경우")
    @Test
    void apply_attendanceYn() throws Exception{
        //given
        long applyId = 123123L;
        ApplyDto applyDto = new ApplyDto(1L, 2L);
        when(userRepository.findByUserId(applyDto.userId())).thenReturn(new Users(2L, "sihyun"));
        Schedule schedule = new Schedule(3L, getDate(2024, 06,27, 10, 00), 30, "한국사");
        when(scheduleRepository.findById(applyDto.scheduleId())).thenReturn(schedule);
        when(lectureRepository.findById(3L)).thenReturn(new Lecture(3L, "한국사"));
        Apply getApply = new Apply(1L, 2L, new Date(), "N");
        when(applyRepository.getApplyInfo(1L, 2L)).thenReturn(getApply);

        //when
        Boolean apply = applyService.apply(applyDto);
        // then
        assertThat(apply).isTrue();
    }

    @DisplayName("신청정보 - save 정상 작동여부")
    @Test
    void apply_working() throws Exception{
        //given
        long userId = 2L;
        long scheduleId = 1L;
        long lcId = 3L;
        ApplyDto applyDto = new ApplyDto(scheduleId, userId);
        when(userRepository.findByUserId(applyDto.userId())).thenReturn(new Users(userId, "sihyun"));
        Schedule schedule = new Schedule(lcId, getDate(2024, 06,27, 10, 00), 30, "한국사");
        when(scheduleRepository.findById(applyDto.scheduleId())).thenReturn(schedule);
        when(lectureRepository.findById(lcId)).thenReturn(new Lecture(lcId, "한국사"));
        Apply getApply = new Apply(scheduleId, userId, new Date(), "N");
        when(applyRepository.getApplyInfo(scheduleId, userId)).thenReturn(getApply);
        LectureHistory lectureHistory = new LectureHistory(scheduleId, userId, LectureType.APPLICATION, new Date());

        //when
        Boolean savedApply = applyService.apply(applyDto);
        // then
        assertThat(savedApply).isTrue();

//        verify(userRepository, times(1)).findByUserId(userId);
//        verify(scheduleRepository, times(1)).findById(scheduleId);
//        verify(scheduleRepository, times(1)).save(schedule);
//        verify(lectureRepository, times(1)).findById(lcId);
//        verify(applyRepository, times(1)).getApplyInfo(scheduleId, userId);
//        verify(applyRepository, times(1)).save(saveApply);
//        verify(lectureHistoryRepository, times(1)).save(lectureHistory);
    }

    private Date getDate(int year, int month, int date, int hour, int minute) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month-1, date, hour, minute);
        return new Date(cal.getTimeInMillis());
    }
//
//    @DisplayName("apply(신청)정보가 있을 경우")
//    @Test
//    void exist_apply_info() throws Exception{
//        //given
//        ApplyDto applyDto = new ApplyDto(1L, 2L);
//        Apply apply = Apply.builder().userId(applyDto.userId()).lcId(applyDto.lcId()).attendDate(new Date()).attendanceYn("Y").build();
//        Users user = Users.builder().userId(applyDto.userId()).userName("p").build();
//        Lecture koreanLecture = Lecture.builder().lcId(applyDto.lcId()).lectureName("한국사").openDate(new Date()).maxAttendees(30).build();
//        when(userRepository.findById(applyDto.userId())).thenReturn(Optional.of(user));
//        when(applyRepository.findByLcIdAndUserId(applyDto.lcId(), applyDto.userId())).thenReturn(Optional.of(apply));
//        when(lectureRepository.findById(applyDto.lcId())).thenReturn(Optional.of(koreanLecture));
//
//        //when && then
//        assertThrows(NotExistApplyInfoException.class, () -> {
//            applyService.apply(applyDto);
//        });
//
//    }
//
//    @DisplayName("apply(신청)정보가 없을 경우")
//    @Test
//    void not_exist_apply_info() throws Exception{
//        //given
//        ApplyDto applyDto = new ApplyDto(1L, 2L);
//        Users user = Users.builder().userId(applyDto.userId()).userName("p").build();
//        Lecture koreanLecture = Lecture.builder().lcId(applyDto.lcId()).lectureName("한국사").openDate(new Date()).maxAttendees(30).build();
//        when(userRepository.findById(applyDto.userId())).thenReturn(Optional.of(user));
//        when(lectureRepository.findById(applyDto.lcId())).thenReturn(Optional.of(koreanLecture));
//
//        //when
//        Apply apply = applyService.apply(applyDto);
//        // && then
//        assertThat(apply).isNull();
//    }
}
