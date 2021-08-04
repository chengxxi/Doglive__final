package com.ssafy.api.service;

import com.ssafy.api.request.UserUpdatePutReq;
import com.ssafy.db.entity.auth.Bookmark;
import com.ssafy.db.entity.auth.User;
import com.ssafy.db.entity.auth.UserProfile;
import com.ssafy.db.entity.auth.UserToken;
import com.ssafy.db.repository.auth.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service("userService")
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRepositorySupport userRepositorySupport;

    @Autowired
    UserProfileRepository userProfileRepository;

    @Autowired
    UserProfileRepositorySupport userProfileRepositorySupport;


    @Autowired
    UserTokenRepository userTokenRepository;

    @Autowired
    UserTokenRepositorySupport userTokenRepositorySupport;

    @Autowired
    BookmarkRepository bookmarkRepository;

    @Autowired
    BookmarkRepositorySupport bookmarkRepositorySupport;

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
        userProfile.setPhoneNumber(phoneNumber);

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
        Optional<User> user = userRepositorySupport.findUserById(id);

        if(user.isPresent()) return user.get();
        return null;

    }


    @Value("${profileImg.path}")
    private String uploadFolder;

    @Override
    public UserProfile updateUserProfile(String id, UserUpdatePutReq userUpdatePutReq, MultipartFile multipartFile) {
        User user = userRepositorySupport.findUserById(id).get();
        Optional<UserProfile> userProfile = userProfileRepositorySupport.findUserByUserId(user);

        String imageFileName = user.getId() + "_" + multipartFile.getOriginalFilename();
        Path imageFilePath = Paths.get(uploadFolder + imageFileName);

        // 파일이 업로드 되었는지 확인
        if(multipartFile.getSize() != 0){
            try{
                // 이미 프로필 사진이 있는 경우
                if(userProfile.get().getProfileImageUrl()!=null){
                    File file = new File(uploadFolder + userProfile.get().getProfileImageUrl());
                    file.delete();
                }
                Files.write(imageFilePath, multipartFile.getBytes());
                System.out.println("File Path: " + imageFilePath);
            } catch(Exception e){
                e.printStackTrace();
            }
        }

        userProfile.get().setName(userUpdatePutReq.getName());
        userProfile.get().setProfileImageUrl(imageFileName);
        userProfileRepository.save(userProfile.get());

        return userProfile.get();
    }

    @Override
    public boolean deleteUser(String id) {
        System.out.println("탈퇴할 아이디: " + id);
        if(getUserById(id)!=null){
            User user = userRepositorySupport.findUserById(id).get();
            Optional<UserProfile> userProfile = userProfileRepositorySupport.findUserByUserId(user);
            if(userProfile.isPresent()) {
                Optional<UserToken> userToken = userTokenRepository.findByUserId(user);
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

}
