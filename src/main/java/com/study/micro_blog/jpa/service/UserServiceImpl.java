package com.study.micro_blog.jpa.service;

import com.study.micro_blog.jpa.dto.PasswordChangeRequestDTO;
import com.study.micro_blog.jpa.dto.UserDTO;
import com.study.micro_blog.jpa.entity.User;
import com.study.micro_blog.common.exception.custom.UserNotFoundException;
import com.study.micro_blog.jpa.repository.JpaUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements JpaUserService {

    private final JpaUserRepository userRepository;

    public UserServiceImpl(JpaUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 사용자 조회
    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User [" + id + "] not found", LocalDateTime.now(), HttpStatus.NOT_FOUND));
    }

    // 사용자 등록
    @Override
    public void save(UserDTO userDTO) {
        User user = userDTO.toEntity();
        userRepository.save(user);
    }

    // 사용자 비밀번호 변경
    @Override
    public void changePassword(PasswordChangeRequestDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User [" + dto.getUserId() + "] not found", LocalDateTime.now(), HttpStatus.NOT_FOUND));

        // 현재 비밀번호가 동일해야 변경 가능하다.
        if (!dto.getCurrentPWD().equals(user.getPassword())) {
            throw new IllegalArgumentException("Current Password is not correct");
        }

        user.setPassword(dto.getNewPWD());
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);
    }

    // 사용자 삭제
    @Override
    public void delete(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User [" + userId + "] not found", LocalDateTime.now(), HttpStatus.NOT_FOUND));;
        userRepository.delete(user);
    }
}
