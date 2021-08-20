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
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Slf4j
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

    @Autowired
    S3Uploader s3Uploader;


    /* 입양임보 게시물 필터링 및 페이지네이션해서 가져오기 */
    @Override
    public Page<DogInformation> filterBoardList(Pageable pageable, Long boardType, Long weight, Long age, Long gender, String searchWord) {


        if(boardType==null&&weight==null&&age==null&&gender==null&&(searchWord==null||searchWord.equals(""))){
            Specification<DogInformation> spec = Specification.where( DogInformationSpecification.inType(
                    boardCategoryRepository.findById(Long.parseLong("1")).get(),
                    boardCategoryRepository.findById(Long.parseLong("2")).get()));

            return dogInformationRepository.
                    findAll(
                            spec,
                            pageable);
        }
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


    /* 실종보호 게시물 필터링 및 페이지네이션해서 가져오기 */
    @Override
    public Page<DogInformation> filterFindBoardList(Pageable pageable, Long boardType, Long sido, Long color, Long dogType, String searchWord) {

        if(boardType==null&&sido==null&&color==null&&dogType==null&&(searchWord==null||searchWord.equals(""))){
            Specification<DogInformation> spec = Specification.where( DogInformationSpecification.inType(
                    boardCategoryRepository.findById(Long.parseLong("3")).get(),
                    boardCategoryRepository.findById(Long.parseLong("4")).get()));

            return dogInformationRepository.
                    findAll(
                            spec,
                            pageable);
        }
        Specification<DogInformation> spec = Specification.where(DogInformationSpecification.likeAddress(searchWord));
        spec = spec.or(DogInformationSpecification.likeSido(searchWord));
        spec = spec.or(DogInformationSpecification.likeGugun(searchWord));
        spec = spec.or(DogInformationSpecification.likeDogType(searchWord));

        //품종종, 상세주소 색
        if(searchWord!=null){
            spec = spec.and(DogInformationSpecification.likeAddress(searchWord));
            spec = spec.or(DogInformationSpecification.likeSido(searchWord));
            spec = spec.or(DogInformationSpecification.likeGugun(searchWord));
            spec = spec.or(DogInformationSpecification.likeDogType(searchWord));
        }
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
        }if(boardType==null){

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

    @Override
    public List<DogInformation> getRecentAdoptBoard() {
        Optional<List<DogInformation>> res = dogInformationRepository.findRecentAdoptBoard();
        if(res.isPresent()){
            return res.get();
        }
        return null;
    }

    @Override
    public List<DogInformation> getRecentFindBoard() {
        Optional<List<DogInformation>> res = dogInformationRepository.findRecentFindBoard();
        if(res.isPresent()){
            return res.get();
        }
        return null;
    }


    /* 유기동물 관련 게시물 작성하기 */
    @Override

    public Board registerBoard(BoardRegisterPostReq boardRegisterPostReq) throws IOException {


        String thumbnailUrl = "";

        if(boardRegisterPostReq.getFileList()!=null){
            //썸네일 저장
            thumbnailUrl = s3Uploader.upload(boardRegisterPostReq.getFileList().get(0), "static");
        }



        Board board = Board.builder()
                .title(boardRegisterPostReq.getTitle())
                .userId(boardRegisterPostReq.getUserId())
                .thumbnailUrl("https://"+S3Uploader.CLOUD_FRONT_DOMAIN_NAME+"/"+thumbnailUrl)
                .build();

        Optional<BoardCategory> boardCategory = boardCategoryRepository.findById(Long.parseLong(boardRegisterPostReq.getBoardType()));
        if(boardCategory.isPresent()){
            board.addBoardCategory(boardCategory.get());
        }
        boardRepository.save(board);

        //이미지 저장
        DogInformation dogInformation = new DogInformation();


        for(MultipartFile file : boardRegisterPostReq.getFileList()){
            String saveUrl = s3Uploader.upload(file, "static");
            BoardImage image = BoardImage
                    .builder()
                    .filename(saveUrl)
                    .imgFullPath("https://"+S3Uploader.CLOUD_FRONT_DOMAIN_NAME+"/"+saveUrl)
                    .build();
            image.addBoard(board);
            boardImageRepository.save(image);
        }


        dogInformation.setBoardId(board);
        dogInformation.setDescription(boardRegisterPostReq.getDescription());
        if(boardRegisterPostReq.getMbti()!=null){
            dogInformation.setMbti(boardRegisterPostReq.getMbti());
        }
        if(boardRegisterPostReq.getAddress()!=null) {
            dogInformation.setAddress(boardRegisterPostReq.getAddress());                   //세부주소 저장
        }
        if(boardRegisterPostReq.getDogName()!=null){
            dogInformation.setDogName(boardRegisterPostReq.getDogName());                   //강아지 이름 저장
        }

        dogInformation.setGugun(gugunRepository.findById(Long.parseLong(boardRegisterPostReq.getGugun())).get()); //구군 코드 저장


        Code gender = getCode(Long.parseLong(boardRegisterPostReq.getGender()));
        if(gender!=null) dogInformation.setGender(gender);

        Code colorType = getCode(Long.parseLong(boardRegisterPostReq.getColorType()));
        if(colorType!=null) dogInformation.setColorType(colorType);

        Optional<DogType> dogType = dogTypeRepository.findById(Long.parseLong(boardRegisterPostReq.getDogType()));
        if(dogType.isPresent()) {
            if(dogType.get()!=null){
                dogInformation.setDogType(dogType.get());
            }
        }

        Code weight = getCode(Long.parseLong(boardRegisterPostReq.getWeight()));
        if(weight!=null) dogInformation.setWeight(weight);


        Code age= getCode(Long.parseLong(boardRegisterPostReq.getAge()));
        if(age!=null) dogInformation.setAge(age);



        dogInformationRepository.save(dogInformation);


        return board;
    }

    /* 유기동물 관련 게시물 삭제하기 */
    @Override
    public boolean deleteBoard(Long boardId) {

        Board tgtBoard = getBoardByBoardId(boardId); //지울 대상

        if(tgtBoard!=null){

            DogInformation tgtDogInformation = getDogInformationByBoard(tgtBoard); //dogInformation도 삭제

            deleteAllBoardImagesByBoard(tgtBoard);

            deleteAllBoardCommentsByBoard(tgtBoard);
            if(tgtDogInformation!=null) dogInformationRepository.delete(tgtDogInformation);

            boardRepository.delete(tgtBoard);
        }

        return false;
    }

    /* 유기동물 관련 게시물 수정하기 */
    @Override
    public Board updateBoard(Long boardId, BoardRegisterPostReq boardRegisterPostReq) throws IOException {


        System.out.println(boardRegisterPostReq.toString());

        Board board = getBoardByBoardId(boardId); //수정할 Board 찾기
        DogInformation dogInformation = getDogInformationByBoard(board); //수정할 dogInformation 찾기

        //boardImage 수정
        if(boardRegisterPostReq.getDelList()!=null) {
            System.out.println("========= delete List ! ");
            deleteSomeBoardImagesByUrl(boardRegisterPostReq.getDelList());
        }


        //썸네일 및 이미지  저장
        if(boardRegisterPostReq.getFileList()!=null){

            for(MultipartFile file : boardRegisterPostReq.getFileList()){
                String saveUrl = s3Uploader.upload(file, "static");
                BoardImage image = BoardImage
                        .builder()
                        .filename(saveUrl)
                        .imgFullPath("https://"+S3Uploader.CLOUD_FRONT_DOMAIN_NAME+"/"+saveUrl)
                        .build();
                image.addBoard(board);
                boardImageRepository.save(image);
            }
        }



        BoardImage boardImage = boardImageRepository.findFirstByBoardIdOrderById(board);
        board.setThumbnailUrl(boardImage.getImgFullPath());

        Optional<BoardCategory> boardCategory = boardCategoryRepository.findById(Long.parseLong(boardRegisterPostReq.getBoardType()));
        if(boardCategory.isPresent()){
            board.setType(boardCategory.get());
        }
        board.setTitle(boardRegisterPostReq.getTitle());
        boardRepository.save(board);





        dogInformation.setBoardId(board);
        dogInformation.setDescription(boardRegisterPostReq.getDescription());
        if(boardRegisterPostReq.getMbti()!=null){
            dogInformation.setMbti(boardRegisterPostReq.getMbti());
        }
        if(boardRegisterPostReq.getAddress()!=null) {
            dogInformation.setAddress(boardRegisterPostReq.getAddress());                   //세부주소 저장
        }
        if(boardRegisterPostReq.getDogName()!=null){
            dogInformation.setDogName(boardRegisterPostReq.getDogName());                   //강아지 이름 저장
        }

        dogInformation.setGugun(gugunRepository.findById(Long.parseLong(boardRegisterPostReq.getGugun())).get()); //구군 코드 저장


        Code gender = getCode(Long.parseLong(boardRegisterPostReq.getGender()));
        if(gender!=null) dogInformation.setGender(gender);

        Code colorType = getCode(Long.parseLong(boardRegisterPostReq.getColorType()));
        if(colorType!=null) dogInformation.setColorType(colorType);

        Optional<DogType> dogType = dogTypeRepository.findById(Long.parseLong(boardRegisterPostReq.getDogType()));
        if(dogType.isPresent()) {
            if(dogType.get()!=null){
                dogInformation.setDogType(dogType.get());
            }
        }

        Code weight = getCode(Long.parseLong(boardRegisterPostReq.getWeight()));
        if(weight!=null) dogInformation.setWeight(weight);


        Code age= getCode(Long.parseLong(boardRegisterPostReq.getAge()));
        if(age!=null) dogInformation.setAge(age);



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

        List<BoardImage> tgtBoardImage = getBoardImagesByBoard(board);

        if(tgtBoardImage!=null) {

            for(BoardImage tgt : tgtBoardImage) {
                boardImageRepository.delete(tgt);
                s3Uploader.delete(tgt.getFilename());
            }
        }


    }
    
    /* 파일 수정 시 삭제해야 되는 사진들 */
    @Override
    public void deleteSomeBoardImagesByUrl(List<String> delList) {
        if(delList!=null){
            for(String url : delList){
                Optional<List<BoardImage>> delImgList = boardImageRepository.findBoardImagesByImgFullPath(url);
                if(delImgList.isPresent()){
                    for(BoardImage img : delImgList.get()){
                        boardImageRepository.delete(img);
                        s3Uploader.delete(img.getFilename());
                    }
                }
            }
        }



    }

    /* Board로 BoardComment 전부 지우기 */
    @Override
    public void deleteAllBoardCommentsByBoard(Board board) {

        List<BoardComment> tgtBoardComment = getBoardCommentsByBoard(board);



        if(tgtBoardComment!=null) {
            for(BoardComment tgt : tgtBoardComment) boardCommentRepository.delete(tgt);
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

        Optional<List<Gugun>> gugunList = gugunRepository.findGugunsBySidoCode(sidoRepository.findById(sido).get());
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
