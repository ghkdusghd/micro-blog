package com.study.micro_blog.common.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
public class ResponseError {

    private String message;
    private LocalDateTime timestamp;
    private HttpStatus status;

    public ResponseError(String message, LocalDateTime timestamp, HttpStatus status) {
        this.message = message;
        this.timestamp = timestamp;
        this.status = status;
    }

}
