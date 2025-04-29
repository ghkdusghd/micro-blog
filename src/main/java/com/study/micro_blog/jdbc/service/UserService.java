package com.study.micro_blog.jdbc.service;

import com.study.micro_blog.common.exception.custom.UserNotFoundException;
import com.study.micro_blog.jdbc.dto.PasswordChangeRequestDTO;
import com.study.micro_blog.jdbc.dto.UserDTO;
import com.study.micro_blog.jdbc.entity.User;
import com.study.micro_blog.jdbc.entity.UserProfile;
import com.study.micro_blog.jdbc.repository.UserProfileRepository;
import com.study.micro_blog.jdbc.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDateTime;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserProfileRepository userProfileRepository;

    public UserService(UserRepository userRepository, UserProfileRepository userProfileRepository) {
        this.userRepository = userRepository;
        this.userProfileRepository = userProfileRepository;
    }

    // 사용자 조회
    public User findById(Long id) {
        try {
            return userRepository.findById(id);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            throw new UserNotFoundException("User [" + id + "] not found", LocalDateTime.now(), HttpStatus.NOT_FOUND);
        }
    }

    // 사용자 등록 (users 와 user_profile 을 함께 저장하고 둘 중 하나라도 실패하면 롤백)
    @Transactional
    public void save(UserDTO userDTO) {
        // user_profile 먼저 저장
        UserProfile userProfile = new UserProfile();
        userProfile.setId(userDTO.getUserId());
        userProfile.setPhone(userDTO.getPhone());
        userProfile.setAddress(userDTO.getAddress());
        userProfile.setBirthday(userDTO.getBirthday());
        userProfile.setGender(userDTO.getGender());
        userProfileRepository.save(userProfile);

        // 그 후 user 저장
        User user = new User();
        user.setId(userDTO.getUserId());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        userRepository.save(user);
    }

    // 사용자 비밀번호 변경
    public void changePassword(PasswordChangeRequestDTO dto) {
        User user = findById(dto.getUserId());

        // 현재 비밀번호가 동일해야 변경 가능하다.
        if (!dto.getCurrentPWD().equals(user.getPassword())) {
            throw new IllegalArgumentException("Current Password is not correct");
        }

        user.setPassword(dto.getNewPWD());
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.update(user);
    }

    // 사용자 삭제
    public void delete(Long userId) {
        userRepository.delete(userId);
    }

}
