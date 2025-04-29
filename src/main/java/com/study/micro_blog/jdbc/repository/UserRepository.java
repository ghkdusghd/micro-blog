package com.study.micro_blog.jdbc.repository;

import com.study.micro_blog.jdbc.entity.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User findById(Long id) {
        String sql = "SELECT u.id, username, password, email, address, phone, gender " +
                "FROM users u " +
                "JOIN user_profile p ON u.id = p.id " +
                "WHERE p.id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            return user;
        });
    }

    public void save(User user) {
        String sql = "insert into users(id, username, email, password, created_at) values(?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                LocalDateTime.now()
                );
    }

    public void update(User user) {
        String sql = "update users set password = ?, updated_at = ? where id = ?";
        jdbcTemplate.update(sql,
                user.getPassword(),
                user.getUpdatedAt(),
                user.getId());
    }

    public void delete(Long userId) {
        String sql = "delete from users where id = ?";
        jdbcTemplate.update(sql, userId);
    }

}
