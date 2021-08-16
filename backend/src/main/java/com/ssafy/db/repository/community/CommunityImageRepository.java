package com.ssafy.db.repository.community;

import com.ssafy.db.entity.community.Community;
import com.ssafy.db.entity.community.CommunityImage;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.List;
import java.util.Optional;

@Repository
public interface CommunityImageRepository extends JpaRepository<CommunityImage, Long> {

    Optional<List<CommunityImage>> findCommunityImagesByCommunityId(Community communityId);

    @Transactional
    void deleteCommunityImageByCommunityId(Community community);

    @Transactional
    Optional<List<CommunityImage>> findCommunityImagesByFilePath(String url);

}

