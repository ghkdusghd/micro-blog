package com.study.micro_blog.jpa.repository;

import com.study.micro_blog.jpa.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaPostRepository extends JpaRepository<Post, Long> {

    List<Post> findByUserId(Long userId);

}
