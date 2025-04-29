package com.study.micro_blog.jdbc.service;

import com.study.micro_blog.common.exception.custom.CustomAccessDeniedException;
import com.study.micro_blog.common.exception.custom.PostNotFoundException;
import com.study.micro_blog.common.exception.custom.UserNotFoundException;
import com.study.micro_blog.jdbc.dto.DeleteDTO;
import com.study.micro_blog.jdbc.dto.PostDTO;
import com.study.micro_blog.jdbc.entity.Comment;
import com.study.micro_blog.jdbc.entity.Post;
import com.study.micro_blog.jdbc.repository.PostRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // 게시글 ID 로 특정 게시글 하나만 조회하기 (엔티티 반환)
    public Post findByPostId(Long postId) {
        try {
            return postRepository.findById(postId);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            throw new UserNotFoundException("Post [" + postId + "] not found", LocalDateTime.now(), HttpStatus.NOT_FOUND);
        }
    }

    // 사용자 ID 로 특정 사용자가 작성한 게시글 모두 조회하기 (DTO 반환)
    public List<PostDTO> findByUserId(Long userId) {
        List<Post> postList = postRepository.findByUserId(userId);
        if (postList.isEmpty()) {
            throw new PostNotFoundException("Post not found", LocalDateTime.now(), HttpStatus.NOT_FOUND);
        }

        return postList.stream()
                .map(Post::toDto)
                .toList();
    }

    // 게시글 작성
    public void save(PostDTO postDTO) {
        Post post = new Post();
        post.setId(postDTO.getPostId());
        post.setUserId(postDTO.getUserId());
        post.setTitle(postDTO.getTitle());
        post.setBody(postDTO.getBody());
        post.setStatus(postDTO.getStatus());
        postRepository.save(post);
    }

    // 게시글 수정
    public void update(PostDTO postDTO) {
        Post post = findByPostId(postDTO.getPostId());

        // 작성자만 수정 가능
        if (post.getUserId() != postDTO.getUserId()) {
            throw new CustomAccessDeniedException("User [" + postDTO.getUserId() + "] do not have permission", LocalDateTime.now(), HttpStatus.FORBIDDEN);
        }

        post.setTitle(postDTO.getTitle());
        post.setBody(postDTO.getBody());
        post.setStatus(postDTO.getStatus());
        post.setUpdatedAt(LocalDateTime.now());
        postRepository.update(post);
    }

    // 게시글 삭제
    public void delete(DeleteDTO deleteDTO) {
        Post post = findByPostId(deleteDTO.getPostId());

        // 작성자만 삭제 가능
        if (post.getUserId() != deleteDTO.getUserId()) {
            throw new CustomAccessDeniedException("User [" + deleteDTO.getUserId() + "] do not have permission", LocalDateTime.now(), HttpStatus.FORBIDDEN);
        }

        postRepository.delete(deleteDTO.getPostId());
    }
}
