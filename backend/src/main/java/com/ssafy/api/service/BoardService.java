package com.ssafy.api.service;

import com.ssafy.api.request.BoardRegisterPostReq;
import com.ssafy.db.entity.board.Board;
import com.ssafy.db.entity.board.BoardComment;
import com.ssafy.db.entity.board.BoardImage;
import com.ssafy.db.entity.board.DogInformation;

import java.util.List;

public interface BoardService {
    Board registerBoard(BoardRegisterPostReq boardRegisterPostReq);

    boolean deleteBoard(Long boardId);


    Board updateBoard(Long boardId, BoardRegisterPostReq boardRegisterPostReq);

    Board getBoardByBoardId(Long boardId);

    List<BoardComment> getBoardCommentsByBoard(Board board);

    List<BoardImage> getBoardImagesByBoard(Board board);

    DogInformation getDogInformationByBoard(Board board);

    void deleteAllBoardImagesByBoard(Board board);

    void deleteAllBoardCommentsByBoard(Board board);


}
