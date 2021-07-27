package com.ssafy.api.service;

import com.ssafy.db.entity.auth.User;
import com.ssafy.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl{
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

//    @Override
//    public User getUserByUserId(String userId) {
//        // 디비에 유저 정보 조회 (userId 를 통한 조회).
//        User user = userRepositorySupport.findUserByUserId(userId).get();
//        return user;
//    }
}
