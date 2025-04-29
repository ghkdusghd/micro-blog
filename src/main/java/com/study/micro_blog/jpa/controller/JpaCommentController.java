package com.study.micro_blog.jpa.controller;

import com.study.micro_blog.jpa.dto.CommentDTO;
import com.study.micro_blog.jpa.dto.DeleteDTO;
import com.study.micro_blog.jpa.service.JpaCommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jpa-controller")
public class JpaCommentController {

    private final JpaCommentService commentService;

    public JpaCommentController(JpaCommentService commentService) {
        this.commentService = commentService;
    }

    // 댓글 작성
    @PostMapping("/comments")
    public ResponseEntity<?> createComment(@RequestBody CommentDTO commentDTO) {
        commentService.save(commentDTO);
        return ResponseEntity.status(201).build();
    }

    // 댓글 수정
    @PutMapping("/comments")
    public ResponseEntity<?> updateComment(@RequestBody CommentDTO commentDTO) {
        commentService.update(commentDTO);
        return ResponseEntity.status(200).build();
    }

    // 댓글 삭제
    @DeleteMapping("/comments")
    public ResponseEntity<?> deleteComment(@RequestBody DeleteDTO deleteDTO) {
        commentService.delete(deleteDTO);
        return ResponseEntity.status(200).build();
    }

}
