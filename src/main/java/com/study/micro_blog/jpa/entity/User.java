package com.study.micro_blog.jpa.entity;

import com.study.micro_blog.jpa.dto.UserDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@Getter @Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_profile_id")
    private UserProfile userProfile;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments;

    public UserDTO toDtoWithProfile() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(this.username);
        userDTO.setPhone(this.userProfile.getPhone());
        userDTO.setAddress(this.userProfile.getAddress());
        userDTO.setBirthday(this.userProfile.getBirthday());
        userDTO.setGender(this.userProfile.getGender());
        return userDTO;
    }

}
