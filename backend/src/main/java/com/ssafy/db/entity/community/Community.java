package com.ssafy.db.entity.community;

import com.ssafy.db.entity.board.BoardComment;
import com.ssafy.db.entity.board.BoardImage;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * 커뮤니티 게시글 정보 Entity
 */
@Entity
@Table(name="community", schema = "community")
@Getter
@Setter
public class Community extends BaseEntity {

    @Column(length = 13)
    private String userId;                    // 사용자 아이디

    @Column(length = 30)
    private String title;                     // 게시글 제목

    @Column(columnDefinition = "TEXT")
    private String description;               // 게시글 내용
    private java.sql.Timestamp registerDate;  // 게시글 작성 시간

    @OneToMany(mappedBy = "communityId", cascade = {CascadeType.ALL}, orphanRemoval=true)
    private List<CommunityComment> communityComments;

    @OneToMany(mappedBy = "communityId", cascade = {CascadeType.ALL}, orphanRemoval=true)
    private List<CommunityImage> communityImages;

}

