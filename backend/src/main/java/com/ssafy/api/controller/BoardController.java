package com.ssafy.api.controller;



import com.ssafy.api.request.BoardParamDto;

import com.ssafy.api.request.BoardRegisterPostReq;
import com.ssafy.api.request.BookmarkReq;
import com.ssafy.api.response.*;
import com.ssafy.api.service.AdoptService;
import com.ssafy.api.service.BoardService;
import com.ssafy.api.service.FindService;
import com.ssafy.api.service.UserService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.auth.Bookmark;
import com.ssafy.db.entity.board.*;

import com.ssafy.db.repository.board.BoardRepository;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 유기동물 게시판 API 요청 처리를 위한 컨트롤러 정의.
 */
@CrossOrigin(origins={"*"}, maxAge=6000)
@Api(value = "유기동물 정보 게시판 API", tags = {"Board"})
@RestController
@RequestMapping("/api/v1/board")
public class BoardController {

    @Autowired
    UserService userService;

    @Autowired
    BoardService boardService;

    @Autowired
    AdoptService adoptService;

    @Autowired
    FindService findService;

    @Autowired
    BoardRepository boardRepository;



    @GetMapping("/adopt")
    @ApiOperation(value = "입양/임보 공고 목록", notes = "입양/임보 공고 목록을 가져온다")
    @ApiResponses({
            @ApiResponse(code = 204, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<BoardDetailListGetRes> adoptBoardList(@PageableDefault(size = 12) Pageable pageable,
            Long boardType, Long weight, Long age, Long gender,
            String searchWord
    ) {

        Page<DogInformation> resultFilterList = boardService.filterBoardList(pageable, boardType, weight, age, gender, searchWord.replace(" ", ""));
        return ResponseEntity.ok(BoardDetailListGetRes.of(200, "Success", resultFilterList));


    }

    @GetMapping("/recent")
    @ApiOperation(value = "입양/임보/실종/보호 최신 공고 목록", notes = "입양/임보/실종/보호 최신 공고 목록을 가져온다")
    @ApiResponses({
            @ApiResponse(code = 204, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<BoardRecentListRes> recentAdoptBoardList() {

        List<DogInformation> resultAdoptList = boardService.getRecentAdoptBoard();
        List<DogInformation> resultFindList = boardService.getRecentFindBoard();
        return ResponseEntity.ok(BoardRecentListRes.of(200, "Success", resultAdoptList, resultFindList));


    }

    @GetMapping("/find")
    @ApiOperation(value = "실종/보호 공고 목록", notes = "실종/보호 공고 목록을 가져온다")
    @ApiResponses({
            @ApiResponse(code = 204, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<BoardDetailListGetRes> findBoardList(@PageableDefault(size = 12) Pageable pageable, Long boardType, Long sido, Long color, Long dogType, String searchWord
    ) {

        Page<DogInformation> resultFilterList = boardService.filterFindBoardList(pageable, boardType, sido, color, dogType, searchWord.replace(" ", ""));
        return ResponseEntity.ok(BoardDetailListGetRes.of(200, "Success", resultFilterList));
    }


    @PostMapping(consumes = {"multipart/form-data"})
    @ApiOperation(value = "게시판 공고 등록", notes = "게시판 공고를 등록한다")
    @ApiResponses({
            @ApiResponse(code = 204, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<BoardRegisterRes> registerAdoptBoard(@ModelAttribute BoardRegisterPostReq boardRegisterPostReq) throws IOException {
        Board board = boardService.registerBoard(boardRegisterPostReq);

        System.out.println(board);
        return ResponseEntity.ok(BoardRegisterRes.of(200, "공고가 정상적으로 등록되었습니다", board.getId()));
    }


    @DeleteMapping("/{boardId}")
    @ApiOperation(value = "게시판 공고 삭제", notes = "게시판 공고를 삭제한다")
    @ApiResponses({
            @ApiResponse(code = 204, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<? extends BaseResponseBody> deleteAdoptBoard(@PathVariable("boardId") String id){

        boardService.deleteBoard(Long.parseLong(id));
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "공고가 정상적으로 삭제되었습니다"));
    }

    @PutMapping(value = "/{boardId}", consumes = {"multipart/form-data"})
    @ApiOperation(value = "게시판 공고 수정", notes = "게시판 공고를 수정한다")
    @ApiResponses({
            @ApiResponse(code = 204, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<? extends BaseResponseBody> updateAdoptBoard(@PathVariable("boardId") Long boardId, @ModelAttribute BoardRegisterPostReq boardRegisterPostReq) throws IOException {

        Board board = boardService.updateBoard(boardId, boardRegisterPostReq);
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "공고가 정상적으로 수정되었습니다"));
    }

    @GetMapping("/{boardId}/{userId}")
    @ApiOperation(value = "게시판 공고 상세 글 정보", notes = "게시판 공고 상세 정보를 가져온다")
    @ApiResponses({
            @ApiResponse(code = 204, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<BoardDetailGetRes> boardDetail(@PathVariable("boardId") String boardId, @PathVariable("userId") String userId){
        boolean isOwner = false;
        boolean isBookmarked = false;

        System.out.println(boardId);
        System.out.println(userId);
        Board board = boardService.getBoardByBoardId(Long.parseLong(boardId));

        // 공고 -> 실종 / 보호 (유사공고 찾기)
        List<Board> listSimilar = null;
        List<DogInformation> listSimilarDog = null;
        if(board.getType().getId() > 2) {
            listSimilarDog = findService.getBoardSimilarListByBoard(board);
            //listSimilarDog
            System.out.println("글 상세 보기 : listSimilar >>> " + listSimilar);
            System.out.println("글 상세 보기 : listSimilarDog >>> " + listSimilarDog);
        }

        DogInformation dogInformation = boardService.getDogInformationByBoard(board);
        List<BoardComment> boardComments = boardService.getBoardCommentsByBoard(board);
        List<BoardImage> boardImages = boardService.getBoardImagesByBoard(board);

        List<String> fileList  = new ArrayList<>();
        if(boardImages!=null){
            for(BoardImage img : boardImages){
                fileList.add(img.getImgFullPath());
            }
        }


        String writer = userService.getUserName(board.getUserId());
        if(userId!=null){
            if(board.getUserId().equals(userId)) isOwner = true;
        }


        List<Bookmark> userBookmarks = userService.getBookmarkList(userId);

        if(userBookmarks!=null){
            for(Bookmark bookmark : userBookmarks){
                if(bookmark.getBoardId()==Long.parseLong(boardId)){
                    isBookmarked = true;
                    break;
                }
            }
        }

        System.out.println("북마크체크"+isBookmarked+" "+userId+" "+board.getUserId());
        return ResponseEntity.ok(BoardDetailGetRes.of(200, "Success", isBookmarked, isOwner,  writer, dogInformation, fileList, boardComments, listSimilar, listSimilarDog));
    }




    @PostMapping("/bookmark")
    @ApiOperation(value = "게시글 북마크 등록", notes = "게시글을 북마크로 등록한다.")
    @ApiResponses({
            @ApiResponse(code = 204, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<? extends BaseResponseBody> insertBookmark(@RequestBody @ApiParam(value="북마크 정보", required = true) BookmarkReq bookmarkReq){
        Bookmark bookmark = boardService.getBookmark(bookmarkReq);
        if(bookmark!=null) return ResponseEntity.status(409).body(BaseResponseBody.of(409, "이미 북마크로 등록된 게시물입니다"));

        System.out.println("test");
        boardService.insertBookmark(bookmarkReq);

        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "북마크가 정상적으로 등록되었습니다"));
    }

    @DeleteMapping("/bookmark/{userId}/{boardId}")
    @ApiOperation(value = "게시글 북마크 삭제", notes = "게시글을 북마크에서 삭제한다.")
    @ApiResponses({
            @ApiResponse(code = 204, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<? extends BaseResponseBody> deleteBookmark(@PathVariable("boardId") String boardId, @PathVariable("userId") String userId){

        BookmarkReq req = new BookmarkReq();
        req.setBoardId(Long.parseLong(boardId));
        req.setUserId(userId);

        Bookmark bookmark = boardService.deleteBookmark(req);

        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "북마크가 정상적으로 삭제되었습니다"));
    }




    @GetMapping("/myboard/{id}")
    @ApiOperation(value = "사용자 작성글 목록", notes = "사용자가 작성한 글만 가져온다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<BoardListGetRes> findMyBoardList(@PathVariable("id") String id){
        List<Board> boardList = boardService.getBoardListByUserId(id);
        return ResponseEntity.ok(BoardListGetRes.of(200, "Success", boardList, boardList.size()));
    }

    @GetMapping("/sido")
    @ApiOperation(value = "시/도 코드 리스트", notes = "시/도 코드 정보를 가져온다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<SidoCodeGetRes> findSidoList(){

        List<Sido> sidoList = boardService.getSidoList();

        return ResponseEntity.ok(SidoCodeGetRes.of(200, "Success", sidoList));
    }


    @GetMapping("/gugun/{sido}")
    @ApiOperation(value = "구/군 코드 리스트", notes = "구/군 코드 정보를 가져온다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<GugunCodeGetRes> findGugunList(@PathVariable("sido") Long sido){

        List<Gugun> gugunList = boardService.getGugunListBySido(sido);
        return ResponseEntity.ok(GugunCodeGetRes.of(200, "Success",gugunList));
    }

    @GetMapping("/dogType")
    @ApiOperation(value = "강아지 품종 코드 리스트", notes = "강아지 품종 코드 정보를 가져온다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<DogTypeGetRes> findDogTypeList(){

        List<DogType> dogTypeList = boardService.getDogTypeList();
        return ResponseEntity.ok(DogTypeGetRes.of(200, "Success", dogTypeList));
    }

}
