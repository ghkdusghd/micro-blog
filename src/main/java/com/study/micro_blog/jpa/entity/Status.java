package com.study.micro_blog.jpa.entity;

public enum Status {
    DRAFT,      // 임시 저장
    PUBLISHED,  // 게시된 상태
    ARCHIVED    // 보관된 상태 (작성자만 볼 수 있다)
}
