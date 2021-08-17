package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.community.Community;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel("CommunityBoardGetResponse")
public class CommunityBoardGetRes extends BaseResponseBody {
    @ApiModelProperty
    Community community;

    @ApiModelProperty
    List<String> filePath;

//    @ApiModelProperty
//    String username;

    public static CommunityBoardGetRes of(Integer statusCode, String message, Community community, List<String> filePath){
        CommunityBoardGetRes res = new CommunityBoardGetRes();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        res.setCommunity(community);
        res.setFilePath(filePath);
//        res.setUsername(username);
        return res;
    }

}
