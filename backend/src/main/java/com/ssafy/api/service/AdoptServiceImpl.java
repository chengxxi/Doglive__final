package com.ssafy.api.service;


import com.ssafy.api.request.AdoptFormReq;
import com.ssafy.db.entity.auth.CounselingHistory;
import com.ssafy.db.entity.board.Board;
import com.ssafy.db.repository.board.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service("adoptService")
public class AdoptServiceImpl implements AdoptService{

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    BoardRepositorySupport boardRepositorySupport;

    @Override
    public List<Board> getAdoptBoardList() {
        Optional<List<Board>> boardList = boardRepository.findAdoptBoard();
        if(boardList.isPresent()) return boardList.get();

        return null;
    }

    @Override
    public CounselingHistory insertAdoptForm(String userId, AdoptFormReq adoptFormReq) {

        return null;
    }

}
