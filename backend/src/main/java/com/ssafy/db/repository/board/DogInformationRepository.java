package com.ssafy.db.repository.board;


import com.ssafy.db.entity.board.Board;
import com.ssafy.db.entity.board.DogInformation;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Optional;


@Repository
public interface DogInformationRepository extends JpaRepository<DogInformation, Long> {

    Optional<DogInformation> findDogInformationByBoardId(Board board);

}
