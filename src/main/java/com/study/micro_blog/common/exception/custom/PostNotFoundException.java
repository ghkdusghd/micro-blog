package com.study.micro_blog.common.exception.custom;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter @Setter
public class PostNotFoundException extends NotFoundException {
    public PostNotFoundException(String message, LocalDateTime dateTime, HttpStatus status) {
        super(message, dateTime, status);
    }
}
