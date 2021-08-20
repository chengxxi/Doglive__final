package com.ssafy.db.repository.board;


import com.ssafy.db.entity.board.Board;

import com.ssafy.db.entity.board.DogInformation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;


@Repository
public interface DogInformationRepository extends JpaRepository<DogInformation, Long> {

    Optional<DogInformation> findDogInformationByBoardId(Board board);

    Optional<List<DogInformation>> findDogInformationsByMbti(String mbti);

    //pecification를 이용하여 동적으로 조건을 세팅하여 find
    Page<DogInformation> findAll(Specification<DogInformation> spec, Pageable pageable);

    @Query(
            value = "SELECT * " +
                    "FROM board b " +
                    "LEFT JOIN dog_information d " +
                    "ON b.id = d.board_id " +
                    "WHERE b.board_type = 1 or b.board_type = 2 " +
                    "ORDER BY d.id DESC " +
                    "LIMIT 3 " +
                    "OFFSET 0; ",
            nativeQuery = true
    )
    Optional<List<DogInformation>> findRecentAdoptBoard();


    @Query(
            value = "SELECT * " +
                    "FROM board b " +
                    "LEFT JOIN dog_information d " +
                    "ON b.id = d.board_id " +
                    "WHERE b.board_type = 3 or b.board_type = 4 " +
                    "ORDER BY d.id DESC " +
                    "LIMIT 3 " +
                    "OFFSET 0; ",
            nativeQuery = true
    )
    Optional<List<DogInformation>> findRecentFindBoard();


//    @Query(
//            value = "SELECT * " +
//                    "FROM dog_information d " +
//                    "ORDER BY d.id DESC " +
//                    "WHERE d.mbti = :mbti ",
//            nativeQuery = true
//    )
//    Optional<List<DogInformation>> findSameMBTI(@Param("mbti") String mbti);
//

    Optional<List<DogInformation>> findTop10ByMbtiOrderByIdDesc(String mbti);


}
