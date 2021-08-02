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
    BoardImageRepository boardImageRepository;

    @Autowired
    DogInformationRepository dogInformationRepository;



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
    public boolean deleteAdoptBoard(Long boardId) {

        Board tgtBoard = getBoardByBoardId(boardId); //지울 대상

        if(tgtBoard!=null){


            DogInformation tgtDogInformation = getDogInformationByBoard(tgtBoard);


            deleteAllBoardCommentsByBoard(tgtBoard);
            deleteAllBoardCommentsByBoard(tgtBoard);
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
    public Board updateAdoptBoard(Long boardId, BoardRegisterPostReq boardRegisterPostReq) {


        Board board = getBoardByBoardId(boardId);
        DogInformation dogInformation = getDogInformationByBoard(board);


        //boardImage 수정
        if(board!=null) {
            deleteAllBoardImagesByBoard(board);
        }

        if(!boardRegisterPostReq.getFilePath().isEmpty()){

            for(String file : boardRegisterPostReq.getFilePath()){
                BoardImage boardImage = new BoardImage();

                boardImage.setBoardId(board);
                boardImage.setFilePath(file);

                boardImageRepository.save(boardImage);
            }
        }

        //board, DogInformation 수정

        board.setType(boardRegisterPostReq.getBoardType());
        board.setTitle(boardRegisterPostReq.getTitle());
        board.setThumbnailUrl(boardRegisterPostReq.getThumbnailUrl());
        board.setUserId(boardRegisterPostReq.getUserId());
        boardRepository.save(board);


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
    public Board getBoardByBoardId(Long id) {

        Optional<Board> board = boardRepository.findById(id);

        if(board.isPresent()) return board.get();
        return null;

    }

    @Override
    public List<BoardComment> getBoardCommentsByBoard(Board board) {

        Optional<List<BoardComment>> boardCommentList = boardCommentRepository.findBoardCommentsByBoardId(board);

        if(boardCommentList.isPresent()) return boardCommentList.get();
        return null;
    }

    @Override
    public List<BoardImage> getBoardImagesByBoard(Board board) {

        Optional<List<BoardImage>> boardImageList = boardImageRepository.findBoardImagesByBoardId(board);

        if(boardImageList.isPresent()) return boardImageList.get();
        return null;
    }

    @Override
    public DogInformation getDogInformationByBoard(Board board) {

        Optional<DogInformation> dogInformation= dogInformationRepository.findDogInformationByBoardId(board);

        if(dogInformation.isPresent()) return dogInformation.get();
        return null;
    }

    @Override
    public void deleteAllBoardImagesByBoard(Board board) {

        List<BoardComment> tgtBoardComment = getBoardCommentsByBoard(board);



        if(tgtBoardComment!=null) {
            for(BoardComment tgt : tgtBoardComment) boardCommentRepository.delete(tgt);
        }

    }

    @Override
    public void deleteAllBoardCommentsByBoard(Board board) {

        List<BoardImage> tgtBoardImage = getBoardImagesByBoard(board);

        if(tgtBoardImage!=null) {

            for(BoardImage tgt : tgtBoardImage) boardImageRepository.delete(tgt);
        }

    }


}
