package com.ssafy.db.entity.community;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * 커뮤니티 댓글 정보 Entity
 */
@Entity
@Getter
@Setter
public class CommunityComment extends BaseEntity {

    @ManyToOne
    @JoinColumn(name="community_id")
    private Community communityId;                 // 사용자 커뮤니티 번호

    @Column(length=13)
    private String userId;                    // 사용자 아이디

    @Column(columnDefinition = "TEXT")
    private String comment;                   // 커뮤니티 댓글내용

    private java.sql.Timestamp registerDate;  // 커뮤니티 댓글 작성 시간


}

