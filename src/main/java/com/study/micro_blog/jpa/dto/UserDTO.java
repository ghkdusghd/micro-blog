package com.study.micro_blog.jpa.dto;

import com.study.micro_blog.jpa.entity.Gender;
import com.study.micro_blog.jpa.entity.User;
import com.study.micro_blog.jpa.entity.UserProfile;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter @Setter
public class UserDTO {

    private Long userId;
    private String username;
    private String password;
    private String email;

    private String phone;
    private String address;
    private LocalDate birthday;
    private Gender gender;

    // Builder
    public User toEntity() {
        UserProfile userProfile = new UserProfile();
        userProfile.setPhone(this.phone);
        userProfile.setAddress(this.address);
        userProfile.setBirthday(this.birthday);
        userProfile.setGender(gender);
        userProfile.setCreatedAt(LocalDateTime.now());

        User user = new User();
        user.setId(this.userId);
        user.setUsername(this.username);
        user.setPassword(this.password);
        user.setEmail(this.email);
        user.setCreatedAt(LocalDateTime.now());
        user.setUserProfile(userProfile);

        return user;
    }

}
