package com.ssafy.api.service;


import com.ssafy.api.request.BoardRegisterPostReq;
import com.ssafy.db.entity.board.Board;
import com.ssafy.db.entity.board.BoardComment;
import com.ssafy.db.entity.board.BoardImage;
import com.ssafy.db.entity.board.DogInformation;
import com.ssafy.db.repository.board.*;

import net.bytebuddy.description.NamedElement;
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
}
