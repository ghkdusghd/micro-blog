package com.study.micro_blog.jpa.service;

import com.study.micro_blog.jpa.dto.CommentDTO;
import com.study.micro_blog.jpa.dto.DeleteDTO;
import com.study.micro_blog.jpa.entity.Comment;
import com.study.micro_blog.jpa.entity.Post;
import com.study.micro_blog.jpa.entity.User;
import com.study.micro_blog.common.exception.custom.CustomAccessDeniedException;
import com.study.micro_blog.common.exception.custom.CommentNotFoundException;
import com.study.micro_blog.jpa.repository.JpaCommentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentServiceImpl implements JpaCommentService {

    private final JpaCommentRepository commentRepository;
    private final JpaUserService userService;
    private final JpaPostService postService;

    public CommentServiceImpl(JpaCommentRepository commentRepository, JpaUserService userService, JpaPostService postService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.postService = postService;
    }

    // 댓글 작성
    @Override
    public void save(CommentDTO commentDTO) {
        User user = userService.findById(commentDTO.getUserId());
        Post post = postService.findByPostId(commentDTO.getProductId());
        Comment comment = new Comment();
        comment.setUser(user);
        comment.setPost(post);
        comment.setMsg(commentDTO.getMsg());
        comment.setCreatedAt(LocalDateTime.now());
        commentRepository.save(comment);
    }

    // 댓글 수정
    @Override
    public void update(CommentDTO commentDTO) {
        Comment comment = commentRepository.findById(commentDTO.getCommentId())
                .orElseThrow(() -> new CommentNotFoundException("Comment [" + commentDTO.getCommentId() + "] not found", LocalDateTime.now(), HttpStatus.NOT_FOUND));

        // 댓글 작성자만 수정할 수 있다.
        if (comment.getUser().getId() != commentDTO.getUserId()) {
            throw new CustomAccessDeniedException("User [" + commentDTO.getUserId() + "] do not have permission", LocalDateTime.now(), HttpStatus.FORBIDDEN);
        }

        comment.setMsg(commentDTO.getMsg());
        comment.setUpdatedAt(LocalDateTime.now());
        commentRepository.save(comment);
    }

    // 댓글 삭제
    @Override
    public void delete(DeleteDTO deleteDTO) {
        Comment comment = commentRepository.findById(deleteDTO.getCommentId())
                .orElseThrow(() -> new CommentNotFoundException("Comment [" + deleteDTO.getCommentId() + "] not found", LocalDateTime.now(), HttpStatus.NOT_FOUND));

        // 댓글 작성자만 삭제할 수 있다.
        if (comment.getUser().getId() != deleteDTO.getUserId()) {
            throw new CustomAccessDeniedException("User [" + deleteDTO.getUserId() + "] do not have permission", LocalDateTime.now(), HttpStatus.FORBIDDEN);
        }

        commentRepository.delete(comment);
    }
}
