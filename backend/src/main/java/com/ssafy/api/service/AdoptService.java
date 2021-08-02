package com.ssafy.api.service;

import com.ssafy.api.request.BoardRegisterPostReq;

import com.ssafy.db.entity.board.Board;
import com.ssafy.db.entity.board.BoardComment;
import com.ssafy.db.entity.board.BoardImage;
import com.ssafy.db.entity.board.DogInformation;

import java.util.List;

public interface AdoptService {

    List<Board> getAdoptBoardList();
}
