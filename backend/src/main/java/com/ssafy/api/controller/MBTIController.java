package com.ssafy.api.controller;


import com.ssafy.api.request.MbtiCalPostReq;
import com.ssafy.api.response.MBTIDetailGetRes;
import com.ssafy.api.response.MBTIListGetRes;
import com.ssafy.api.response.MBTIResultPostRes;
import com.ssafy.api.service.MBTIService;
import com.ssafy.db.entity.board.Board;
import com.ssafy.db.entity.board.DogInformation;
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
    @ApiOperation(value = "MBTI 별 상세 정보", notes = "id로 MBTI 상세 정보를 가져온다")
    @ApiResponses({
            @ApiResponse(code = 204, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "데이터 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<MBTIResultPostRes> mbtiDetailById(@PathVariable("id") Long id){
        MBTI mbti = mbtiService.getMbtiById(id);

        List<DogInformation> matchedBoardList = mbtiService.getSameMbtiDogBoardById(mbti.getId());
        if(mbti==null) return ResponseEntity.ok(MBTIResultPostRes.of(404, "Fail", null, null));
        return ResponseEntity.ok(MBTIResultPostRes.of(200, "Success", mbti, matchedBoardList));
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
    @ApiOperation(value = "MBTI 결과 출력", notes = "항목 별 점수로 MBTI를 계산하여 결과와 매칭 보드 리스트를 리턴한다")
    @ApiResponses({
            @ApiResponse(code = 204, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "데이터 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<MBTIResultPostRes> returnMbtiTest(@RequestBody @ApiParam(value="MBTI 테스트 점수", required = true) MbtiCalPostReq mbtiCalPostReq){
        MBTI mbti = mbtiService.calMbti(mbtiCalPostReq);
        if(mbti==null) return ResponseEntity.ok(MBTIResultPostRes.of(404, "Fail", null, null));

        List<DogInformation> matchedBoardList = mbtiService.getSameMbtiDogBoardByName(mbti.getName());
        return ResponseEntity.ok(MBTIResultPostRes.of(200, "Success", mbti, matchedBoardList));
    }


    /* 항목 별 점수로 MBTI 계산하는 메소드 */
    @GetMapping("")
    @ApiOperation(value = "MBTI 전체 리스트 정보", notes = "MBTI 전체 데이터를 가져온다")
    @ApiResponses({
            @ApiResponse(code = 204, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "데이터 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<MBTIListGetRes> returnMbtiList(){
        List<MBTI> mbtiList = mbtiService.getMbtiList();
        if(mbtiList==null) return ResponseEntity.ok(MBTIListGetRes.of(404, "Fail", null));


        return ResponseEntity.ok(MBTIListGetRes.of(200, "Success", mbtiList));
    }


}
