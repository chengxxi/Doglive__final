package com.ssafy.api.controller;

import com.ssafy.api.request.AdoptFormReq;


import com.ssafy.api.request.StatusUpdatePutReq;

import com.ssafy.api.response.AdoptFormData;
import com.ssafy.api.response.AdoptFormGetRes;
import com.ssafy.api.response.CounselingHistoryGetRes;
import com.ssafy.api.service.AdoptService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.auth.CounselingHistory;
import io.swagger.annotations.*;
import org.h2.util.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 유기동물 입양 API 요청 처리를 위한 컨트롤러 정의.
 */
@CrossOrigin(origins={"*"}, maxAge=6000)
@Api(value = "유기동물 입양 API", tags = {"Adopt"})
@RestController
@RequestMapping("/api/v1/adopt")
public class AdoptController {

    @Autowired
    AdoptService adoptService;


    @PostMapping("/form/{userId}")
    @ApiOperation(value = "입양신청서 등록", notes = "입양신청서를 작성하고 저장한다")
    @ApiResponses({
            @ApiResponse(code = 204, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<CounselingHistoryGetRes> insertAdoptForm(@PathVariable("userId") String id, @RequestBody @ApiParam(value="입양신청 등록 정보", required = true) AdoptFormReq adoptFormReq){

        CounselingHistory counseling = adoptService.insertAdoptForm(id, adoptFormReq);

        return ResponseEntity.status(200).body(CounselingHistoryGetRes.of(200, "입양신청서가 정상적으로 등록되었습니다", counseling));
    }


    @GetMapping("/{formId}")
    @ApiOperation(value = "입양신청서 읽기", notes = "제출된 입양 신청서를 read한다..")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 409, message = "이미 존재하는 유저"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<AdoptFormGetRes> readAdoptForm(@PathVariable("formId") Long formId){
        AdoptFormData adoptForm = adoptService.readAdoptForm(formId);

        return ResponseEntity.status(200).body(AdoptFormGetRes.of(200, "입양 신청서 읽기 성공", adoptForm));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "신청서 현황 수정", notes = "신청서 현황을 수정한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 409, message = "이미 존재하는 유저"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<? extends BaseResponseBody> updateStatus(@PathVariable("id") Long id,  @RequestBody @ApiParam(value="신청현황수정", required = true) StatusUpdatePutReq statusUpdatePutReq) {
        System.out.println(statusUpdatePutReq.getResult());
        adoptService.updateStatus(id, statusUpdatePutReq);

        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "입양 신청서 결과가 정상적으로 수정되었습니다."));
    }



    @GetMapping("/check/{userId}/{boardId}")
    @ApiOperation(value = "입양신청서 작성 여부 확인", notes = "해당 게시물에 대한 진행중인 입양신청 데이터가 있는지 확인한다.")
    @ApiResponses({
            @ApiResponse(code = 204, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<? extends BaseResponseBody> CheckAdoptBoard(@PathVariable("userId") String userId, @PathVariable("boardId") Long boardId){

        CounselingHistory counselingHistory = adoptService.canAdoptForm(userId, boardId);
        System.out.println(counselingHistory);
        if(counselingHistory == null) {
            System.out.println(userId + "님의 " + boardId + "번 글에 대한 신청 기록이 없습니다.");
            return ResponseEntity.status(204).body(CounselingHistoryGetRes.of(204, "입양 신청이 가능합니다.", null)); // No Content
        }
        return ResponseEntity.status(200).body(CounselingHistoryGetRes.of(200, "이미 작성한 신청폼이 있습니다.", counselingHistory));

    }

    @DeleteMapping("/{conselingId}")
    @ApiOperation(value = "신청서 삭제", notes = "연결된 채팅방과 함께 신청서를 삭제한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 409, message = "이미 존재하는 유저"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<? extends BaseResponseBody> deleteCounselingHistory(@PathVariable("conselingId") Long conselingId) {

        adoptService.deleteCounselingAndChatRoom(conselingId);
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "입양 신청서와 채팅방이 정상적으로 삭제되었습니다."));
    }
}
