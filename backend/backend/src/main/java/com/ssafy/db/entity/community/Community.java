package com.ssafy.db.entity.community;

import com.ssafy.db.entity.auth.UserProfile;
import com.ssafy.db.entity.community.CommunityComment;
import com.ssafy.db.entity.community.CommunityImage;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 커뮤니티 게시글 정보 Entity
 */
@Entity
@Table(name="community", schema = "community")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Community extends BaseEntity {

    @Column(name="user_id")
    private String userId;                    // 사용자 아이디

    @Column(length = 30)
    private String title;                     // 게시글 제목

    @Column(columnDefinition = "TEXT")
    private String description;               // 게시글 내용

    @Column(length = 10)
    private String category;                  // 게시글 종류

    @Column(name="reg_date", columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP")
    private LocalDateTime regDate;

//    @OneToMany(mappedBy = "communityId", cascade = {CascadeType.ALL}, orphanRemoval=true)
//    private List<CommunityComment> communityComments;
//
//    @OneToMany(mappedBy = "communityId", cascade = {CascadeType.ALL}, orphanRemoval=true)
//    private List<CommunityImage> communityImages;

}

