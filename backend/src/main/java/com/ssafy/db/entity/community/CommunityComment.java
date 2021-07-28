package com.ssafy.db.entity.community;

import com.ssafy.db.entity.auth.UserProfile;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 커뮤니티 댓글 정보 Entity
 */
@Entity
@Table(name="community_comment", schema = "community")
@Getter
@Setter
public class CommunityComment extends BaseEntity {

    @ManyToOne
    @JoinColumn(name="community_id")
    private Community communityId;                 // 사용자 커뮤니티 번호

    @Column(name="user_id")
    private String userId;                    // 사용자 아이디

    @Column(columnDefinition = "TEXT")
    private String comment;                   // 커뮤니티 댓글내용

    @Column(name="reg_date")
    @Temporal(TemporalType.TIME)
    private java.util.Date regDate;  // 커뮤니티 댓글 작성 시간


}

