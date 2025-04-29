package com.study.micro_blog.jpa.service;

import com.study.micro_blog.jpa.dto.DeleteDTO;
import com.study.micro_blog.jpa.dto.PostDTO;
import com.study.micro_blog.jpa.entity.Post;
import com.study.micro_blog.jpa.entity.User;
import com.study.micro_blog.common.exception.custom.CustomAccessDeniedException;
import com.study.micro_blog.common.exception.custom.PostNotFoundException;
import com.study.micro_blog.jpa.repository.JpaPostRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostServiceImpl implements JpaPostService {

    private final JpaPostRepository postRepository;
    private final JpaUserService userService;

    public PostServiceImpl(JpaPostRepository postRepository, JpaUserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }

    // 게시글 ID 로 특정 게시글 하나만 조회하기 (엔티티 반환)
    @Override
    public Post findByPostId(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Post [" + postId + "] not found", LocalDateTime.now(), HttpStatus.NOT_FOUND));
    }

    // 사용자 ID 로 특정 사용자가 작성한 게시글 모두 조회하기 (DTO 반환)
    @Override
    public List<PostDTO> findByUserId(Long userId) {
        List<Post> postList = postRepository.findByUserId(userId);
        if (postList.isEmpty()) {
            throw new PostNotFoundException("Post not found", LocalDateTime.now(), HttpStatus.NOT_FOUND);
        }
        return postList.stream()
                .map(Post::toDtoWithComment)
                .toList();
    }

    // 게시글 작성
    @Override
    public void save(PostDTO postDTO) {
        User user = userService.findById(postDTO.getUserId());
        Post post = postDTO.toEntity();
        post.setCreatedAt(LocalDateTime.now());
        post.setUser(user);
        postRepository.save(post);
    }

    // 게시글 수정
    @Override
    public void update(PostDTO postDTO) {
        Post post = postRepository.findById(postDTO.getPostId())
                .orElseThrow(() -> new PostNotFoundException("Post [" + postDTO.getPostId() + "] not found", LocalDateTime.now(), HttpStatus.NOT_FOUND));

        // 작성자만 수정 가능
        if (post.getUser().getId() != postDTO.getUserId()) {
            throw new CustomAccessDeniedException("User [" + postDTO.getUserId() + "] do not have permission", LocalDateTime.now(), HttpStatus.FORBIDDEN);
        }

        post.setTitle(postDTO.getTitle());
        post.setBody(postDTO.getBody());
        post.setStatus(postDTO.getStatus());
        post.setUpdatedAt(LocalDateTime.now());
        postRepository.save(post);
    }

    // 게시글 삭제
    @Override
    public void delete(DeleteDTO deleteDTO) {
        Post post = postRepository.findById(deleteDTO.getPostId())
                .orElseThrow(() -> new PostNotFoundException("Post [" + deleteDTO.getPostId() + "] not found", LocalDateTime.now(), HttpStatus.NOT_FOUND));

        // 작성자만 삭제 가능
        if (post.getUser().getId() != deleteDTO.getUserId()) {
            throw new CustomAccessDeniedException("User [" + deleteDTO.getUserId() + "] do not have permission", LocalDateTime.now(), HttpStatus.FORBIDDEN);
        }

        postRepository.delete(post);
    }
}
