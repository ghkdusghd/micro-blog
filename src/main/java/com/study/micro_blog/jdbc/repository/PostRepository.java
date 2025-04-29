package com.study.micro_blog.jdbc.repository;

import com.study.micro_blog.jdbc.entity.Post;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class PostRepository {

    private final JdbcTemplate jdbcTemplate;

    public PostRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 게시글 id 로 하나만 조회
    public Post findById(Long postId) {
        String sql = "select * from posts where id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{postId}, (rs, rowNum) -> {
            Post post = new Post();
            post.setId(rs.getLong("id"));
            post.setUserId(rs.getLong("users_id"));
            post.setTitle(rs.getString("title"));
            post.setBody(rs.getString("body"));
            post.setStatus(rs.getString("status"));
            return post;
        });
    }

    // 사용자 id 로 여러 개 조회
    public List<Post> findByUserId(Long userId) {
        String sql = "SELECT * FROM posts WHERE users_id = ?";
        return jdbcTemplate.query(sql, new Object[]{userId}, (rs, rowNum) -> {
            Post post = new Post();
            post.setId(rs.getLong("id"));
            post.setUserId(rs.getLong("users_id"));
            post.setTitle(rs.getString("title"));
            post.setBody(rs.getString("body"));
            post.setStatus(rs.getString("status"));
            return post;
        });
    }

    public void save(Post post) {
        String sql = "insert into posts(id, users_id, body, title, status, created_at) values(?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                post.getId(),
                post.getUserId(),
                post.getBody(),
                post.getTitle(),
                post.getStatus(),
                LocalDateTime.now()
                );
    }

    public void update(Post post) {
        String sql = "update posts set title = ?, body = ?, status = ?, updated_at = ? where id = ?";
        jdbcTemplate.update(sql,
                post.getTitle(),
                post.getBody(),
                post.getStatus(),
                LocalDateTime.now(),
                post.getId()
                );
    }

    public void delete(Long postId) {
        String sql = "delete from posts where id = ?";
        jdbcTemplate.update(sql, postId);
    }

}
