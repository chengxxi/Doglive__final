package com.ssafy.db.repository.board;

import com.ssafy.db.entity.board.DogType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DogTypeRepository extends JpaRepository<DogType, Long> {

    @Override
    Optional<DogType> findById(Long id);
}
