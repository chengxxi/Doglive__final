package com.ssafy.api.request;

import com.ssafy.db.entity.auth.User;
import com.ssafy.db.entity.auth.UserProfile;
import com.ssafy.db.entity.community.Community;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("CommentPostRequest")
public class CommentPostReq {

    @ApiModelProperty
    Long communityId;

    @ApiModelProperty
    String userId;

    @ApiModelProperty
    String comment;
}

