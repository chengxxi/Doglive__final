package com.ssafy.api.controller;

import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.api.service.UserService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.auth.User;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

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


}
