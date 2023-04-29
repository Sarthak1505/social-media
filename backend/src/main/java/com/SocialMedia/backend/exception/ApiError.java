package com.SocialMedia.backend.exception;

import org.springframework.http.HttpStatus;

public class ApiError {

    private String message;

    private int httpStatusCode;

    private HttpStatus httpStatus;

    public ApiError() {
    }

    public ApiError(String message, int httpStatusCode, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatusCode = httpStatusCode;
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(int httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
