package com.study.micro_blog.jdbc.controller;

import com.study.micro_blog.jdbc.dto.CommentDTO;
import com.study.micro_blog.jdbc.dto.DeleteDTO;
import com.study.micro_blog.jdbc.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jdbc-controller")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // 댓글 조회
    @GetMapping("/comments/{postId}")
    public ResponseEntity<?> getCommentsById(@PathVariable Long postId) {
        return ResponseEntity.status(200).body(commentService.findByPostId(postId));
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
