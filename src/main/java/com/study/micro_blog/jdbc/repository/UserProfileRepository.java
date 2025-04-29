package com.study.micro_blog.jdbc.repository;

import com.study.micro_blog.jdbc.entity.UserProfile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class UserProfileRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserProfileRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(UserProfile userProfile) {
        String sql = "insert into user_profile(id, address, phone, gender, birthday, created_at) values(?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                userProfile.getId(),
                userProfile.getAddress(),
                userProfile.getPhone(),
                userProfile.getGender(),
                userProfile.getBirthday(),
                LocalDateTime.now()
                );
    }

}
