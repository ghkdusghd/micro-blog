package com.study.micro_blog.jpa.repository;

import com.study.micro_blog.jpa.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaCommentRepository extends JpaRepository<Comment, Long> {
}
