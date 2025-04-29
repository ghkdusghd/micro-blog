package com.study.micro_blog.common.exception.custom;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public abstract class NotFoundException extends RuntimeException {

    private final LocalDateTime localDateTime;
    private final HttpStatus httpStatus;

    public NotFoundException(String message, LocalDateTime localDateTime, HttpStatus httpStatus) {
        super(message);
        this.localDateTime = localDateTime;
        this.httpStatus = httpStatus;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
