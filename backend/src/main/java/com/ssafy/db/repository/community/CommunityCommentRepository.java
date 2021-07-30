package com.ssafy.db.repository.community;

import com.ssafy.db.entity.community.CommunityComment;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Repository
public interface CommunityCommentRepository extends JpaRepository<CommunityComment, Long> {

}

