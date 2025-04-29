package com.study.micro_blog.common.exception.custom;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter @Setter
public class CustomAccessDeniedException extends RuntimeException{
    private String message;
    private LocalDateTime localDateTime;
    private HttpStatus httpStatus;

    public CustomAccessDeniedException(String message, LocalDateTime localDateTime, HttpStatus httpStatus) {
        this.message = message;
        this.localDateTime = localDateTime;
        this.httpStatus = httpStatus;
    }
}
