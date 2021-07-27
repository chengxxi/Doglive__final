package com.ssafy.api.service;

import com.ssafy.db.entity.auth.User;

public interface UserService {
    User getUserByUserId(String kakaoId);
}
