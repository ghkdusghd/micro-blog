package com.study.micro_blog.jdbc.controller;

import com.study.micro_blog.jdbc.dto.PasswordChangeRequestDTO;
import com.study.micro_blog.jdbc.dto.UserDTO;
import com.study.micro_blog.jdbc.entity.User;
import com.study.micro_blog.jdbc.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jdbc-controller")
public class JdbcUserController {

    private final UserService userService;

    public JdbcUserController(UserService userService) {
        this.userService = userService;
    }

    // 사용자 조회
    @GetMapping("/users/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable("userId") Long userId) {
        User user = userService.findById(userId);
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
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
