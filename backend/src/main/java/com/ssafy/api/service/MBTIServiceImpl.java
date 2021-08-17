package com.ssafy.api.service;

import com.ssafy.api.request.MbtiCalPostReq;
import com.ssafy.db.entity.board.Board;
import com.ssafy.db.entity.board.DogInformation;
import com.ssafy.db.entity.board.MBTI;
import com.ssafy.db.repository.board.BoardCategoryRepository;
import com.ssafy.db.repository.board.BoardRepository;
import com.ssafy.db.repository.board.DogInformationRepository;
import com.ssafy.db.repository.board.MBTIRepository;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("mbtiService")
public class MBTIServiceImpl implements MBTIService{

    @Autowired
    MBTIRepository mbtiRepository;

    @Autowired
    BoardCategoryRepository boardCategoryRepository;

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    DogInformationRepository dogInformationRepository;

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


    @Override
    public List<DogInformation> getSameMbtiDogBoardByName(String mbti) {

        Optional<List<DogInformation>> dogInformations = dogInformationRepository.findTop10ByMbtiOrderByIdDesc(mbti);
        if (dogInformations.isPresent()){
            if(dogInformations.get().size()!=0) return dogInformations.get();
        }

        return null;
    }


    @Override
    public List<DogInformation> getSameMbtiDogBoardById(Long id) {
       MBTI mbti = mbtiRepository.findById(id).get();
        Optional<List<DogInformation>> dogInformations = dogInformationRepository.findTop10ByMbtiOrderByIdDesc(mbti.getName());
        if (dogInformations.isPresent()){
            if(dogInformations.get().size()!=0) return dogInformations.get();
        }

        return null;
    }

    @Override
    public List<MBTI> getMbtiList() {
        return mbtiRepository.findAll();
    }


}
