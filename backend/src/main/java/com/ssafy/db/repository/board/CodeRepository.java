package com.ssafy.db.repository.board;

import com.ssafy.db.entity.board.Code;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CodeRepository extends JpaRepository<Code, Long> {

    @Override
    Optional<Code> findById(Long id);
}
