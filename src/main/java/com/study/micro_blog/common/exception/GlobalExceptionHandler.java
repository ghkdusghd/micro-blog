package com.study.micro_blog.common.exception;

import com.study.micro_blog.common.exception.custom.CustomAccessDeniedException;
import com.study.micro_blog.common.exception.custom.NotFoundException;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;

@Hidden
@ControllerAdvice
public class GlobalExceptionHandler {

    // 중복 User 는 DB insert 불가
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<?> handleDuplateEntry(SQLIntegrityConstraintViolationException ex) {
        ResponseError error = new ResponseError("Duplate entry", LocalDateTime.now(), HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(400).body(error);
    }

    // 비밀번호 불일치
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIncorrectPwd(IllegalArgumentException ex) {
        ResponseError error = new ResponseError(ex.getMessage(), LocalDateTime.now(), HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(400).body(error);
    }

    // Not Found Exception
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleUserNotFound(NotFoundException ex) {
        ResponseError error = new ResponseError(ex.getMessage(), ex.getLocalDateTime(), ex.getHttpStatus());
        return ResponseEntity.status(ex.getHttpStatus()).body(error);
    }

    // 작성자만 수정,삭제 가능
    @ExceptionHandler(CustomAccessDeniedException.class)
    public ResponseEntity<?> handleAccessDenied(CustomAccessDeniedException ex) {
        ResponseError error = new ResponseError(ex.getMessage(), ex.getLocalDateTime(), ex.getHttpStatus());
        return ResponseEntity.status(ex.getHttpStatus()).body(error);
    }

}
