package com.ssafy.api.service;

import com.ssafy.api.response.BoardListData;
import com.ssafy.db.entity.board.Board;

import java.util.List;

public interface FindService {
    List<BoardListData> getFindBoardList();

}
