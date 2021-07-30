package com.ssafy.api.service;


import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.db.entity.auth.User;

import java.util.HashMap;

public interface UserService {
    User createUser(String access_Token, String refresh_Token, HashMap<String, Object> userInfo);
    User getUserById(String Id);
    boolean deleteUser(String id);
}
