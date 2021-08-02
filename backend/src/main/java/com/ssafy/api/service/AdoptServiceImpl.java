package com.ssafy.api.service;


import com.ssafy.db.entity.board.Board;
import com.ssafy.db.repository.board.BoardRepository;
import com.ssafy.db.repository.board.BoardRepositorySupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("adoptService")
public class AdoptServiceImpl implements AdoptService{

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    BoardRepositorySupport boardRepositorySupport;


    @Override
    public Board createAdoptBoard() {
        return null;
    }

    @Override
    public Board getAdoptBoard() {
        return null;
    }

    @Override
    public boolean deleteAdoptBoard() {
        return false;
    }

    @Override
    public List<Board> getAdoptBoardList() {
        return boardRepository.findAll();
    }

    @Override
    public Board updateAdoptBoard() {
        return null;
    }
}
