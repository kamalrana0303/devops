package com.devops.developers.exception;

import org.springframework.http.HttpStatus;

import java.util.List;


public class ErrorDetails {
    HttpStatus httpStatus;
    String message;
    List<String> errors;

    public ErrorDetails() {
    }

    public ErrorDetails(HttpStatus httpStatus, String message, List<String> errors) {
        this.httpStatus = httpStatus;
        this.message = message;
        this.errors = errors;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
