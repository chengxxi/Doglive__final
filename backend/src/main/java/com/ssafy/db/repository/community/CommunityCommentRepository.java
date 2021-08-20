package com.ssafy.db.repository.community;

import com.ssafy.db.entity.community.Community;
import com.ssafy.db.entity.community.CommunityComment;
import com.ssafy.db.entity.community.CommunityImage;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.List;
import java.util.Optional;

@Repository
public interface CommunityCommentRepository extends JpaRepository<CommunityComment, Long> {

    Optional<List<CommunityComment>> findCommunityCommentsByCommunityId(Community communityId);

    Optional<List<CommunityComment>> findCommunityCommentsByUserId(String userId);


}

