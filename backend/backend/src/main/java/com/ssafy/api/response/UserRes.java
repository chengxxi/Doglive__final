package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.auth.UserProfile;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("UserResponse")
public class UserRes extends BaseResponseBody {

    @ApiModelProperty
    UserProfile userProfile;

    public static UserRes of(Integer statusCode, String message, UserProfile userProfile){
        UserRes res = new UserRes();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        res.setUserProfile(userProfile);
        return res;
    }
}
