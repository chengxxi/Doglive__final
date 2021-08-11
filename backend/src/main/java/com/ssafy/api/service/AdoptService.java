package com.ssafy.api.service;

import com.ssafy.api.request.AdoptFormReq;
import com.ssafy.api.request.StatusUpdatePutReq;
import com.ssafy.db.entity.auth.CounselingHistory;
import com.ssafy.db.entity.auth.UserProfile;
import com.ssafy.db.entity.board.DogInformation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdoptService {

    Page<DogInformation> getAdoptBoardListInit(Pageable pageable); //전체 입양/임보 공고 리스트 불러오기

    Page<DogInformation> filterAdoptBoardList(Pageable pageable, Long boardType, Long weight, Long age, Long gender, String searchWord); //필터링 결과 리스트 불러오기



    CounselingHistory insertAdoptForm(String userId, AdoptFormReq adoptFormReq); //입양신청서 폼 제출

    CounselingHistory updateStatus(Long id, StatusUpdatePutReq statusUpdatePutReq);

    UserProfile findByUserId(String userId);

    boolean canAdoptForm(String userId, Long boardId); //입양신청서 작성한 적 있는지 체크
}
