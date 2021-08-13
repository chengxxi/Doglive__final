package com.ssafy.db.repository.community;

import com.ssafy.api.request.CommunityParamDto;
import com.ssafy.db.entity.community.Community;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Optional;

@Repository
public interface CommunityRepository extends JpaRepository<Community, Long> {

    Optional<List<Community>> findCommunitiesByUserId(String id);

    Optional<Community> findCommunityById(Long id);

    @Query(name = "CommunityAndUser", nativeQuery = true)
    Optional<Page<CommunityParamDto>> findAllDesc(Pageable paging);

}

