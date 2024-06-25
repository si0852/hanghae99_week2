package com.hhplus.lecture.exception;

import com.hhplus.lecture.response.Response;

public class NoLectureInfoException extends Exception{

    private Response response;

    public NoLectureInfoException(Response response) {
        this.response = response;
    }

    public Response getResponse() {
        return response;
    }
}
