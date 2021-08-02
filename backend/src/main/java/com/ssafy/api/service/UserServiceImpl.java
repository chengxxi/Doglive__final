package com.ssafy.api.service;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.db.entity.auth.User;
import com.ssafy.db.entity.auth.UserProfile;
import com.ssafy.db.entity.auth.UserToken;
import com.ssafy.db.repository.auth.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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

    @Override
    public boolean deleteUser(String id) {
        System.out.println("탈퇴할 아이디: " + id);
        if(getUserById(id)!=null){
            User user = userRepositorySupport.findUserById(id).get();

            Optional<UserProfile> userProfile = userProfileRepositorySupport.findUserByUserId(user);
            if(userProfile.isPresent()) {
                userProfileRepository.delete(userProfile.get());
            }

            userRepository.delete(user);
            return true;
        }
        return false;

    }


}
