package com.ssafy.api.controller;


import com.ssafy.api.request.BoardRegisterPostReq;
import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.api.response.BoardListGetRes;
import com.ssafy.api.response.UserLoginPostRes;
import com.ssafy.api.service.AdoptService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.board.Board;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 사용자 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@CrossOrigin(origins={"*"}, maxAge=6000)
@Api(value = "입양/임보 API", tags = {"Adopt"})
@RestController
@RequestMapping("/api/v1/adopt")
public class BoardController {

    @Autowired
    AdoptService adoptService;

    @GetMapping("")
    @ApiOperation(value = "입양/임보 공고 목록", notes = "입양/입양 공고 목록을 가져온다")
    @ApiResponses({
            @ApiResponse(code = 204, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<BoardListGetRes> boardList(){
        List<Board> boardList = adoptService.getAdoptBoardList();


        return ResponseEntity.ok(BoardListGetRes.of(200, "Success", boardList, boardList.size()));
    }

    @PostMapping()
    @ApiOperation(value = "입양/임보 공고 등록", notes = "입양/입양 공고를 삭제한다")
    @ApiResponses({
            @ApiResponse(code = 204, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<? extends BaseResponseBody> registerAdoptBoard(@RequestBody @ApiParam(value="공고 등록 정보", required = true)BoardRegisterPostReq boardRegisterPostReq){
        Board board = adoptService.registerAdoptBoard(boardRegisterPostReq);
        System.out.println(board);
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "공고가 정상적으로 등록되었습니다"));
    }



    @DeleteMapping("/{boardId}")
    @ApiOperation(value = "입양/임보 공고 삭제", notes = "입양/입양 공고를 삭제한다")
    @ApiResponses({
            @ApiResponse(code = 204, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<? extends BaseResponseBody> deleteAdoptBoard(@PathVariable("boardId") String id){
        adoptService.deleteAdoptBoard(Long.parseLong(id));
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "공고가 정상적으로 삭제되었습니다"));
    }

}
