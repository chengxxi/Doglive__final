package com.ssafy.common.auth;

import com.ssafy.api.service.UserService;
import com.ssafy.db.entity.auth.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class KakaoUserDetailService implements UserDetailsService {
    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserProfile userProfile = userService.getUserProfile(username);
        if(userProfile!=null){
            KakaoUserDetails kakaoUserDetails = new KakaoUserDetails(userProfile);
            return kakaoUserDetails;
        }
        return null;
    }
}
