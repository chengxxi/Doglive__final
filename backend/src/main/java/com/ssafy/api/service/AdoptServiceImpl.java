package com.ssafy.api.service;


import com.ssafy.api.request.BoardRegisterPostReq;
import com.ssafy.db.entity.board.Board;
import com.ssafy.db.entity.board.BoardComment;
import com.ssafy.db.entity.board.BoardImage;
import com.ssafy.db.entity.board.DogInformation;
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

    @Autowired
    BoardCommentRepository boardCommentRepository;

    @Autowired
    BoardCommentRepositorySupport boardCommentRepositorySupport;

    @Autowired
    BoardImageRepository boardImageRepository;

    @Autowired
    BoardImageRepositorySupport boardImageRepositorySupport;

    @Autowired
    DogInformationRepository dogInformationRepository;

    @Autowired
    DogInformationRepositorySupport dogInformationRepositorySupport;



    @Override
    public Board registerAdoptBoard(BoardRegisterPostReq boardRegisterPostReq) {

        Board board = new Board();

        DogInformation dogInformation = new DogInformation();

        board.setType(boardRegisterPostReq.getBoardType());
        board.setTitle(boardRegisterPostReq.getTitle());
        board.setThumbnailUrl(boardRegisterPostReq.getThumbnailUrl());
        board.setUserId(boardRegisterPostReq.getUserId());


        boardRepository.save(board);


        if(!boardRegisterPostReq.getFilePath().isEmpty()){

            for(String file : boardRegisterPostReq.getFilePath()){
                BoardImage boardImage = new BoardImage();


                boardImage.setBoardId(board);
                boardImage.setFilePath(file);

                boardImageRepository.save(boardImage);
            }
        }

        dogInformation.setDescription(boardRegisterPostReq.getDescription());
        dogInformation.setMbti(boardRegisterPostReq.getMbti());
        dogInformation.setGender(boardRegisterPostReq.getGender());
        dogInformation.setAddress(boardRegisterPostReq.getAddress());
        dogInformation.setBoardId(board);
        dogInformation.setColorType(boardRegisterPostReq.getColorType());
        dogInformation.setHairType(boardRegisterPostReq.getHairType());
        dogInformation.setWeight(boardRegisterPostReq.getWeight());
        dogInformation.setNeutralization(boardRegisterPostReq.getNeutralization());

        dogInformationRepository.save(dogInformation);


        return board;
    }

    @Override
    public Board getAdoptBoard() {
        return null;
    }

    @Override
    public boolean deleteAdoptBoard(Long boardId) {

        Board tgtBoard = getBoardByBoardId(boardId); //지울 대상

        if(tgtBoard!=null){

            BoardComment tgtBoardComment = getBoardCommentByBoard(tgtBoard);
            BoardImage tgtBoardImage = getBoardImageByBoard(tgtBoard);
            DogInformation tgtDogInformation = getDogInformationByBoard(tgtBoard);

            if(tgtBoard!=null) boardCommentRepository.delete(tgtBoardComment);
            if(tgtBoardImage!=null) boardImageRepository.delete(tgtBoardImage);
            if(tgtDogInformation!=null) dogInformationRepository.delete(tgtDogInformation);

            boardRepository.delete(tgtBoard);
        }

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

    @Override
    public Board getBoardByBoardId(Long id) {

        Optional<Board> board = boardRepository.findById(id);

        if(board.isPresent()) return board.get();
        return null;

    }

    @Override
    public BoardComment getBoardCommentByBoard(Board board) {

        Optional<BoardComment> boardComment = boardCommentRepositorySupport.findBoardCommentByBoardId(board);

        if(boardComment.isPresent()) return boardComment.get();
        return null;
    }

    @Override
    public BoardImage getBoardImageByBoard(Board board) {

        Optional<BoardImage> boardImage = boardImageRepositorySupport.findBoardImageByBoardId(board);

        if(boardImage.isPresent()) return boardImage.get();
        return null;
    }

    @Override
    public DogInformation getDogInformationByBoard(Board board) {

        Optional<DogInformation> dogInformation= dogInformationRepositorySupport.findDogInformationByBoardId(board);

        if(dogInformation.isPresent()) return dogInformation.get();
        return null;
    }


}
