package com.ssafy.db.repository.board;



import com.ssafy.db.entity.board.Board;

import com.ssafy.db.entity.board.BoardCategory;
import com.ssafy.db.entity.board.Code;
import com.ssafy.db.entity.board.DogType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {



    Optional<List<Board>> findBoardsByUserId(String userId);
    @Transactional
    Optional<List<Board>> deleteBoardsByUserId(String userId);

    @Override
    Optional<Board> findById(Long boardId);

    @Query(
            value = "SELECT * FROM board b WHERE b.board_type = 1 or b.board_type = 2",
            nativeQuery = true
    )
    Optional<List<Board>> findAdoptBoard();


    @Query(
            value = "SELECT * FROM board b WHERE b.board_type = 3 or b.board_type = 4",
            nativeQuery = true
    )
    Optional<List<Board>> findFindBoard();

    // AND d.gugun LIKE ':두자리수%',
    // AND d.sort = :sort
    @Query(
            value = "SELECT b.id, b.reg_date, b.thumbnail_url, b.title, b.user_id, b.board_type FROM board b LEFT JOIN dog_information d "
                    +"ON b.id = d.board_id "
                    +"WHERE b.board_type = :boardCategory "     // 공고 타입
                    +"AND b.user_id != :userId "                // 글 작성자와 유사공고 작성자가 같지 않게
                    +"AND d.gender = :gender "                  // 강아지 성별
                    +"AND d.dog_type = :sort "
                    +"AND d.gugun LIKE :sidoCode% ",
            nativeQuery = true
    )
    Optional<List<Board>> findBoardsByType(@Param("boardCategory") BoardCategory boardCategory,
                                           @Param("userId") String userId,
                                           @Param("gender") Code gender,
                                           @Param("sort") DogType sort,
                                           @Param("sidoCode") Long sidoCode);


}
