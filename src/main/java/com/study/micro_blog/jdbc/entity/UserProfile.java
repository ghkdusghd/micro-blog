package com.study.micro_blog.jdbc.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter @Setter
public class UserProfile {

    private Long id;
    private String phone;
    private String address;
    private LocalDate birthday;
    private String gender;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
