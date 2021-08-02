package com.ssafy.api.service;

import com.ssafy.db.entity.board.Board;

import java.util.List;

public interface AdoptService {
    Board createAdoptBoard();
    Board getAdoptBoard();
    boolean deleteAdoptBoard();
    List<Board> getAdoptBoardList();
    Board updateAdoptBoard();
}
