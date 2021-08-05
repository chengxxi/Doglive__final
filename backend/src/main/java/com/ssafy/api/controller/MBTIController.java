package com.ssafy.api.controller;


import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 유기견 MBTI 테스트 API 요청 처리를 위한 컨트롤러 정의.
 */
@CrossOrigin(origins={"*"}, maxAge=6000)
@Api(value = "유기견 MBTI Test API", tags = {"Board"})
@RestController
@RequestMapping("/api/v1/mbti")
public class MBTIController {

    //MBTI Test별 결과값을 저장해 놓을 DB가 필요하넹


}
