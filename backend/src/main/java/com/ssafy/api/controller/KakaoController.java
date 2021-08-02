package com.ssafy.api.controller;

import com.ssafy.api.response.UserLoginPostRes;
import com.ssafy.api.service.KakaoAPI;
import com.ssafy.api.service.UserService;
import com.ssafy.db.entity.auth.User;
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
@RestController
@RequestMapping("/kakao")
public class KakaoController {
    @Autowired
    private KakaoAPI kakaoAPI;
    @Autowired
    UserService userService;

    // code를 통해 accessToken을 받아오는 메소드
    @GetMapping(value = "/oauth")
    public ResponseEntity<UserLoginPostRes> kakaoConnect(@RequestParam("code") String code, HttpSession session) {
        HashMap<String,Object> Token = kakaoAPI.getAccessToken(code);
        return ResponseEntity.ok(UserLoginPostRes.of(200,"Success", Token));
    }

    // accessToken을 통해 사용자 정보를 받아오는 메소드
    @RequestMapping(value="/login")
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

    @RequestMapping(value="/logout")
    public ResponseEntity<String> logout(@CookieValue(value="accessToken", required = false) Cookie access_Token){
        if(access_Token == null)
            return ResponseEntity.ok("토큰이 유효하지 않습니다.");

        System.out.println("logout accessToken : " + access_Token.getValue());
        kakaoAPI.Logout(access_Token.getValue());
        return ResponseEntity.ok("로그아웃 되었습니다.");
    }
}
