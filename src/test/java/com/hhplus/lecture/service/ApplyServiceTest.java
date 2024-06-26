package com.hhplus.lecture.service;


import com.hhplus.lecture.business.entity.Apply;
import com.hhplus.lecture.business.entity.Lecture;
import com.hhplus.lecture.business.entity.Users;
import com.hhplus.lecture.business.repository.ApplyRepository;
import com.hhplus.lecture.business.repository.LectureRepository;
import com.hhplus.lecture.business.repository.UserRepository;
import com.hhplus.lecture.business.service.ApplyServiceImpl;
import com.hhplus.lecture.dto.ApplyDto;
import com.hhplus.lecture.exception.NoLectureInfoException;
import com.hhplus.lecture.exception.NoUserInfoException;
import com.hhplus.lecture.exception.NotExistApplyInfoException;
import com.hhplus.lecture.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
public class ApplyServiceTest {

    @InjectMocks
    ApplyServiceImpl applyService;

    @Mock
    private ApplyRepository applyRepository;
//
    @Mock
    private LectureRepository lectureRepository;
//
//    @Mock
//    private LectureHistoryRepository lectureHistoryRepository;

    @Mock
    UserRepository userRepository;

    public ApplyServiceTest() {
    }


    @DisplayName("유저 정보 없을 경우 Exception")
    @Test
    void no_user() throws Exception{
        //given
        ApplyDto applyDto = new ApplyDto(1L, 2L);
        when(userRepository.findById(applyDto.userId())).thenReturn(Optional.empty());

        //when && then
        assertThrows(NoUserInfoException.class, () ->
            applyService.apply(applyDto)
        );
    }

    @DisplayName("강의 정보 없을 경우 Exception")
    @Test
    void no_lecture() throws Exception{
        //given
        ApplyDto applyDto = new ApplyDto(1L, 2L);
        Users user = Users.builder().userId(applyDto.userId()).userName("p").build();
        when(userRepository.findById(applyDto.userId())).thenReturn(Optional.of(user));

        //when && then
        assertThrows(NoLectureInfoException.class, () ->
                applyService.apply(applyDto)
        );
    }

    @DisplayName("apply(신청)정보가 있을 경우")
    @Test
    void exist_apply_info() throws Exception{
        //given
        ApplyDto applyDto = new ApplyDto(1L, 2L);
        Apply apply = Apply.builder().userId(applyDto.userId()).lcId(applyDto.lcId()).attendDate(new Date()).attendanceYn("Y").build();
        Users user = Users.builder().userId(applyDto.userId()).userName("p").build();
        Lecture koreanLecture = Lecture.builder().lcId(applyDto.lcId()).lectureName("한국사").openDate(new Date()).maxAttendees(30).build();
        when(userRepository.findById(applyDto.userId())).thenReturn(Optional.of(user));
        when(applyRepository.findByLcIdAndUserId(applyDto.lcId(), applyDto.userId())).thenReturn(Optional.of(apply));
        when(lectureRepository.findById(applyDto.lcId())).thenReturn(Optional.of(koreanLecture));

        //when && then
        assertThrows(NotExistApplyInfoException.class, () -> {
            applyService.apply(applyDto);
        });

    }

    @DisplayName("apply(신청)정보가 없을 경우")
    @Test
    void not_exist_apply_info() throws Exception{
        //given
        ApplyDto applyDto = new ApplyDto(1L, 2L);
        Users user = Users.builder().userId(applyDto.userId()).userName("p").build();
        Lecture koreanLecture = Lecture.builder().lcId(applyDto.lcId()).lectureName("한국사").openDate(new Date()).maxAttendees(30).build();
        when(userRepository.findById(applyDto.userId())).thenReturn(Optional.of(user));
        when(lectureRepository.findById(applyDto.lcId())).thenReturn(Optional.of(koreanLecture));

        //when
        Apply apply = applyService.apply(applyDto);
        // && then
        assertThat(apply).isNull();
    }
}
