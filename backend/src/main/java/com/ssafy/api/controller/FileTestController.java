package com.ssafy.api.controller;

import com.ssafy.api.service.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin("*")
@RequiredArgsConstructor
@Controller
public class FileTestController {
    private final S3Uploader s3Uploader;

    @GetMapping("/file") // 파일 업로드 테스트용 뷰
    public String uploadView(){
        return "upload";
    }

    @PostMapping("/upload") //업로드 테스트 API
    public void upload(@RequestParam("data") List<MultipartFile> multipartFiles) throws IOException {
        String coverUrl = s3Uploader.upload(multipartFiles.get(0), "static");
        System.out.println("cover"+coverUrl);
    }

    @PostMapping("/upload/update") //업로드 테스트 API
    public void upload(@RequestParam(
            "url") String currentUrl, @RequestParam("data") List<MultipartFile> multipartFiles) throws IOException {
        String coverUrl = s3Uploader.upload(currentUrl, multipartFiles.get(0), "static");
        System.out.println("cover"+coverUrl);
    }
}