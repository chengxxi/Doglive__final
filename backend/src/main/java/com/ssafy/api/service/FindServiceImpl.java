package com.ssafy.api.service;


import com.ssafy.db.entity.board.Board;
import com.ssafy.db.entity.board.BoardCategory;
import com.ssafy.db.entity.board.Code;
import com.ssafy.db.entity.board.DogInformation;
import com.ssafy.db.repository.board.BoardCategoryRepository;
import com.ssafy.db.repository.board.BoardRepository;
import com.ssafy.db.repository.board.BoardRepositorySupport;
import com.ssafy.db.repository.board.DogInformationRepository;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Console;
import java.util.List;
import java.util.Optional;

@Service("findService")
public class FindServiceImpl implements FindService {
    @Autowired
    BoardRepository boardRepository;

    @Autowired
    BoardRepositorySupport boardRepositorySupport;

    @Autowired
    DogInformationRepository dogInformationRepository;

    @Autowired
    BoardCategoryRepository boardCategoryRepository;
    /* 실종보호 게시물 전체 목록 보기 */
    @Override
    public List<Board> getFindBoardList() {
        Optional<List<Board>> boardList = boardRepository.findFindBoard();

        if(boardList.isPresent()) return boardList.get();

        return null;
    }


    @Override
    public List<Board> getBoardSimilarListByBoard(Board board) {
        Optional<BoardCategory> boardCategory = boardCategoryRepository.findById(board.getType().getId() == (long)3 ? (long)4 : (long)3);
        System.out.println("찾을 공고 타입 ==> " + boardCategory.get().getId() + ", " + boardCategory.get().getName());
        Optional<DogInformation> curDogInfo = dogInformationRepository.findDogInformationByBoardId(board);
        Code gender = curDogInfo.get().getGender();
        Code sort = curDogInfo.get().getHairType(); // 변경해아할 부분 -> 품종으로 변경
//        Code sidoCode = (long)0;
        System.out.println("현재 공고 강아지 성별 : " + gender.getName());
        System.out.println("현재 공고 강아지 헤어타입 : " + sort.getName());
//        System.out.println("현재 공고 강아지 주소 : " + curDogInfo.get().getAddress());
        // 실종 -> 보호 공고 / 보호 -> 실종 공고 가져오기 V
        // 성별 / 품종 / 시로 거르기(아예 후보에서 제외) (sql)
        // 순서대로 공고 타입 / 성별 / 품종 / 시도
        Optional<List<Board>> listSimilar = boardRepository.findBoardsByType(boardCategory.get(), gender/*, sort*/);

        // 색 / 나이 / 무게 / 구로 점수 부여

        // 몇 점 이상 -> list에 넣기
        //

        if(listSimilar.isPresent()) {
            return listSimilar.get();
        } else return null;
    }

}
