package com.study.micro_blog.jdbc.entity;

import com.study.micro_blog.jdbc.dto.PostDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class Post {

    private Long id;
    private Long userId;
    private String title;
    private String body;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public PostDTO toDto() {
        PostDTO postDTO = new PostDTO();
        postDTO.setPostId(this.id);
        postDTO.setUserId(this.userId);
        postDTO.setTitle(this.title);
        postDTO.setBody(this.body);
        postDTO.setStatus(this.status);
        return postDTO;
    }

}
