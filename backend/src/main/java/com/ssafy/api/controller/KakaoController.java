package com.ssafy.api.controller;

import com.ssafy.api.response.UserLoginPostRes;
import com.ssafy.api.service.KakaoAPI;
import com.ssafy.api.service.UserService;
import com.ssafy.db.entity.auth.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.http.client.methods.HttpHead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;

import static java.util.Objects.isNull;

@CrossOrigin(origins={"*"}, maxAge=6000)
@Api(value = "카카오 API", tags = {"Kakao"})
@RestController
@RequestMapping("/api/v1/kakao")
public class KakaoController {
    @Autowired
    private KakaoAPI kakaoAPI;
    @Autowired
    UserService userService;

    @GetMapping(value = "/oauth")
    @ApiOperation(value = "code를 통해 accessToken 획득", notes = "Kakao 인증서버에 code를 넘겨주어 accessToken, refreshToken을 발급받는다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<UserLoginPostRes> kakaoConnect(@RequestParam("code") String code, HttpSession session) {
        HashMap<String,Object> Token = kakaoAPI.getAccessToken(code);
        System.out.println(Token);
        return ResponseEntity.ok(UserLoginPostRes.of(200,"Success", Token));
    }

    @PostMapping(value="/login")
    @ApiOperation(value = "accessToken을 통해 사용자 정보 조회", notes = "Kakao 인증서버에 accessToken을 넘겨주어 userInfo, userProfile을 받아온다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<UserLoginPostRes> login(@RequestBody HashMap<String, Object> Token, HttpSession session) {
        System.out.println(Token.toString());
        // Token 정보를 <String, 객체>로 생성
        String accessToken = (String) Token.get("accessToken");
        String refreshToken = (String) Token.get("refreshToken");

        // 사용자의 정보를 <string, 객체> 로 생성
        HashMap<String, Object> userInfo = kakaoAPI.getUserInfo(accessToken, refreshToken); //front 전송 용 유저 Info Hash Map
        HashMap<String, Object> userProfile = kakaoAPI.getUserProfile(accessToken, refreshToken); //create 용 유저 Profile Hash Map
        HashMap<String, Object> userObject = new HashMap<String, Object>();
        userObject.put("Token",Token);
        userObject.put("userInfo",userInfo);

        // 카카오가 보낸 정보에서 id를 가져온다.
        String id = (String) userInfo.get("userid");
        User user = userService.getUserById(id);

        // 회원가입이 되어있는 경우
        if(!isNull(user)){
            return ResponseEntity.ok(UserLoginPostRes.of(200,"Success", userObject));
        }
        // 클라이언트의 이메일이 존재할 때 세션에 해당 이메일과 토큰 등록
        if (userInfo.get("email") != null) {
            session.setAttribute("userId", userInfo.get("email"));
            session.setAttribute("access_Token", userObject);
        }
        userService.createUser(accessToken, refreshToken, userProfile);
        return ResponseEntity.ok(UserLoginPostRes.of(200, "Success", userObject));
    }

    @ApiOperation(value = "카카오 계정 로그아웃", notes = "Kakao 인증서버에 accessToken을 넘겨주어 로그인된 카카오 계정을 로그아웃 시킨다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    @GetMapping(value="/logout")
    public ResponseEntity<String> logout(@CookieValue(value="accessToken", required = false) Cookie access_Token){
        if(access_Token == null)
            return ResponseEntity.ok("토큰이 유효하지 않습니다.");

        System.out.println("logout accessToken : " + access_Token.getValue());
        kakaoAPI.Logout(access_Token.getValue());
        return ResponseEntity.ok("로그아웃 되었습니다.");
    }

}
