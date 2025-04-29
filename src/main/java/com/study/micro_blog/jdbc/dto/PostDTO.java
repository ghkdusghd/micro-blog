package com.study.micro_blog.jdbc.dto;

import com.study.micro_blog.jdbc.entity.Post;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class PostDTO {

    private Long postId;
    private Long userId;
    private String title;
    private String body;
    private String status;
    private List<CommentDTO> commentList;

}
