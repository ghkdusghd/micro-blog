package com.study.micro_blog.jdbc.repository;

import com.study.micro_blog.jdbc.entity.Comment;
import com.study.micro_blog.jdbc.entity.Post;
import com.study.micro_blog.jdbc.entity.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class CommentRepository {

    private final JdbcTemplate jdbcTemplate;

    public CommentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 댓글 Id 로 하나만 조회
    public Comment findById(Long commentId) {
        String sql = "select * from comments where id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{commentId}, (rs, rowNum) -> {
            Comment comment = new Comment();
            comment.setId(rs.getLong("id"));
            comment.setPostId(rs.getLong("posts_id"));
            comment.setUserId(rs.getLong("users_id"));
            comment.setMsg(rs.getString("msg"));
            return comment;
        });
    }

    // 게시글 id 로 여러 개 조회
    public List<Comment> findByPostId(Long postId) {
        String sql = "SELECT * FROM comments WHERE posts_id = ?";
        return jdbcTemplate.query(sql, new Object[]{postId}, (rs, rowNum) -> {
            Comment comment = new Comment();
            comment.setId(rs.getLong("id"));
            comment.setUserId(rs.getLong("users_id"));
            comment.setPostId(rs.getLong("posts_id"));
            comment.setMsg(rs.getString("msg"));
            return comment;
        });
    }

    // 사용자 id 로 여러 개 조회
    public List<Comment> findByUserId(Long userId) {
        String sql = "SELECT * FROM comments WHERE users_id = ?";
        return jdbcTemplate.query(sql, new Object[]{userId}, (rs, rowNum) -> {
            Comment comment = new Comment();
            comment.setId(rs.getLong("id"));
            comment.setUserId(rs.getLong("users_id"));
            comment.setPostId(rs.getLong("posts_id"));
            comment.setMsg(rs.getString("msg"));
            return comment;
        });
    }

    public void save(Comment comment) {
        String sql = "insert into comments(id, posts_id, users_id, msg, created_at) values(?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                comment.getId(),
                comment.getPostId(),
                comment.getUserId(),
                comment.getMsg(),
                LocalDateTime.now()
        );
    }

    public void update(Comment comment) {
        String sql = "update comments set msg = ?, updated_at = ? where id = ?";
        jdbcTemplate.update(sql,
                comment.getMsg(),
                LocalDateTime.now(),
                comment.getId());
    }

    public void delete(Long commentId) {
        String sql = "delete from comments where id = ?";
        jdbcTemplate.update(sql, commentId);
    }

}
