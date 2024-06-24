package com.hhplus.lecture.util;

import com.hhplus.lecture.response.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

//    @ExceptionHandler(value = Exception.class)
//    public ResponseEntity<Response> handleException(Exception e) {
//        return ResponseEntity.status(e)
//    }
}
