package com.ssafy.api.service;


import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.ssafy.api.request.AdoptFormReq;
import com.ssafy.db.entity.auth.CounselingHistory;
import com.ssafy.db.entity.auth.User;
import com.ssafy.db.entity.auth.UserProfile;
import com.ssafy.db.entity.board.Board;
import com.ssafy.db.repository.auth.CounselingHistoryRepository;
import com.ssafy.db.repository.auth.UserProfileRepository;
import com.ssafy.db.repository.auth.UserRepository;
import com.ssafy.db.repository.board.*;

import io.micrometer.core.instrument.util.JsonUtils;
import org.checkerframework.checker.nullness.Opt;
import org.h2.util.json.JSONObject;
import org.h2.util.json.JSONString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
    BoardRepositorySupport boardRepositorySupport;

    /* 입양임보 게시물 전체 목록 불러오기 */
    @Override
    public List<Board> getAdoptBoardList() {
        Optional<List<Board>> boardList = boardRepository.findAdoptBoard();
        if(boardList.isPresent()) return boardList.get();

        return null;
    }

    /* 입양신청서 작성하기 */
    @Override
    public CounselingHistory insertAdoptForm(String userId, AdoptFormReq adoptFormReq) {

        CounselingHistory counselingHistory = new CounselingHistory();

        JsonParser parser = new JsonParser();
        JsonElement content = parser.parse(adoptFormReq.getContent().toString());

        Optional<User> user = userRepository.findUserById(userId);
        if(user.isPresent()) {
            Optional<UserProfile> userProfile = userProfileRepository.findByUserId(user.get());
            if (userProfile.isPresent()) {
                counselingHistory.setApplicantId(userProfile.get());
            }
        }

        counselingHistory.setBoardId(adoptFormReq.getBoardId());
        counselingHistory.setBoardType(adoptFormReq.getBoardType());
        counselingHistory.setContent(content.toString());
        counselingHistory.setResult("대기");


        counselingHistoryRepository.save(counselingHistory);


        return counselingHistory;
    }




}
