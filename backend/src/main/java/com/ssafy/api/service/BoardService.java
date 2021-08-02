package com.ssafy.api.service;

import com.ssafy.api.request.AdoptFormReq;
import com.ssafy.api.request.BoardRegisterPostReq;
import com.ssafy.api.request.BookmarkReq;
import com.ssafy.db.entity.auth.Bookmark;
import com.ssafy.db.entity.auth.UserProfile;
import com.ssafy.db.entity.board.*;

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

    Code getCode(Long id);

    Bookmark insertBookmark(BookmarkReq bookmarkReq);

    Bookmark deleteBookmark(BookmarkReq bookmarkReq);

    Bookmark getBookmark(BookmarkReq bookmarkReq);

}
