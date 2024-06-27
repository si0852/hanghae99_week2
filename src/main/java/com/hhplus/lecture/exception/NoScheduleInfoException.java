package com.hhplus.lecture.exception;

import com.hhplus.lecture.response.Response;

public class NoScheduleInfoException extends Exception{

    private Response response;

    public NoScheduleInfoException(Response response) {
        this.response = response;
    }

    public Response getResponse() {
        return response;
    }
}
