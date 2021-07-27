package com.ssafy.db.entity.community;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

/**
 * 커뮤니티 게시글 정보 Entity
 */
@Entity
@Getter
@Setter
public class Community extends BaseEntity {

    private String userId;                    // 사용자 아이디
    private String title;                     // 게시글 제목
    private String description;               // 게시글 내용
    private java.sql.Timestamp registerDate;  // 게시글 작성 시간
    private long likeCnt;                     // 게시글 좋아요 수

}

