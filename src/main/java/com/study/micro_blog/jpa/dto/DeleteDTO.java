package com.study.micro_blog.jpa.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DeleteDTO {
    private Long userId;
    private Long postId;
    private Long commentId;
}
