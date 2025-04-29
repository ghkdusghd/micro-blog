package com.study.micro_blog.jpa.dto;

import com.study.micro_blog.jpa.entity.Post;
import com.study.micro_blog.jpa.entity.Status;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class PostDTO {

    private Long postId;
    private Long userId;
    private String title;
    private String body;
    private Status status;

    // 댓글
    private List<CommentDTO> commentList;

    public Post toEntity() {
        Post post = new Post();
        post.setId(this.postId);
        post.setTitle(this.title);
        post.setBody(this.body);
        post.setStatus(this.status);
        return post;
    }

}
