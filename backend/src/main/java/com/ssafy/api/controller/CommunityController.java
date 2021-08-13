package com.ssafy.api.controller;

import com.ssafy.api.request.CommunityParamDto;
import com.ssafy.api.request.CommunityRegisterPostReq;
import com.ssafy.api.response.BoardListGetRes;
import com.ssafy.api.response.CommunityBoardGetRes;
import com.ssafy.api.response.CommunityListGetRes;
import com.ssafy.api.service.CommunityService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.community.Community;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 커뮤니티 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@CrossOrigin(origins={"*"}, maxAge=6000)
@Api(value = "커뮤니티 API", tags = {"Community"})
@RestController
@RequestMapping("/api/v1/community")
public class CommunityController {

    @Autowired
    CommunityService communityService;

    @PostMapping()
    @ApiOperation(value = "커뮤니티 게시글 등록", notes = "커뮤니티 게시글을 등록한다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<? extends BaseResponseBody> registerCommunityBoard(@RequestBody @ApiParam(value="커뮤니티 게시글 정보", required = true)CommunityRegisterPostReq communityRegisterPostReq){
        Community community = communityService.registerCommunityBoard(communityRegisterPostReq);

        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "게시글이 정상적으로 등록되었습니다"));
    }

    @DeleteMapping("/{communityId}")
    @ApiOperation(value = "커뮤니티 게시글 삭제", notes = "커뮤니티의 게시글을 삭제한다")
    @ApiResponses({
            @ApiResponse(code = 204, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<? extends BaseResponseBody> deleteCommunityBoard(@PathVariable("communityId") Long id){
        communityService.deleteCommunityBoard(id);
        return ResponseEntity.status(204).body(BaseResponseBody.of(204, "게시글이 정상적으로 삭제되었습니다"));
    }

    @PutMapping("/{communityId}")
    @ApiOperation(value = "커뮤니티 게시글 수정", notes = "커뮤니티 게시글을 수정한다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<? extends BaseResponseBody> updateCommunityBoard(@PathVariable("communityId") Long id, @RequestBody @ApiParam(value="커뮤니티 수정 정보", required = true) CommunityRegisterPostReq communityRegisterPostReq){
        Community community = communityService.updateCommunityBoard(id, communityRegisterPostReq);
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "게시글이 정상적으로 수정되었습니다"));
    }

    @GetMapping("/list")
    @ApiOperation(value = "커뮤니티 게시글 목록", notes = "커뮤니티 게시글 목록을 가져온다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public List<CommunityParamDto> communityBoardList(){
        List<CommunityParamDto> communityList = communityService.communityList();
        System.out.println("list: " + communityList);
        return communityList;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "사용자 작성글 목록", notes = "커뮤니티에 사용자가 작성한 글만 가져온다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<CommunityListGetRes> findCommunityList(@PathVariable("id") String id){
        List<Community> communityList = communityService.getCommunityListByUserId(id);
        return ResponseEntity.ok(CommunityListGetRes.of(200, "Success", communityList, communityList.size()));
    }


    @GetMapping("/detail/{communityId}")
    @ApiOperation(value = "커뮤니티 게시글 상세정보", notes = "커뮤니티 글의 상세정보를 가져온다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<CommunityBoardGetRes> findCommunityById(@PathVariable("communityId") Long id){
        Community community = communityService.getCommunityById(id);
        return ResponseEntity.ok(CommunityBoardGetRes.of(200, "Success", community));
    }

}
