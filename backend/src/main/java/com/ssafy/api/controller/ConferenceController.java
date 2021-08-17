package com.ssafy.api.controller;

import com.ssafy.common.model.response.BaseResponseBody;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/conference")
public class ConferenceController {

    /**
     * 프론트 단에서 보내온 두 사용자의 카카오톡 아이디, 회의 종류 (입양 / 임보 ) 를 바탕으로
     * 화상회의 정보를 등록한다.
     *
     * @return 응답코드
     */
//    @PostMapping("/")
//    @ApiOperation(value = "화상회의 초기정보 세팅", notes = "화상회의 고유 ID, 참여자 정보를 세팅한다.")
//    @ApiResponses({
//            @ApiResponse(code = 204, message = "성공"),
//            @ApiResponse(code = 404, message = "회의정보 세팅 실패")
//            @ApiResponse(code = 500, message = "서버 오류")
//    })
//    public ResponseEntity<? extends BaseResponseBody> registerConference(@RequestBody @ApiParam(value = "회의실 등록 정보", required = true) ) {
//        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "화상회의가 정상적으로 등록되었습니다"));
//    }
}
