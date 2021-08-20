package com.ssafy.db.repository.community;

import com.ssafy.api.request.CommunityParamDto;
import com.ssafy.api.request.CommunityParamDtoInterface;
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

    Optional<Community> findCommunityByIdOrderByIdDesc(Long id);

    @Query(
            value="select `b`.id, `b`.description, `b`.title, `b`.user_id, `b`.reg_date, `u`.name, `u`.profile_image_url, `b`.category " +
                    "from `community`.`community` b inner join `auth`.`user_profile` u where `b`.user_id = `u`.user_id " +
                    "order by `b`.id desc",
            nativeQuery = true)
    Optional<Page<CommunityParamDtoInterface>> findAllDesc(Pageable paging);

}


