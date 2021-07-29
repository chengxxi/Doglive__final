package com.ssafy.api.service;

import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.db.entity.auth.User;
import com.ssafy.db.entity.auth.UserProfile;
import com.ssafy.db.repository.auth.UserRepository;
import com.ssafy.db.repository.auth.UserRepositorySupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRepositorySupport userRepositorySupport;


    @Override
    public User createUser(UserRegisterPostReq userRegisterInfo) {
        User user = new User();
        UserProfile profile = new UserProfile();
        user.setId(userRegisterInfo.getId());

        return userRepository.save(user);
    }

    @Override
    public User getUserById(String id) {
        // 디비에 유저 정보 조회 (userId 를 통한 조회).
        User user = userRepositorySupport.findUserById(id).get();
        return user;
    }



}
