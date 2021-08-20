package com.ssafy.api.service;

import com.ssafy.api.request.AdoptFormReq;
import com.ssafy.api.request.StatusUpdatePutReq;
import com.ssafy.api.response.AdoptFormData;
import com.ssafy.api.response.AdoptFormGetRes;
import com.ssafy.db.entity.auth.CounselingHistory;
import com.ssafy.db.entity.auth.UserProfile;
import com.ssafy.db.entity.board.DogInformation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdoptService {



    CounselingHistory insertAdoptForm(String userId, AdoptFormReq adoptFormReq); //입양신청서 폼 제출

    CounselingHistory updateStatus(Long id, StatusUpdatePutReq statusUpdatePutReq);

    UserProfile findByUserId(String userId);

    AdoptFormData readAdoptForm(Long formId);

    CounselingHistory canAdoptForm(String userId, Long boardId); //입양신청서 작성한 적 있는지 체크 (있으면 해당 신청서의 Id, 없으면 -1 반환)

    void deleteCounselingAndChatRoom(Long counselingId); // 상담내역을 삭제할 때 채팅방도 함께 삭제하는 로직
}
