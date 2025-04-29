package com.study.micro_blog.jpa.service;

import com.study.micro_blog.jpa.dto.PasswordChangeRequestDTO;
import com.study.micro_blog.jpa.dto.UserDTO;
import com.study.micro_blog.jpa.entity.User;

public interface JpaUserService {

    User findById(Long id);

    void save(UserDTO userDTO);

    void changePassword(PasswordChangeRequestDTO dto);

    void delete(Long id);

}
