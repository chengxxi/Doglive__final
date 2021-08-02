package com.ssafy.api.service;

import com.ssafy.api.request.AdoptFormReq;
import com.ssafy.api.request.BoardRegisterPostReq;
import com.ssafy.api.request.BookmarkReq;
import com.ssafy.db.entity.auth.Bookmark;
import com.ssafy.db.entity.auth.UserProfile;
import com.ssafy.db.entity.board.*;

import java.util.List;

public interface BoardService {

    /* 유기동물 관련 게시물 작성하기 */
    Board registerBoard(BoardRegisterPostReq boardRegisterPostReq);

    /* 유기동물 관련 게시물 삭제하기 */
    boolean deleteBoard(Long boardId);

    /* 유기동물 관련 게시물 수정하기 */
    Board updateBoard(Long boardId, BoardRegisterPostReq boardRegisterPostReq);

    /* BoardId로 Board 찾기 */
    Board getBoardByBoardId(Long boardId);

    /* Board로 BoardComment 찾기 */
    List<BoardComment> getBoardCommentsByBoard(Board board);

    /* Board로 BoardImage 찾기 */
    List<BoardImage> getBoardImagesByBoard(Board board);

    /* Board로 DogInformtaion 찾기 */
    DogInformation getDogInformationByBoard(Board board);

    /* Board로 BoardImage 전부 지우기 */
    void deleteAllBoardImagesByBoard(Board board);

    /* Board로 BoardComment 전부 지우기 */
    void deleteAllBoardCommentsByBoard(Board board);

    /* 공통코드 찾기 */
    Code getCode(Long id);

    /* 북마크 생성하기 */
    Bookmark insertBookmark(BookmarkReq bookmarkReq);

    /* 북마크 지우기 */
    Bookmark deleteBookmark(BookmarkReq bookmarkReq);

    /* 북마크 존재하면 찾기 */
    Bookmark getBookmark(BookmarkReq bookmarkReq);

}
