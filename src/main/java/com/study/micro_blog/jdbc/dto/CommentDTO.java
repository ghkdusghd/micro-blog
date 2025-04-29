package com.study.micro_blog.jdbc.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class CommentDTO {

    private Long commentId;
    private Long postId;
    private Long userId;
    private String msg;
    private LocalDateTime createdAt;

}
