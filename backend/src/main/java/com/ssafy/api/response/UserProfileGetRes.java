package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.auth.UserProfile;
import com.ssafy.db.entity.board.Board;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel("UserProfileGetResponse")
public class UserProfileGetRes extends BaseResponseBody {
    @ApiModelProperty
    List<UserProfile> userProfileList;

    @ApiModelProperty
    int size;

    public static UserProfileGetRes of(Integer statusCode, String message, List<UserProfile> userProfileList, Integer size) {
        UserProfileGetRes res = new UserProfileGetRes();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        res.setUserProfileList(userProfileList);
        res.setSize(size);
        return res;
    }
}
