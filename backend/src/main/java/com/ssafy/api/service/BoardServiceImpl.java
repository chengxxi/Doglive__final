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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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

    @Autowired
    GugunRepository gugunRepository;

    @Autowired
    SidoRepository sidoRepository;

    @Autowired
    DogTypeRepository dogTypeRepository;


    /* 입양임보실종보호 게시물 필터링 및 페이지네이션해서 가져오기 */
    @Override
    public Page<DogInformation> filterBoardList(Pageable pageable, Long boardType, Long weight, Long age, Long gender, String searchWord) {

        Specification<DogInformation> spec = Specification.where(DogInformationSpecification.likeDogName(searchWord));
        spec = spec.or(DogInformationSpecification.likeTitle(searchWord));


        if(age!=null){
            spec = spec.and(DogInformationSpecification.eqAge(codeRepository.findById(age).get()));
        }
        if(gender!=null){
            spec = spec.and(DogInformationSpecification.eqGender(codeRepository.findById(gender).get()));
        }
        if(weight!=null){
            spec = spec.and(DogInformationSpecification.eqWeight(codeRepository.findById(weight).get()));
        }
        if(boardType!=null){
            spec = spec.and(DogInformationSpecification.eqBoardType(boardCategoryRepository.findById(boardType).get()));
        }else{

                spec = spec.and( DogInformationSpecification.inType(
                        boardCategoryRepository.findById(Long.parseLong("1")).get(),
                        boardCategoryRepository.findById(Long.parseLong("2")).get()));
                //adopt 페이지 게시물이면 1,2번 공고가 default로 출력되도록
        }

        return dogInformationRepository.
                findAll(
                        spec,
                        pageable);

    }

    @Override
    public Page<DogInformation> filterFindBoardList(Pageable pageable, Long boardType, Long sido, Long color, Long dogType, String searchWord) {


        Specification<DogInformation> spec = Specification.where(DogInformationSpecification.likeAddress(searchWord));
        spec = spec.or(DogInformationSpecification.likeTitle(searchWord));

        //제목, 상세주소 검색

        if(color!=null){
            spec = spec.and(DogInformationSpecification.eqColor(codeRepository.findById(color).get()));
        }
        if(sido!=null){
            spec = spec.and(DogInformationSpecification.eqSido(sidoRepository.findById(sido).get()));
        }
        if(dogType!=null){
            spec = spec.and(DogInformationSpecification.eqDogType(dogTypeRepository.findById(dogType).get()));
        }
        if(boardType!=null){
            spec = spec.and(DogInformationSpecification.eqBoardType(boardCategoryRepository.findById(boardType).get()));
        }else{

            spec = spec.and( DogInformationSpecification.inType(
                    boardCategoryRepository.findById(Long.parseLong("3")).get(),
                    boardCategoryRepository.findById(Long.parseLong("4")).get()));
            //find 페이지 게시물이면 1,2번 공고가 default로 출력되도록
        }

        return dogInformationRepository.
                findAll(
                        spec,
                        pageable);

    }


    /* 유기동물 관련 게시물 작성하기 */
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
        dogInformation.setDogName(boardRegisterPostReq.getDogName());


        System.out.println();
        Code gender = getCode(boardRegisterPostReq.getGender());
        if(gender!=null) dogInformation.setGender(gender);

        Code colorType = getCode(boardRegisterPostReq.getColorType());
        if(colorType!=null) dogInformation.setColorType(colorType);

        Optional<DogType> dogType = dogTypeRepository.findById(boardRegisterPostReq.getDogType());
        if(dogType.isPresent()) {
            if(dogType.get()!=null){
                dogInformation.setDogType(dogType.get());
            }
        }

        Code weight = getCode(boardRegisterPostReq.getWeight());
        if(weight!=null) dogInformation.setWeight(weight);


        Code age= getCode(boardRegisterPostReq.getAge());
        if(age!=null) dogInformation.setAge(age);

        dogInformation.setNeutralization(boardRegisterPostReq.isNeutralization());


        dogInformationRepository.save(dogInformation);


        return board;
    }

    /* 유기동물 관련 게시물 삭제하기 */
    @Override
    public boolean deleteBoard(Long boardId) {

        Board tgtBoard = getBoardByBoardId(boardId); //지울 대상

        if(tgtBoard!=null){

            DogInformation tgtDogInformation = getDogInformationByBoard(tgtBoard); //dogInformation도 삭제


            deleteAllBoardCommentsByBoard(tgtBoard);
            deleteAllBoardCommentsByBoard(tgtBoard);
            if(tgtDogInformation!=null) dogInformationRepository.delete(tgtDogInformation);

            boardRepository.delete(tgtBoard);
        }

        return false;
    }

    /* 유기동물 관련 게시물 수정하기 */
    @Override
    public Board updateBoard(Long boardId, BoardRegisterPostReq boardRegisterPostReq) {


        Board board = getBoardByBoardId(boardId); //수정할 Board 찾기
        DogInformation dogInformation = getDogInformationByBoard(board); //수정할 dogInformation 찾기


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
        dogInformation.setDogName(boardRegisterPostReq.getDogName());
        dogInformation.setDescription(boardRegisterPostReq.getDescription());
        dogInformation.setMbti(boardRegisterPostReq.getMbti());
        dogInformation.setAddress(boardRegisterPostReq.getAddress());
        Code gender = getCode(boardRegisterPostReq.getGender());
        if(gender!=null) dogInformation.setGender(gender);

        Code colorType = getCode(boardRegisterPostReq.getColorType());
        if(colorType!=null) dogInformation.setColorType(colorType);

        Optional<DogType> dogType = dogTypeRepository.findById(boardRegisterPostReq.getDogType());
        if(dogType.isPresent()) {
            if(dogType.get()!=null){
                dogInformation.setDogType(dogType.get());
            }
        }

        Code weight = getCode(boardRegisterPostReq.getWeight());
        if(weight!=null) dogInformation.setWeight(weight);

        Code age= getCode(boardRegisterPostReq.getAge());
        if(age!=null) dogInformation.setAge(age);

        dogInformation.setNeutralization(boardRegisterPostReq.isNeutralization());

        dogInformationRepository.save(dogInformation);


        return board;
    }

    /* BoardId로 Board 찾기 */
    @Override
    public Board getBoardByBoardId(Long id) {

        Optional<Board> board = boardRepository.findById(id);

        if(board.isPresent()) return board.get();
        return null;

    }

    /* Board로 BoardComment 찾기 */
    @Override
    public List<BoardComment> getBoardCommentsByBoard(Board board) {

        Optional<List<BoardComment>> boardCommentList = boardCommentRepository.findBoardCommentsByBoardId(board);

        if(boardCommentList.isPresent()) return boardCommentList.get();
        return null;
    }

    /* Board로 BoardImage 찾기 */
    @Override
    public List<BoardImage> getBoardImagesByBoard(Board board) {

        Optional<List<BoardImage>> boardImageList = boardImageRepository.findBoardImagesByBoardId(board);

        if(boardImageList.isPresent()) return boardImageList.get();
        return null;
    }

    /* userId에 따라 BoardList 출력 */
    @Override
    public List<Board> getBoardListByUserId(String id) {
        Optional<List<Board>> boardList = boardRepository.findBoardsByUserId(id);
        if(boardList.isPresent()){
            return boardList.get();
        }
        return null;
    }

    /* Board로 DogInformtaion 찾기 */
    @Override
    public DogInformation getDogInformationByBoard(Board board) {

        Optional<DogInformation> dogInformation= dogInformationRepository.findDogInformationByBoardId(board);

        if(dogInformation.isPresent()) return dogInformation.get();
        return null;
    }

    /* Board로 BoardImage 전부 지우기 */
    @Override
    public void deleteAllBoardImagesByBoard(Board board) {

        List<BoardComment> tgtBoardComment = getBoardCommentsByBoard(board);



        if(tgtBoardComment!=null) {
            for(BoardComment tgt : tgtBoardComment) boardCommentRepository.delete(tgt);
        }

    }

    /* Board로 BoardComment 전부 지우기 */
    @Override
    public void deleteAllBoardCommentsByBoard(Board board) {

        List<BoardImage> tgtBoardImage = getBoardImagesByBoard(board);

        if(tgtBoardImage!=null) {

            for(BoardImage tgt : tgtBoardImage) boardImageRepository.delete(tgt);
        }

    }

    /* 공통코드 찾기 */
    @Override
    public Code getCode(Long id) {
        Optional<Code> code = codeRepository.findById(id);
        if(code.isPresent()) return code.get();
        return null;
    }

    /* 북마크 생성하기 */
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


    /* 북마크 지우기 */
    @Override
    public Bookmark deleteBookmark(BookmarkReq bookmarkReq) {

        Bookmark bookmark = getBookmark(bookmarkReq);
        if(bookmark!=null) bookmarkRepository.delete(bookmark);

        return null;
    }


    /* 북마크 존재하면 찾기 */
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

    @Override
    public List<Gugun> getGugunListBySido(Long sido) {

        Optional<List<Gugun>> gugunList = gugunRepository.findGugunsBySidoCode(sido);
        if(gugunList.isPresent()){
            return gugunList.get();
        }
        return null;
    }


    @Override
    public List<Sido> getSidoList() {
        return sidoRepository.findAll();
    }

    @Override
    public List<DogType> getDogTypeList() {
        return dogTypeRepository.findAll();
    }


}
