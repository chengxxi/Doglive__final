package com.ssafy.db.entity.community;

import com.ssafy.db.entity.auth.UserProfile;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 커뮤니티 댓글 정보 Entity
 */
@Entity
@Table(name="community_comment", schema = "community")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class CommunityComment extends BaseEntity {

    @ManyToOne
    @JoinColumn(name="community_id")
    private Community communityId;                 // 사용자 커뮤니티 번호

    @Column(name="user_id")
    private String userId;                    // 사용자 아이디

    @Column(name="user_name")
    private String name;                    // 사용자 이름

    @Column(columnDefinition = "TEXT")
    private String comment;                   // 커뮤니티 댓글내용

    @Column(name="is_delete")
    private Boolean isDelete;         // 댓글 삭제 유무

    @Column(name="reg_date")
    private LocalDateTime regDate; // 댓글 등록 일자

    public CommunityComment() {
        regDate = LocalDateTime.now();
    }

}

