package com.ssafy.api.service;

import com.ssafy.api.request.MbtiCalPostReq;
import com.ssafy.db.entity.board.Board;
import com.ssafy.db.entity.board.DogInformation;
import com.ssafy.db.entity.board.MBTI;

import java.util.List;

public interface MBTIService {
    MBTI getMbtiByName(String mbti);
    MBTI getMbtiById(Long id);
    MBTI calMbti(MbtiCalPostReq mbtiCalPostReq);
    List<DogInformation> getSameMbtiDogBoardByName(String mbti);
    List<DogInformation> getSameMbtiDogBoardById(Long id);
    List<MBTI> getMbtiList();
}
