package com.ssafy.api.service;

import com.ssafy.api.request.BoardRegisterPostReq;
import com.ssafy.api.request.BookmarkReq;
import com.ssafy.db.entity.auth.Bookmark;
import com.ssafy.db.entity.board.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BoardService {

    /* 입양임보 게시판 목록 가져오기 (필터링, 페이지네이션 추가) */
    Page<DogInformation> filterBoardList(Pageable pageable, Long boardType, Long weight, Long age, Long gender, String searchWord); //필터링 결과 리스트 불러오기

    /* 실종보호 게시판 목록 가져오기 (필터링, 페이지네이션 추가) */
    Page<DogInformation> filterFindBoardList(Pageable pageable, Long boardType,  Long sido, Long color, Long dogType, String searchWord); //필터링 결과 리스트 불러오기

    /* 입양/임보 최신게시물 4개 가져오기 */
    List<DogInformation> getRecentAdoptBoard();

    /* 실종/보호 최신게시물 4개 가져오기 */
    List<DogInformation> getRecentFindBoard();

    /* 유기동물 관련 게시물 작성하기 */
    Board registerBoard(BoardRegisterPostReq boardRegisterPostReq) throws IOException;


    /* 유기동물 관련 게시물 삭제하기 */
    boolean deleteBoard(Long boardId);

    /* 유기동물 관련 게시물 수정하기 */

    Board updateBoard(Long boardId, BoardRegisterPostReq boardRegisterPostReq) throws IOException;


    /* BoardId로 Board 찾기 */
    Board getBoardByBoardId(Long boardId);

    /* Board로 BoardComment 찾기 */
    List<BoardComment> getBoardCommentsByBoard(Board board);

    /* Board로 BoardImage 찾기 */
    List<BoardImage> getBoardImagesByBoard(Board board);

    /* userId로 Board 목록 조회 */
    List<Board> getBoardListByUserId(String id);

    /* Board로 DogInformtaion 찾기 */
    DogInformation getDogInformationByBoard(Board board);

    /* Board로 BoardImage 전부 지우기 */
    void deleteAllBoardImagesByBoard(Board board);

    void deleteSomeBoardImagesByUrl(List<String> delList);

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

    List<Gugun> getGugunListBySido(Long sido);

    List<Sido> getSidoList();

    List<DogType> getDogTypeList();

}
