package com.ssafy.api.service;

import com.ssafy.api.request.BoardRegisterPostReq;
import com.ssafy.db.entity.board.Board;
import com.ssafy.db.entity.board.BoardComment;
import com.ssafy.db.entity.board.BoardImage;
import com.ssafy.db.entity.board.DogInformation;

import java.util.List;

public interface AdoptService {
    Board registerAdoptBoard(BoardRegisterPostReq boardRegisterPostReq);

    Board getAdoptBoard();

    boolean deleteAdoptBoard(Long boardId);

    List<Board> getAdoptBoardList();

    Board updateAdoptBoard();

    Board getBoardByBoardId(Long boardId);

    BoardComment getBoardCommentByBoard(Board board);

    BoardImage getBoardImageByBoard(Board board);

    DogInformation getDogInformationByBoard(Board board);

}
