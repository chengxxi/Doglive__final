package com.ssafy.api.controller;


import com.ssafy.api.request.AdoptFormReq;
import com.ssafy.api.request.MbtiCalPostReq;
import com.ssafy.api.response.BoardListGetRes;
import com.ssafy.api.response.MBTIDetailGetRes;
import com.ssafy.api.service.MBTIService;
import com.ssafy.db.entity.board.Board;
import com.ssafy.db.entity.board.MBTI;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 유기견 MBTI 테스트 API 요청 처리를 위한 컨트롤러 정의.
 */
@CrossOrigin(origins={"*"}, maxAge=6000)
@Api(value = "유기견 MBTI Test API", tags = {"MBTI"})
@RestController
@RequestMapping("/api/v1/mbti")
public class MBTIController {

    @Autowired
    MBTIService mbtiService;

    @GetMapping("/id/{id}")
    @ApiOperation(value = "MBTI 별 상세 정보 (1)", notes = "id로 MBTI 상세 정보를 가져온다")
    @ApiResponses({
            @ApiResponse(code = 204, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "데이터 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<MBTIDetailGetRes> mbtiDetailById(@PathVariable("id") String id){
        MBTI mbti = mbtiService.getMbtiById(Long.parseLong(id));
        if(mbti==null) return ResponseEntity.ok(MBTIDetailGetRes.of(404, "Fail", null));

        return ResponseEntity.ok(MBTIDetailGetRes.of(200, "Success", mbti));
    }


    @GetMapping("/name/{name}")
    @ApiOperation(value = "MBTI 별 상세 정보 (2)", notes = "name으로 MBTI 상세 정보를 가져온다")
    @ApiResponses({
            @ApiResponse(code = 204, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "데이터 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<MBTIDetailGetRes> mbtiDetailByName(@PathVariable("name") String name){
        MBTI mbti = mbtiService.getMbtiByName(name);
        if(mbti==null) return ResponseEntity.ok(MBTIDetailGetRes.of(404, "Fail", null));

        return ResponseEntity.ok(MBTIDetailGetRes.of(200, "Success", mbti));
    }


    /* 항목 별 점수로 MBTI 계산하는 메소드 */
    @PostMapping("")
    @ApiOperation(value = "MBTI 결과 출력", notes = "항목 별 점수로 MBTI를 계산하여 결과를 리턴한다")
    @ApiResponses({
            @ApiResponse(code = 204, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "데이터 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<MBTIDetailGetRes> returnMbtiTest(@RequestBody @ApiParam(value="MBTI 테스트 점수", required = true) MbtiCalPostReq mbtiCalPostReq){
        MBTI mbti = mbtiService.calMbti(mbtiCalPostReq);
        if(mbti==null) return ResponseEntity.ok(MBTIDetailGetRes.of(404, "Fail", null));

        return ResponseEntity.ok(MBTIDetailGetRes.of(200, "Success", mbti));
    }


}
