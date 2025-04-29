package com.study.micro_blog.jpa.controller;

import com.study.micro_blog.jpa.dto.PasswordChangeRequestDTO;
import com.study.micro_blog.jpa.dto.UserDTO;
import com.study.micro_blog.jpa.entity.User;
import com.study.micro_blog.jpa.service.JpaUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jpa-controller")
public class JpaUserController {

    private final JpaUserService userService;

    public JpaUserController(JpaUserService userService) {
        this.userService = userService;
    }

    // 사용자 조회
    @GetMapping("/users/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable("userId") Long userId) {
        User user = userService.findById(userId);
        UserDTO userDTO = user.toDtoWithProfile();
        return ResponseEntity.status(200).body(userDTO);
    }

    // 사용자 등록
    @PostMapping("/users")
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) {
        userService.save(userDTO);
        return ResponseEntity.status(201).build();
    }

    // 사용자 비밀번호 변경
    @PutMapping("/users-password")
    public ResponseEntity<?> updateUserPassword(@RequestBody PasswordChangeRequestDTO dto) {
        userService.changePassword(dto);
        return ResponseEntity.status(200).build();
    }

    // 사용자 삭제
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") Long userId) {
        userService.delete(userId);
        return ResponseEntity.status(200).build();
    }

}
