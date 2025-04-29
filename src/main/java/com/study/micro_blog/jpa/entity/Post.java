package com.study.micro_blog.jpa.entity;

import com.study.micro_blog.jpa.dto.CommentDTO;
import com.study.micro_blog.jpa.dto.PostDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "posts")
@Getter @Setter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "users_id", nullable = false)
    private User user;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(nullable = false)
    private String body;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments;

    public PostDTO toDtoWithComment() {
        PostDTO postDTO = new PostDTO();
        postDTO.setPostId(this.id);
        postDTO.setUserId(this.user.getId());
        postDTO.setTitle(this.title);
        postDTO.setBody(this.body);
        postDTO.setStatus(this.status);

        if(this.comments != null) {
            List<CommentDTO> commentList = this.comments.stream().map(comment -> {
                CommentDTO commentDTO = new CommentDTO();
                commentDTO.setCommentId(comment.getId());
                commentDTO.setProductId(this.id);
                commentDTO.setUserId(comment.getUser().getId());
                commentDTO.setMsg(comment.getMsg());
                commentDTO.setCreatedAt(comment.getCreatedAt());
                return commentDTO;
            }).collect(Collectors.toList());
            postDTO.setCommentList(commentList);
        }

        return postDTO;
    }

}
