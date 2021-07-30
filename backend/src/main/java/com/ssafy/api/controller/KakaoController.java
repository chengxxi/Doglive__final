package com.ssafy.api.controller;

import com.ssafy.api.response.UserLoginPostRes;
import com.ssafy.api.service.KakaoAPI;
import com.ssafy.api.service.UserService;
import com.ssafy.db.entity.auth.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@CrossOrigin(origins={"*"}, maxAge=6000)
@RestController
@RequestMapping("/kakao")
public class KakaoController {
    @Autowired
    private KakaoAPI kakaoAPI;
    @Autowired
    UserService userService;
    @GetMapping(value = "/oauth")
    public ResponseEntity<String> kakaoConnect(){
        StringBuffer url = new StringBuffer();
        url.append("https://kauth.kakao.com/oauth/authorize?");
        url.append("client_id=" + "8a6da8dccc17d0706c19f099353a04ca");
        url.append("&redirect_uri=http://localhost:8080/kakao/login");
        url.append("&response_type=code");
        System.out.println(url.toString());
        return new ResponseEntity<String>(url.toString(),HttpStatus.OK);
    }

    @RequestMapping(value="/login")
    public ResponseEntity<UserLoginPostRes> login(@RequestParam("code") String code, HttpSession session) {
        HashMap<String,Object> Token = kakaoAPI.getAccessToken(code);

        String access_Token = (String) Token.get("accessToken");
        String refresh_Token = (String) Token.get("refreshToken");

        System.out.println("code : " + code);
        // 사용자의 정보를 <string, 객체> 로 생성
        HashMap<String, Object> userInfo = kakaoAPI.getUserInfo(Token); //front 전송 용 유저 Info Hash Map
        HashMap<String, Object> userProfile = kakaoAPI.getUserProfile(access_Token, refresh_Token); //create 용 유저 Profile Hash Map

        System.out.println("login Controller : " + userInfo);

        HashMap<String, Object> userObject = new HashMap<String, Object>();
        userObject.put("Token",Token);
        userObject.put("userInfo",userInfo);

        System.out.println("-------------------");
        System.out.println(userObject);
        System.out.println("-------------------");

        // 카카오가 보낸 정보에서 id를 가져온다.
        String id = (String) userInfo.get("userid");
        System.out.println(id);
        User user = userService.getUserById(id);
        // 회원가입이 되어있는 경우
        if(user!=null){
            System.out.println("login success!");
            return ResponseEntity.ok(UserLoginPostRes.of(200,"Success", userObject));
        }
        // 클라이언트의 이메일이 존재할 때 세션에 해당 이메일과 토큰 등록
        if (userInfo.get("email") != null) {
//                session.setAttribute("userId", userInfo.get("email"));
//                session.setAttribute("access_Token", userObject);
        }
        userService.createUser(access_Token, refresh_Token, userProfile);
        return ResponseEntity.ok(UserLoginPostRes.of(200, "Success", userObject));

    }

    @GetMapping(value="/logout")
    public Object logout(HttpSession session){
        kakaoAPI.Logout((String)session.getAttribute("access_Token"));
        session.removeAttribute("access_Token");
        session.removeAttribute("refresh_Token");
        session.removeAttribute("login");
        return "logout";
    }
}