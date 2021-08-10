package com.ssafy.api.service;


import com.ssafy.api.response.BoardListData;
import com.ssafy.db.entity.board.Board;
import com.ssafy.db.entity.board.DogInformation;
import com.ssafy.db.entity.board.QDogInformation;
import com.ssafy.db.repository.board.BoardRepository;
import com.ssafy.db.repository.board.DogInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("findService")
public class FindServiceImpl implements FindService {
    @Autowired
    BoardRepository boardRepository;

    @Autowired
    DogInformationRepository dogInformationRepository;

    /* 실종보호 게시물 전체 목록 보기 */
    @Override
    public List<BoardListData> getFindBoardList() {
        List<BoardListData> adoptList = new ArrayList<>();

        Optional<List<Board>> boardList = boardRepository.findFindBoard();

        if(boardList.isPresent()) {
            for (Board board : boardList.get()){

                BoardListData data = new BoardListData(); //게시판 목록에서 필요한 정보들

                data.setBoardId(board.getId());
                data.setUserId(board.getUserId());
                data.setType(board.getType());
                data.setTitle(board.getTitle());
                data.setThumbnailUrl(board.getThumbnailUrl());
                data.setRegDate(board.getRegDate());


                Optional<DogInformation> dogInformation = dogInformationRepository.findDogInformationByBoardId(board);

                if(dogInformation.isPresent()){
                    data.setGender(dogInformation.get().getGender());
                    data.setDogName(dogInformation.get().getDogName());
                    data.setMbti(dogInformation.get().getMbti());
                    data.setAge(dogInformation.get().getAge());
                    data.setNeutralization(dogInformation.get().isNeutralization());
                    data.setColorType(dogInformation.get().getColorType());
                    data.setHairType(dogInformation.get().getHairType());
                    data.setWeight(dogInformation.get().getWeight());

                    //필요 정보 파싱해서 리스트에 add하기
                    adoptList.add(data);
                }
            }
        }

        return adoptList;
    }

}
