package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.community.Community;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("CommunityBoardGetResponse")
public class CommunityBoardGetRes extends BaseResponseBody {
    @ApiModelProperty
    Community community;

    public static CommunityBoardGetRes of(Integer statusCode, String message, Community community){
        CommunityBoardGetRes res = new CommunityBoardGetRes();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        res.setCommunity(community);
        return res;
    }

}
