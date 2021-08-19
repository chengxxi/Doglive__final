package com.ssafy.api.service;


import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.ssafy.api.request.AdoptFormReq;

import com.ssafy.api.request.StatusUpdatePutReq;

import com.ssafy.api.response.AdoptFormData;
import com.ssafy.db.entity.auth.CounselingHistory;
import com.ssafy.db.entity.auth.User;
import com.ssafy.db.entity.auth.UserProfile;
import com.ssafy.db.entity.board.DogInformation;

import com.ssafy.db.entity.chat.ChatRoom;
import com.ssafy.db.repository.auth.CounselingHistoryRepository;
import com.ssafy.db.repository.auth.UserProfileRepository;
import com.ssafy.db.repository.auth.UserRepository;
import com.ssafy.db.repository.board.*;

import org.h2.util.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
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

    @Autowired
    GugunRepository gugunRepository;

    @Autowired
    SidoRepository sidoRepository;

    @Autowired
    ChatService chatService;

    /* 입양신청서 작성하기 */
    @Override
    public CounselingHistory insertAdoptForm(String userId, AdoptFormReq adoptFormReq) {

        CounselingHistory counselingHistory = new CounselingHistory();

        System.out.println(adoptFormReq.getContent());

        UserProfile userProfile = findByUserId(userId);
        if(userProfile!=null) {
            counselingHistory.setApplicantId(userProfile);
        }

        Long boardId = adoptFormReq.getBoardId();
        counselingHistory.setBoardId(boardId);
        counselingHistory.setBoardType(adoptFormReq.getBoardType());
        counselingHistory.setBoardTitle(adoptFormReq.getBoardTitle());

        if(adoptFormReq.getBoardType().equals("입양") || adoptFormReq.getBoardType().equals("임보")){
            counselingHistory.setResult("대기");
            counselingHistory.setDogName(adoptFormReq.getDogName());
            counselingHistory.setContent(adoptFormReq.getContent().toString());
        }

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

    /* 입양 신청서 읽기 */
    @Override
    public AdoptFormData readAdoptForm(Long formId) {
        Optional<CounselingHistory> counselingHistory = counselingHistoryRepository.findById(formId);
        if(counselingHistory.isPresent()){
            CounselingHistory form = counselingHistory.get();
            String content = form.getContent();

            System.out.println(content);
            //String type의 JSON Content를 DTO에 매핑하여 front단으로 전달해야한다.

            JsonParser jsonParser = new JsonParser();
            JsonElement element = jsonParser.parse(content);



            AdoptFormData adoptFormData = new AdoptFormData(
                    element.getAsJsonObject().get("name").getAsString(),
                    element.getAsJsonObject().get("email").getAsString(),
                    element.getAsJsonObject().get("phone").getAsString(),
                    sidoRepository.findById(Long.parseLong(element.getAsJsonObject().get("sido").getAsString())).get().getName(),
                    gugunRepository.findById(Long.parseLong(element.getAsJsonObject().get("gugun").getAsString())).get().getName(),
                    element.getAsJsonObject().get("age").getAsString(),
                    element.getAsJsonObject().get("isMarried").getAsString(),
                    element.getAsJsonObject().get("gender").getAsString(),
                    form.getBoardTitle(),
                    form.getDogName(),
                    form.getBoardType(),
                    element.getAsJsonObject().get("answer1").getAsString(),
                    element.getAsJsonObject().get("answer1sub").getAsString(),
                    element.getAsJsonObject().get("answer2").getAsString(),
                    element.getAsJsonObject().get("answer2sub").getAsString(),
                    element.getAsJsonObject().get("answer3").getAsString(),
                    element.getAsJsonObject().get("answer3sub").getAsString(),
                    element.getAsJsonObject().get("answer4").getAsString(),
                    element.getAsJsonObject().get("answer5").getAsString(),
                    element.getAsJsonObject().get("answer6").getAsString(),
                    element.getAsJsonObject().get("answer7").getAsString(),
                    element.getAsJsonObject().get("answer8").getAsString(),
                    element.getAsJsonObject().get("answer9").getAsString(),
                    element.getAsJsonObject().get("answer10").getAsString()
                    );

            return adoptFormData;

        }
        return null;
    }

    /* 입양 신청 중복인지 아닌지 체크 */
    @Override
    public CounselingHistory canAdoptForm(String userId, Long boardId) {

        UserProfile userProfile = findByUserId(userId);
        if(userProfile!=null) {
            Optional<CounselingHistory> counselingHistories =
                    counselingHistoryRepository.
                            findCounselingHistoryByApplicantIdAndBoardId(userProfile, boardId);

            if(counselingHistories.isPresent()){ // 존재하면
                 return counselingHistories.get();
            }
        }
        return null;
    }

    @Override
    public void deleteCounselingAndChatRoom(Long counselingId) {

        ChatRoom chatRoom = chatService.getChatRoomInfoByCounselingId(counselingId);
        if(chatRoom != null) {
            System.out.println("채팅방 먼저 삭제 : " + chatRoom);
            chatService.deleteChatRoom(chatRoom); // 채팅방 먼저 삭제
        }
        Optional<CounselingHistory> counselingHistory = counselingHistoryRepository.findCounselingHistoryById(counselingId);
        if(counselingHistory.isPresent()){
            counselingHistoryRepository.delete(counselingHistory.get());  // 그 다음, 상담 내역 삭제
        }
    }
}
