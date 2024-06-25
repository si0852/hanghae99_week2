package com.hhplus.lecture.exception;

import com.hhplus.lecture.response.Response;

public class NotExistApplyInfoException extends Exception{

    private Response response;

    public NotExistApplyInfoException(Response response) {
        this.response = response;
    }

    public Response getResponse() {
        return response;
    }
}
