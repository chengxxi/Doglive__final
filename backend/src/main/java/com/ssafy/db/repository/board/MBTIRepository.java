package com.ssafy.db.repository.board;

import com.ssafy.db.entity.board.MBTI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MBTIRepository extends JpaRepository<MBTI, Long> {

    @Override
    Optional<MBTI> findById(Long id);

    Optional<MBTI> findByName (String mbti);

}
