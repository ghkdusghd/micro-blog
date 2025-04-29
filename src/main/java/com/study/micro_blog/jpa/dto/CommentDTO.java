package com.study.micro_blog.jpa.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class CommentDTO {

    private Long commentId;
    private Long productId;
    private Long userId;
    private String msg;
    private LocalDateTime createdAt;

}
