package com.ssafy.api.service;


import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.db.entity.auth.User;

public interface UserService {
    User createUser(UserRegisterPostReq userRegisterInfo);
    User getUserById(String Id);
    boolean deleteUser(String id);
}
