package com.study.micro_blog.jpa.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PasswordChangeRequestDTO {
    private Long userId;
    private String currentPWD;
    private String newPWD;
}
