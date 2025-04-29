package com.study.micro_blog.jdbc.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class UserDTO {

    private Long userId;
    private String username;
    private String password;
    private String email;

    private String phone;
    private String address;
    private LocalDate birthday;
    private String gender;

}
