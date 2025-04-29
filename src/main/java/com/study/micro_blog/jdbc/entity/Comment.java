package com.study.micro_blog.jdbc.entity;

import com.study.micro_blog.jdbc.dto.CommentDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class Comment {

    private Long id;
    private Long userId;
    private Long postId;
    private String msg;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public CommentDTO toDto() {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setCommentId(this.id);
        commentDTO.setMsg(this.msg);
        return commentDTO;
    }

}
