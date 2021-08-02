package com.ssafy.api.service;


import com.ssafy.db.entity.board.Board;
import com.ssafy.db.repository.board.BoardRepository;
import com.ssafy.db.repository.board.BoardRepositorySupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("findService")
public class FindServiceImpl implements FindService {
    @Autowired
    BoardRepository boardRepository;

    @Autowired
    BoardRepositorySupport boardRepositorySupport;

    @Override
    public List<Board> getFindBoardList() {
        Optional<List<Board>> boardList = boardRepository.findFindBoard();

        if(boardList.isPresent()) return boardList.get();

        return null;
    }

}
