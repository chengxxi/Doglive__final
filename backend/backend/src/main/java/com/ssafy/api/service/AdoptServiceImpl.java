package com.ssafy.api.service;


import com.google.gson.JsonParser;
import com.ssafy.api.request.AdoptFormReq;

import com.ssafy.api.request.StatusUpdatePutReq;

import com.ssafy.db.entity.auth.CounselingHistory;
import com.ssafy.db.entity.auth.User;
import com.ssafy.db.entity.auth.UserProfile;
import com.ssafy.db.entity.board.DogInformation;

import com.ssafy.db.repository.auth.CounselingHistoryRepository;
import com.ssafy.db.repository.auth.UserProfileRepository;
import com.ssafy.db.repository.auth.UserRepository;
import com.ssafy.db.repository.board.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("adoptService")
public class AdoptServiceImpl implements AdoptService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserProfileRepository userProfileRepository;

    @Autowired
    CounselingHistoryRepository counselingHistoryRepository;

    @Autowired
    BoardRepository boardRepository;

    @Autowired

    DogInformationRepositorySupport dogInformationRepositorySupport;

    @Autowired
    DogInformationRepository dogInformationRepository;

    @Autowired
    BoardCategoryRepository boardCategoryRepository;

    @Autowired
    CodeRepository codeRepository;

    /* 입양신청서 작성하기 */
    @Override
    public CounselingHistory insertAdoptForm(String userId, AdoptFormReq adoptFormReq) {

        CounselingHistory counselingHistory = new CounselingHistory();

        JsonParser parser = new JsonParser();
        System.out.println(adoptFormReq.getContent());

        UserProfile userProfile = findByUserId(userId);
        if(userProfile!=null) {
            counselingHistory.setApplicantId(userProfile);
        }

        Long boardId = adoptFormReq.getBoardId();
        counselingHistory.setBoardId(boardId);
        counselingHistory.setBoardType(adoptFormReq.getBoardType());
        counselingHistory.setDogName(adoptFormReq.getDogName());
        counselingHistory.setContent(adoptFormReq.getContent().toString());
        counselingHistory.setBoardTitle(adoptFormReq.getBoardTitle());
        counselingHistory.setResult("대기");
        counselingHistory.setWriter(boardRepository.findById(boardId).get().getUserId());

        counselingHistoryRepository.save(counselingHistory);


        return counselingHistory;
    }


    /* 입양 신청 결과 수정 */
    @Override
    public CounselingHistory updateStatus(Long id, StatusUpdatePutReq statusUpdatePutReq) {
        Optional<CounselingHistory> counselingHistory = counselingHistoryRepository.findCounselingHistoryById(id);
        System.out.println("89: " + counselingHistory.get());
        if (counselingHistory.isPresent()) {
            counselingHistory.get().setResult(statusUpdatePutReq.getResult());
            counselingHistoryRepository.save(counselingHistory.get());

            return counselingHistory.get();
        }
        return null;
    }

    @Override
    public UserProfile findByUserId(String userId) {
        Optional<User> user = userRepository.findUserById(userId);
        if(user.isPresent()) {
            Optional<UserProfile> userProfile = userProfileRepository.findByUserId(user.get());
            if (userProfile.isPresent()) {
                return userProfile.get();
            }
        }
        return null;
    }

    @Override
    public boolean canAdoptForm(String userId, Long boardId) {

        UserProfile userProfile = findByUserId(userId);
        if(userProfile!=null) {
            Optional<CounselingHistory> counselingHistories =
                    counselingHistoryRepository.
                            findCounselingHistoryByApplicantIdAndBoardId(userProfile, boardId);

            if(counselingHistories.isPresent()){
                 return false;
            }
        }

        return true;

    }


}
