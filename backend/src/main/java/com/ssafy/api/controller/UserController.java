package com.ssafy.api.controller;

import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.api.request.UserUpdateNoImagePutReq;
import com.ssafy.api.request.UserUpdatePutReq;
import com.ssafy.api.response.*;
import com.ssafy.api.service.BoardService;
import com.ssafy.api.service.UserService;
import com.ssafy.common.auth.KakaoUserDetails;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.auth.Bookmark;
import com.ssafy.db.entity.auth.CounselingHistory;
import com.ssafy.db.entity.auth.User;
import com.ssafy.db.entity.auth.UserProfile;
import com.ssafy.db.entity.board.Board;
import io.swagger.annotations.*;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 사용자 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@CrossOrigin(origins={"*"}, maxAge=6000)
@Api(value = "유저 API", tags = {"User"})
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    BoardService boardService;

    @GetMapping("present/{userId}")
    @ApiOperation(value = "존재하는 회원 확인", notes = "해당 userID를 사용하는 사용자가 있는지 확인한다.")
    @ApiResponses({
            @ApiResponse(code = 204, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<BaseResponseBody> isPresentUser(@PathVariable String userId) {
        User user = userService.getUserById(userId);
        if(user == null)
            return ResponseEntity.status(200).body(BaseResponseBody.of(200, "사용할 수 있는 ID입니다."));
        return ResponseEntity.status(409).body(BaseResponseBody.of(409, "이미 존재하는 사용자 ID입니다."));
    }


    @PutMapping(value = "/{id}", consumes = {"multipart/form-data"})
    @ApiOperation(value = "회원 정보 수정", notes = "DB에 저장된 회원정보를 새로운 값으로 수정한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 409, message = "이미 존재하는 유저"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<? extends BaseResponseBody> updateUser(@PathVariable("id") String id,  @ModelAttribute UserUpdatePutReq userUpdatePutReq) throws IOException {
        System.out.println("req : " + userUpdatePutReq);
        userService.updateUserProfile(id, userUpdatePutReq);
        return ResponseEntity.status(200).body(BaseResponseBody.of(200,"Success"));
    }

    @PutMapping( "/noimage/{id}")
    @ApiOperation(value = "회원 정보 수정", notes = "DB에 저장된 회원정보를 새로운 값으로 수정한다.(이미지가 없는 경우)")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 409, message = "이미 존재하는 유저"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<? extends BaseResponseBody> updateUserWithoutImage(@PathVariable("id") String id, @RequestBody @ApiParam(value="회원정보수정", required = true) UserUpdateNoImagePutReq userUpdateNoImagePutReq) {
        userService.updateUserNoImage(id, userUpdateNoImagePutReq);
        return ResponseEntity.status(200).body(BaseResponseBody.of(200,"Success"));
    }



    @DeleteMapping("/{id}")
    @ApiOperation(value = "회원 탈퇴", notes = "DB에 저장된 회원정보를 삭제한다.")
    @ApiResponses({
            @ApiResponse(code = 204, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<? extends BaseResponseBody> deleteUser(@PathVariable("id") String id) {
        System.out.println(id + "탈퇴할거야");
        boolean flag = userService.deleteUser(id);
        System.out.print("delete User : " + flag);
        return ResponseEntity.status(204).body(BaseResponseBody.of(204, "Success"));
    }

    @GetMapping("/bookmark/{id}")
    @ApiOperation(value = "사용자 북마크 목록", notes = "사용자 북마크 목록을 가져온다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<BoardListGetRes> findBookmarkList(@PathVariable("id") String id){
        List<Bookmark> bookmarkList = userService.getBookmarkList(id);
        List<Board> boardList = new ArrayList<>();

        for (Bookmark bookmark: bookmarkList) {
            boardList.add(boardService.getBoardByBoardId(bookmark.getBoardId()));
        }
        return ResponseEntity.ok(BoardListGetRes.of(200, "Success", boardList, boardList.size()));
    }

    @GetMapping("/counseling/{id}")
    @ApiOperation(value = "입양 상담 신청 결과 조회", notes = "사용자의 입양 신청 조회 결과를 가지고온다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<CounselingHistoryListGetRes> findCounselingResults(@PathVariable("id") String id){
        List<CounselingHistory> counselingHistoryList = userService.getCounselingResult(id);
        if(counselingHistoryList.size() == 0){
            return ResponseEntity.ok(CounselingHistoryListGetRes.of(404,"신청 내역이 없습니다.",null,0));
        }
        return ResponseEntity.ok(CounselingHistoryListGetRes.of(200,"Success",counselingHistoryList,counselingHistoryList.size()));
    }

    @GetMapping("/applicant/{id}")
    @ApiOperation(value = "상담 신청자 목록 조회", notes = "작성자 아이디에 따라 상담 신청자 목록을 불러온다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<CounselingHistoryListGetRes> findApplicants(@PathVariable("id") String id){
        List<CounselingHistory> applicantList = userService.getApplicantList(id);
        List<UserProfile> userProfileList = new ArrayList<>();

        for (CounselingHistory counselingHistory: applicantList) {
            UserProfile userProfile = counselingHistory.getApplicantId();
            userProfileList.add(userProfile);
        }

        System.out.println(userProfileList);

        if(applicantList.size() == 0){
            return ResponseEntity.ok(CounselingHistoryListGetRes.of(404,"신청자가 없습니다.",null,0));
        }
        return ResponseEntity.ok(CounselingHistoryListGetRes.of(200,"Success",applicantList,applicantList.size()));
    }


    @GetMapping("/{id}")
    @ApiOperation(value = "회원 본인 정보 조회", notes = "로그인한 회원 본인의 정보를 응답한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<UserRes> getUserInfo(@PathVariable("id") String id) {
        UserProfile userProfile = userService.getUserProfile(id);
        if(userProfile == null){
            return null;
        }
        return ResponseEntity.ok(UserRes.of(200,"Success",userProfile));
    }

}
