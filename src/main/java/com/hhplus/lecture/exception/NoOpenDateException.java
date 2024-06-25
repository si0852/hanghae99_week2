package com.hhplus.lecture.exception;

import com.hhplus.lecture.response.Response;

public class NoOpenDateException extends Exception{

    private Response response;

    public NoOpenDateException(Response response) {
        this.response = response;
    }

    public Response getResponse() {
        return response;
    }
}
