package com.ssafy.api.service;

import com.ssafy.api.request.MbtiCalPostReq;
import com.ssafy.db.entity.board.MBTI;
import com.ssafy.db.repository.board.MBTIRepository;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("mbtiService")
public class MBTIServiceImpl implements MBTIService{

    @Autowired
    MBTIRepository mbtiRepository;


    @Override
    public MBTI getMbtiByName(String mbti) {

        Optional<MBTI> mbtiObject = mbtiRepository.findByName(mbti);

        if(mbtiObject.isPresent()) return mbtiObject.get();
        return null;
    }

    @Override
    public MBTI getMbtiById(Long id) {
        Optional<MBTI> mbtiObject = mbtiRepository.findById(id);

        if(mbtiObject.isPresent()) return mbtiObject.get();
        return null;
    }

    /* 각 항목 별 점수로 MBTI를 계산하는 메소드 */
    @Override
    public MBTI calMbti(MbtiCalPostReq mbtiCalPostReq) {

        Long scoreI = mbtiCalPostReq.getScoreI();
        Long scoreE = mbtiCalPostReq.getScoreE();
        Long scoreS = mbtiCalPostReq.getScoreS();
        Long scoreN = mbtiCalPostReq.getScoreN();
        Long scoreF = mbtiCalPostReq.getScoreF();
        Long scoreT = mbtiCalPostReq.getScoreT();
        Long scoreP = mbtiCalPostReq.getScoreP();
        Long scoreJ = mbtiCalPostReq.getScoreJ();
        
        StringBuilder mbti = new StringBuilder();

        mbti.append(scoreE>=scoreI ? "E" : "I");
        mbti.append(scoreS>=scoreN ? "S" : "N");
        mbti.append(scoreF>=scoreT ? "F" : "T");
        mbti.append(scoreP>=scoreJ ? "P" : "J");

        System.out.println("calMbti : " + mbti);

        return getMbtiByName(mbti.toString());
    }
}
