package com.study.micro_blog.jdbc.controller;

import com.study.micro_blog.jdbc.dto.DeleteDTO;
import com.study.micro_blog.jdbc.dto.PostDTO;
import com.study.micro_blog.jdbc.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jdbc-controller")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // 사용자 (userId) 가 작성한 모든 게시글 조회
    @GetMapping("/posts/{userId}")
    public ResponseEntity<?> getPostById(@PathVariable("userId") Long userId) {
        return ResponseEntity.status(200).body(postService.findByUserId(userId));
    }

    // 게시글 작성
    @PostMapping("/posts")
    public ResponseEntity<?> createPost(@RequestBody PostDTO postDTO) {
        postService.save(postDTO);
        return ResponseEntity.status(200).build();
    }

    // 게시글 수정
    @PutMapping("/posts")
    public ResponseEntity<?> updatePost(@RequestBody PostDTO postDTO) {
        postService.update(postDTO);
        return ResponseEntity.status(200).build();
    }

    // 게시글 삭제
    @DeleteMapping("/posts")
    public ResponseEntity<?> deletePost(@RequestBody DeleteDTO deleteDTO) {
        postService.delete(deleteDTO);
        return ResponseEntity.status(200).build();
    }

}
