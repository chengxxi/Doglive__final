package com.ssafy.api.service;


import com.ssafy.api.request.UserUpdatePutReq;
import com.ssafy.db.entity.auth.Bookmark;
import com.ssafy.db.entity.auth.CounselingHistory;
import com.ssafy.db.entity.auth.User;
import com.ssafy.db.entity.auth.UserProfile;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(String access_Token, String refresh_Token, HashMap<String, Object> userInfo);
    User getUserById(String Id);
//    UserProfile updateUserProfile(String id, UserUpdatePutReq userUpdatePutReq, MultipartFile multipartFile);
    UserProfile updateUserProfile(String id, UserUpdatePutReq userUpdatePutReq);
    UserProfile getUserProfile(String id);
    boolean deleteUser(String id);
    String getUserName(String id);
    List<Bookmark> getBookmarkList(String id);
    List<CounselingHistory> getCounselingResult(String id);
    List<CounselingHistory> getApplicantList(String id);
}
