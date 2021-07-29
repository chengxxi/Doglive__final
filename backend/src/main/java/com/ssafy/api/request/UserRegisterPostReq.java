package com.ssafy.api.request;

import com.ssafy.db.entity.auth.UserProfile;
import com.ssafy.db.entity.auth.UserToken;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel("UserRegisterPostResponse")
public class UserRegisterPostReq {
    @ApiModelProperty
    String id;
    @ApiModelProperty
    UserProfile userProfile;
    @ApiModelProperty
    List<UserToken> userTokens;
}

