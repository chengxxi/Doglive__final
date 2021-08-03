package com.ssafy.api.service;

import com.ssafy.api.request.AdoptFormReq;
import com.ssafy.db.entity.auth.CounselingHistory;
import com.ssafy.db.entity.board.Board;

import java.util.List;

public interface AdoptService {

    List<Board> getAdoptBoardList();

    CounselingHistory insertAdoptForm(String userId, AdoptFormReq adoptFormReq);

}
