package com.ssafy.db.repository.board;



import com.ssafy.db.entity.board.BoardListData;
import com.ssafy.db.entity.board.Board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
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


//    @Query(
//            value = "SELECT b.id, b.user_id, b.board_type, b.title, b.thumbnail_url, b.reg_date, d.gender, d.dog_name, d.mbti, d.age_type, d.color_type, d.dog_type, d.weight "
//                    + "FROM board b LEFT JOIN dog_information d "
//                    + "ON b.id = d.board_id "
//                    + "WHERE b.board_type = :type1 OR b.board_type = :type2 ",
//                    + "ORDER BY b.reg_date DESC "
//
//            nativeQuery = true
//    )
//    Page<Object[]> findBoardList(Pageable pageable, int type1, int type2);

    @Query(name = "BoardAndDogwithPaging", nativeQuery = true)
    List<BoardListData> findBoardListwithPaging(
            @Param("type1") int type1, @Param("type2") int type2,
            @Param("limit") int limit, @Param("offset") int offset
    );

//    @Query(name="BoardAndDogwithFilterPaging", nativeQuery = true)
//    List<BoardListData> findFilteringListwithPaging(
//            @Param("boardType") Long boardType,
//            @Param("limit") int limit, @Param("offset") int offset,
//            @Param("searchWord") String searchWord,
//            @Param("age") Long age,
//            @Param("gender") Long gender,
//            @Param("weight") Long weight
//    );

    @Query(
            value = "SELECT count(*) "
                    + "FROM board b LEFT JOIN dog_information d "
                    + "ON b.id = d.board_id "
                    + "WHERE b.board_type = :type1 OR b.board_type = :type2 ",
            nativeQuery = true
    )
    int findBoardListCnt(
            @Param("type1") int type1, @Param("type2") int type2
    );

}
