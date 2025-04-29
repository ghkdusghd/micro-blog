package com.study.micro_blog.jpa.service;

import com.study.micro_blog.jpa.dto.DeleteDTO;
import com.study.micro_blog.jpa.dto.PostDTO;
import com.study.micro_blog.jpa.entity.Post;

import java.util.List;

public interface JpaPostService {

    Post findByPostId(Long postId);

    List<PostDTO> findByUserId(Long userId);

    void save(PostDTO postDTO);

    void update(PostDTO postDTO);

    void delete(DeleteDTO deleteDTO);

}
