package com.hhplus.lecture.exception;

import com.hhplus.lecture.response.Response;

public class NoUserInfoException extends Exception{

    private Response response;

    public NoUserInfoException(Response response) {
        this.response = response;
    }

    public Response getResponse() {
        return response;
    }
}
