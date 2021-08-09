package com.ssafy.api.service;

import com.ssafy.api.request.MbtiCalPostReq;
import com.ssafy.db.entity.board.MBTI;

public interface MBTIService {
    MBTI getMbtiByName(String mbti);
    MBTI getMbtiById(Long id);
    MBTI calMbti(MbtiCalPostReq mbtiCalPostReq);

}
