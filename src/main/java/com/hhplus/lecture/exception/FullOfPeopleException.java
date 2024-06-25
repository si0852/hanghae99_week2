package com.hhplus.lecture.exception;

import com.hhplus.lecture.response.Response;

public class FullOfPeopleException extends Exception{

    private Response response;

    public FullOfPeopleException(Response response) {
        this.response = response;
    }

    public Response getResponse() {
        return response;
    }
}
