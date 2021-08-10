package com.ssafy.db.repository.board;


import com.ssafy.db.entity.board.Board;
import com.ssafy.db.entity.board.DogInformation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;


@Repository
public interface DogInformationRepository extends JpaRepository<DogInformation, Long> {

    Optional<DogInformation> findDogInformationByBoardId(Board board);

    Optional<List<DogInformation>> findDogInformationsByMbti(String mbti);

}
