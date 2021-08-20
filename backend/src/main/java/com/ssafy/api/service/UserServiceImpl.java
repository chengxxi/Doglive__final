package com.ssafy.api.service;


import com.ssafy.api.request.UserUpdateNoImagePutReq;
import com.ssafy.api.request.CommunityParamDto;
import com.ssafy.api.request.UserUpdatePutReq;
import com.ssafy.db.entity.auth.*;
import com.ssafy.db.entity.board.Board;
import com.ssafy.db.entity.chat.ChatRoomJoin;
import com.ssafy.db.entity.community.CommunityComment;
import com.ssafy.db.repository.auth.*;
import com.ssafy.db.repository.board.BoardImageRepository;
import com.ssafy.db.repository.board.BoardRepository;
import com.ssafy.db.repository.community.CommunityCommentRepository;
import com.ssafy.db.repository.community.CommunityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service("userService")
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserProfileRepository userProfileRepository;

    @Autowired
    UserTokenRepository userTokenRepository;

    @Autowired
    BookmarkRepository bookmarkRepository;

    @Autowired
    CounselingHistoryRepository counselingHistoryRepository;

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    BoardImageRepository boardImageRepository;

    @Autowired
    CommunityRepository communityRepository;

    @Autowired
    CommunityCommentRepository communityCommentRepository;

    @Autowired
    S3Uploader s3Uploader;

    @Autowired
    BoardService boardService;

    @Autowired
    CommunityService communityService;

    @Autowired
    ChatService chatService;

    @Override
    public User createUser(String access_Token, String refresh_Token, HashMap<String, Object> userInfo) {

        User user = new User();
        UserProfile userProfile = new UserProfile();

        String id = (String) userInfo.get("userid");
        String name = (String) userInfo.get("name");
        String profileImageUrl = (String)userInfo.get("profileImageUrl");
        String email = (String) userInfo.get("email");
        String accessToken = (String) userInfo.get("accessToken");
        String refreshToken = (String) userInfo.get("refreshToken");
        String phoneNumber = (String) userInfo.get("phoneNumber");

        System.out.println(id+" "+name+profileImageUrl+email+accessToken+refreshToken+phoneNumber);

        /* User 저장 */
        user.setId(id);
        User returnUser = userRepository.save(user);

        /* User Profile 저장 */
        userProfile.setUserId(returnUser);
        userProfile.setName(name);
        userProfile.setProfileImageUrl(profileImageUrl);
        userProfile.setEmail(email);
        userProfile.setPhoneNumber(" ");

        userProfileRepository.save(userProfile);

        /* User Token 저장 */
        UserToken userToken = new UserToken();
        userToken.setUserId(user);
        userToken.setAccessToken(accessToken);
        userToken.setRefreshToken(refreshToken);

        userTokenRepository.save(userToken);

        return returnUser;
    }

    @Override
    public User getUserById(String id) {
        // 디비에 유저 정보 조회 (userId 를 통한 조회).
        Optional<User> user = userRepository.findUserById(id);

        if(user.isPresent()) return user.get();
        return null;

    }

    @Override
    public UserProfile updateUserProfile(String id, UserUpdatePutReq userUpdatePutReq) throws IOException {
        User user = userRepository.findUserById(id).get();

        System.out.println(user + " " + userUpdatePutReq.getBirth() + " " + userUpdatePutReq.getEmail() + " " +  userUpdatePutReq.getPhoneNumber() + " " + userUpdatePutReq.getName());
        UserProfile userProfile = userProfileRepository.findByUserId(user).get();

        if(userUpdatePutReq.getFile()!=null){
            String profileImageUrl = s3Uploader.upload(userUpdatePutReq.getFile(),"static");
            userProfile.setProfileImageUrl("https://"+S3Uploader.CLOUD_FRONT_DOMAIN_NAME+"/"+profileImageUrl);
        }
        userProfile.setName(userUpdatePutReq.getName());
        userProfile.setEmail(userUpdatePutReq.getEmail());
        userProfile.setPhoneNumber(userUpdatePutReq.getPhoneNumber());
        userProfile.setBirth(userUpdatePutReq.getBirth());
        userProfileRepository.save(userProfile);
        return userProfile;
    }

    @Override
    public UserProfile updateUserNoImage(String id, UserUpdateNoImagePutReq userUpdateNoImagePutReq) {
        User user = userRepository.findUserById(id).get();
        UserProfile userProfile = userProfileRepository.findByUserId(user).get();

        userProfile.setName(userUpdateNoImagePutReq.getName());
        userProfile.setEmail(userUpdateNoImagePutReq.getEmail());
        userProfile.setPhoneNumber(userUpdateNoImagePutReq.getPhoneNumber());
        userProfile.setBirth(userUpdateNoImagePutReq.getBirth());
        userProfileRepository.save(userProfile);
        return userProfile;
    }

    @Override
    public UserProfile getUserProfile(String id) {
        Optional<User> user = userRepository.findUserById(id);
        if(user.isPresent()) {
            Optional<UserProfile> userProfile = userProfileRepository.findByUserId(user.get());
            if(userProfile.isPresent()){
                return userProfile.get();
            }
        }
        return null;

    }

    @Override
    public boolean deleteUser(String id) {
        if(getUserById(id)!=null){

            User user = userRepository.findById(id).get();
            Optional<UserProfile> userProfile = userProfileRepository.findByUserId(user);

            if(userProfile.isPresent()) {
                Optional<UserToken> userToken = userTokenRepository.findByUserId(user);

                // 상담내역 삭제
                List<CounselingHistory> counselingHistoryList = counselingHistoryRepository.findCounselingHistoriesByWriter(id).get();
                for (CounselingHistory couselingHistory: counselingHistoryList) {
                    counselingHistoryRepository.delete(couselingHistory);
                }
                // 신청 삭제
                List<CounselingHistory> applicantList = counselingHistoryRepository.findCounselingHistoriesByApplicantId(userProfile.get()).get();
                for (CounselingHistory couselinghistory: applicantList) {
                    counselingHistoryRepository.delete(couselinghistory);
                }
                // 북마크 삭제
                bookmarkRepository.deleteBookmarksByUserId(userProfile.get());

                // 보드 삭제
                List<Board> boardList = boardService.getBoardListByUserId(id);
                for (Board board: boardList) {
                    boardService.deleteBoard(board.getId());
                }
                // community 삭제
                List<CommunityParamDto> communityList =  communityService.getCommunityListByUserId(id);
                for (CommunityParamDto dto: communityList) {
                    communityService.deleteCommunityBoard(dto.getId());
                }
                // community 댓글 삭제
                List<CommunityComment> communityCommentList = communityCommentRepository.findCommunityCommentsByUserId(id).get();
                for (CommunityComment communitycomment: communityCommentList) {
                    communityCommentRepository.delete(communitycomment);
                }
                // 채팅내역 삭제
                List<ChatRoomJoin> chatRoomJoinList = chatService.getChatroomJoinListByUser(user.getId());
                for(ChatRoomJoin join : chatRoomJoinList){
                    chatService.deleteChatRoom(join.getRoomId());
                }

                if(userToken.isPresent()){
                    userTokenRepository.delete(userToken.get());
                }
                userProfileRepository.delete(userProfile.get());
            }
            userRepository.delete(user);
            return true;
        }
        return false;
    }

    @Override
    public String getUserName(String id) {
        User user = getUserById(id);
        if (user == null) return null;

        Optional<UserProfile> userProfile = userProfileRepository.findByUserId(user);
        if (userProfile.isPresent()) {
            return userProfile.get().getName();

        }

        return null;
    }

    @Override
    public List<Bookmark> getBookmarkList(String id) {
        Optional<User> user = userRepository.findUserById(id);
        if(user.isPresent()) {
            Optional<UserProfile> userProfile = userProfileRepository.findByUserId(user.get());
            if (userProfile.isPresent()) {
                Optional<List<Bookmark>> bookmarkList = bookmarkRepository.findBookmarksByUserId(userProfile.get());
                if (bookmarkList.isPresent()) {
                    return bookmarkList.get();
                }
            }

        }
        return null;
    }

    @Override
    public List<CounselingHistory> getCounselingResult(String id) {
        Optional<User> user = userRepository.findUserById(id);
        List<CounselingHistory> resultList = new ArrayList<>();
        if(user.isPresent()){
            Optional<UserProfile> userProfile = userProfileRepository.findByUserId(user.get());
            if(userProfile.isPresent()){
                List<CounselingHistory> List = counselingHistoryRepository.findCounselingHistoriesByApplicantId(userProfile.get()).get();
                for (CounselingHistory counselingHistory: List) {
                    if(counselingHistory.getBoardType().equals("입양") || counselingHistory.getBoardType().equals("임보")){
                        resultList.add(counselingHistory);
                    }
                }
            }

        }
        if(resultList!=null){
            return resultList;
        }
        return null;
    }

    @Override
    public List<CounselingHistory> getApplicantList(String id) {
        Optional<List<CounselingHistory>> applicantList = counselingHistoryRepository.findCounselingHistoriesByWriter(id);
        if(applicantList.isPresent()){
            return applicantList.get();
        }
        return null;
    }

    @Override
    public CounselingHistory getCounselingById(Long counselingId) {
        Optional<CounselingHistory> counselingHistory = counselingHistoryRepository.findCounselingHistoryById(counselingId);
        if(counselingHistory.isPresent())
            return counselingHistory.get();
        return null;
    }

    @Override
    public void deleteUserProfileImageByUrl(String delfile) {
        if(delfile!=null){
            s3Uploader.delete(delfile);
        }
    }

}
