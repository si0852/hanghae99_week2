package com.hhplus.lecture.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hhplus.lecture.business.service.impl.ApplyServiceImpl;
import com.hhplus.lecture.presentation.controller.LectureController;
import com.hhplus.lecture.presentation.dto.ApplyDto;
import com.hhplus.lecture.presentation.dto.ResponseDto;
import org.apache.coyote.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LectureController.class)
public class ApplyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ApplyServiceImpl applyService;

    @Autowired
    ObjectMapper objectMapper;


    @DisplayName("수강신청 - success")
    @Test
    void apply_success() throws Exception {
        ApplyDto applyDto = new ApplyDto();
        applyDto.setScheduleId(1L);
        applyDto.setUserId(2L);

        String content = objectMapper.writeValueAsString(applyDto);
        String result = objectMapper.writeValueAsString(new ResponseDto(200, "Success", true));
        given(applyService.apply(applyDto)).willReturn(true);

        mockMvc.perform(post("/lecture/apply")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk())
                .andExpect(content().string(result));
    }

    @DisplayName("수강신청 - Fail")
    @Test
    void apply_fail() throws Exception {
        ApplyDto applyDto = new ApplyDto();
        applyDto.setScheduleId(1L);
        applyDto.setUserId(2L);

        String content = objectMapper.writeValueAsString(applyDto);
        String result = objectMapper.writeValueAsString(new ResponseDto(200, "Fail", false));
        given(applyService.apply(applyDto)).willReturn(false);

        mockMvc.perform(post("/lecture/apply")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk())
                .andExpect(content().string(result));
    }
}
