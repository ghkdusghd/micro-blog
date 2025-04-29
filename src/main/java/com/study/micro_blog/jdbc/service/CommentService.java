package com.study.micro_blog.jdbc.service;

import com.study.micro_blog.common.exception.custom.CustomAccessDeniedException;
import com.study.micro_blog.common.exception.custom.PostNotFoundException;
import com.study.micro_blog.common.exception.custom.UserNotFoundException;
import com.study.micro_blog.jdbc.dto.CommentDTO;
import com.study.micro_blog.jdbc.dto.DeleteDTO;
import com.study.micro_blog.jdbc.entity.Comment;
import com.study.micro_blog.jdbc.entity.Post;
import com.study.micro_blog.jdbc.repository.CommentRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostService postService;

    public CommentService(CommentRepository commentRepository, PostService postService) {
        this.commentRepository = commentRepository;
        this.postService = postService;
    }

    // 댓글 id 로 조회
    public Comment findById(Long commentId) {
        try {
            return commentRepository.findById(commentId);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            throw new UserNotFoundException("Comment [" + commentId + "] not found", LocalDateTime.now(), HttpStatus.NOT_FOUND);
        }
    }

    // 게시글 id 로 조회
    public List<CommentDTO> findByPostId(Long postId) {
        try {
            List<Comment> comment = commentRepository.findByPostId(postId);
            return comment.stream().map(Comment::toDto).toList();
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            throw new UserNotFoundException("Comment [" + postId + "] not found", LocalDateTime.now(), HttpStatus.NOT_FOUND);
        }
    }

    // 댓글 작성
    public void save(CommentDTO commentDTO) {
        try {
            Post post = postService.findByPostId(commentDTO.getPostId());
        } catch (EmptyResultDataAccessException e) {
            throw new PostNotFoundException("Comment [" + commentDTO.getPostId() + "] not found", LocalDateTime.now(), HttpStatus.NOT_FOUND);
        }
        Comment comment = new Comment();
        comment.setId(commentDTO.getCommentId());
        comment.setUserId(commentDTO.getUserId());
        comment.setPostId(commentDTO.getPostId());
        comment.setMsg(commentDTO.getMsg());
        commentRepository.save(comment);
    }

    // 댓글 수정
    public void update(CommentDTO commentDTO) {
        Comment comment = findById(commentDTO.getCommentId());

        // 댓글 작성자만 수정할 수 있다.
        if (comment.getUserId() != commentDTO.getUserId()) {
            throw new CustomAccessDeniedException("User [" + commentDTO.getUserId() + "] do not have permission", LocalDateTime.now(), HttpStatus.FORBIDDEN);
        }

        comment.setMsg(commentDTO.getMsg());
        comment.setUpdatedAt(LocalDateTime.now());
        commentRepository.update(comment);
    }

    // 댓글 삭제
    public void delete(DeleteDTO deleteDTO) {
        Comment comment = findById(deleteDTO.getCommentId());

        // 댓글 작성자만 삭제할 수 있다.
        if (comment.getUserId() != deleteDTO.getUserId()) {
            throw new CustomAccessDeniedException("User [" + deleteDTO.getUserId() + "] do not have permission", LocalDateTime.now(), HttpStatus.FORBIDDEN);
        }

        commentRepository.delete(deleteDTO.getCommentId());
    }
}
