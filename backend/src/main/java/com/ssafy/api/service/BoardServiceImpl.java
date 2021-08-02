package com.ssafy.api.service;

import com.ssafy.api.request.BoardRegisterPostReq;
import com.ssafy.api.request.BookmarkReq;
import com.ssafy.db.entity.auth.Bookmark;
import com.ssafy.db.entity.auth.User;
import com.ssafy.db.entity.auth.UserProfile;
import com.ssafy.db.entity.board.*;
import com.ssafy.db.repository.auth.BookmarkRepository;
import com.ssafy.db.repository.auth.CounselingHistoryRepository;
import com.ssafy.db.repository.auth.UserProfileRepository;
import com.ssafy.db.repository.auth.UserRepository;
import com.ssafy.db.repository.board.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service("boardService")
public class BoardServiceImpl implements  BoardService{


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

    @Autowired
    BoardCategoryRepository boardCategoryRepository;

    @Autowired
    CodeRepository codeRepository;


    @Autowired
    UserRepository userRepository;


    @Autowired
    UserProfileRepository userProfileRepository;

    @Autowired
    BookmarkRepository bookmarkRepository;

    @Autowired
    CounselingHistoryRepository counselingHistoryRepository;


    @Override
    public Board registerBoard(BoardRegisterPostReq boardRegisterPostReq) {

        Board board = new Board();

        DogInformation dogInformation = new DogInformation();
        Optional<BoardCategory> boardCategory = boardCategoryRepository.findById(boardRegisterPostReq.getBoardType());
        if(boardCategory.isPresent()){
            board.setType(boardCategory.get());
        }

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

        dogInformation.setBoardId(board);
        dogInformation.setDescription(boardRegisterPostReq.getDescription());
        dogInformation.setMbti(boardRegisterPostReq.getMbti());
        dogInformation.setAddress(boardRegisterPostReq.getAddress());

        Code gender = getCode(boardRegisterPostReq.getGender());
        if(gender!=null) dogInformation.setGender(gender);

        Code colorType = getCode(boardRegisterPostReq.getColorType());
        if(colorType!=null) dogInformation.setColorType(colorType);

        Code hairType = getCode(boardRegisterPostReq.getHairType());
        if(hairType!=null) dogInformation.setHairType(hairType);

        Code weight = getCode(boardRegisterPostReq.getWeight());
        if(weight!=null) dogInformation.setWeight(weight);

        Code neutralization = getCode(boardRegisterPostReq.getNeutralization());
        if(neutralization!=null) dogInformation.setNeutralization(neutralization);

        dogInformationRepository.save(dogInformation);


        return board;
    }

    @Override
    public boolean deleteBoard(Long boardId) {

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
    public Board updateBoard(Long boardId, BoardRegisterPostReq boardRegisterPostReq) {


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

        Optional<BoardCategory> boardCategory = boardCategoryRepository.findById(boardRegisterPostReq.getBoardType());
        if(boardCategory.isPresent()){
            board.setType(boardCategory.get());
        }
        board.setTitle(boardRegisterPostReq.getTitle());
        board.setThumbnailUrl(boardRegisterPostReq.getThumbnailUrl());
        board.setUserId(boardRegisterPostReq.getUserId());
        boardRepository.save(board);


        dogInformation.setBoardId(board);
        dogInformation.setDescription(boardRegisterPostReq.getDescription());
        dogInformation.setMbti(boardRegisterPostReq.getMbti());
        dogInformation.setAddress(boardRegisterPostReq.getAddress());

        Code gender = getCode(boardRegisterPostReq.getGender());
        if(gender!=null) dogInformation.setGender(gender);

        Code colorType = getCode(boardRegisterPostReq.getColorType());
        if(colorType!=null) dogInformation.setColorType(colorType);

        Code hairType = getCode(boardRegisterPostReq.getHairType());
        if(hairType!=null) dogInformation.setHairType(hairType);

        Code weight = getCode(boardRegisterPostReq.getWeight());
        if(weight!=null) dogInformation.setWeight(weight);

        Code neutralization = getCode(boardRegisterPostReq.getNeutralization());
        if(neutralization!=null) dogInformation.setNeutralization(neutralization);

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

    @Override
    public Code getCode(Long id) {
        Optional<Code> code = codeRepository.findById(id);
        if(code.isPresent()) return code.get();
        return null;
    }

    @Override
    public Bookmark insertBookmark(BookmarkReq bookmarkReq) {

        Bookmark bookmark = new Bookmark();

        String userId = bookmarkReq.getUserId();
        Long boardId = bookmarkReq.getBoardId();


        Optional<User> user = userRepository.findUserById(userId);
        if(user.isPresent()) {
            Optional<UserProfile> userProfile = userProfileRepository.findByUserId(user.get());
            if(userProfile.isPresent()) {
                bookmark.setUserId(userProfile.get());
            }
        }

        bookmark.setBoardId(boardId);


        bookmarkRepository.save(bookmark);

        return bookmark;
    }

    @Override
    public Bookmark deleteBookmark(BookmarkReq bookmarkReq) {

        Bookmark bookmark = getBookmark(bookmarkReq);
        if(bookmark!=null) bookmarkRepository.delete(bookmark);

        return null;
    }

    @Override
    public Bookmark getBookmark(BookmarkReq bookmarkReq) {

        String userId = bookmarkReq.getUserId();
        Long boardId = bookmarkReq.getBoardId();

        Optional<User> user = userRepository.findUserById(userId);
        if(user.isPresent()) {
            Optional<UserProfile> userProfile = userProfileRepository.findByUserId(user.get());
            if(userProfile.isPresent()) {
                Optional<Bookmark> bookmark = bookmarkRepository.findBookmarkByBoardIdAndUserId(boardId, userProfile.get());
                if(bookmark.isPresent()) return bookmark.get();
            }
        }

        return null;
    }


}
