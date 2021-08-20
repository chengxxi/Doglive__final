package com.ssafy.common.auth;

import com.ssafy.db.entity.auth.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class KakaoUserDetails implements UserDetails {
    @Autowired
    UserProfile userProfile;
    boolean accountNonExpired;
    boolean accountNonLocked;
    boolean credentialNonExpired;
    boolean enabled = false;
    List<GrantedAuthority> roles = new ArrayList<>();

    public KakaoUserDetails(UserProfile userProfile){
        super();
        this.userProfile = userProfile;
    }

    public UserProfile getUserProfile(){
        return this.userProfile;
    }
    @Override
    public String getUsername(){
        return this.userProfile.getUserId().getId();
    }
    @Override
    public String getPassword() {
        return null;
    }
    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }
    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialNonExpired;
    }
    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }



    public void setAuthorities(List<GrantedAuthority> roles) {
        this.roles = roles;
    }

}
