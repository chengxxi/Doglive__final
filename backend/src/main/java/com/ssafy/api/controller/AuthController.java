package com.ssafy.api.controller;


import com.ssafy.api.service.KakaoAPI;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * 인증 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Api(value = "인증 API", tags = {"Auth."})
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private KakaoAPI kakaoAPI;

    @RequestMapping(value="/")
    public String index() {
        return "index";
    }

    @PostMapping(value="/login")
    public String login(@RequestParam("code") String code, HttpSession session) {
        String access_Token = kakaoAPI.getAccessToken(code);
        System.out.println("code : " + code);
        // 사용자의 정보를 <string, 객체> 로 생성
        HashMap<String, Object> userInfo = kakaoAPI.getUserInfo(access_Token);
        System.out.println("login Controller : " + userInfo);

        // 클라이언트의 이메일이 존재할 때 세션에 해당 이메일과 토큰 등록
        if (userInfo.get("email") != null) {
            session.setAttribute("userId", userInfo.get("email"));
            session.setAttribute("access_Token", access_Token);
        }
        return "login";
    }


    @GetMapping(value="/logout")
    public Object logout(HttpSession session){
        kakaoAPI.Logout((String)session.getAttribute("access_Token"));
        session.removeAttribute("access_Token");
        session.removeAttribute("refresh_Token");
        // session.removeAttribute("userkey");
        session.removeAttribute("login");
        return "logout";
    }

}


