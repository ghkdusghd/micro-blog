package com.study.micro_blog.jpa.service;

import com.study.micro_blog.jpa.dto.CommentDTO;
import com.study.micro_blog.jpa.dto.DeleteDTO;

public interface JpaCommentService {

    void save(CommentDTO commentDTO);

    void update(CommentDTO commentDTO);

    void delete(DeleteDTO deleteDTO);

}
