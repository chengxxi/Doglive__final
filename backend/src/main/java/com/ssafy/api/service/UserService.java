package com.ssafy.api.service;


import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.api.request.UserUpdatePutReq;
import com.ssafy.db.entity.auth.Bookmark;
import com.ssafy.db.entity.auth.User;
import com.ssafy.db.entity.auth.UserProfile;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;

public interface UserService {
    User createUser(String access_Token, String refresh_Token, HashMap<String, Object> userInfo);
    User getUserById(String Id);
    UserProfile updateUserProfile(String id, UserUpdatePutReq userUpdatePutReq, MultipartFile multipartFile);
    boolean deleteUser(String id);
    List<Bookmark> getBookmarkList(String id);
}
