package com.ssafy.api.response;

import com.ssafy.api.request.CommunityParamDto;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.community.Community;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel("CommunityListGetRes")
public class CommunityListGetRes extends BaseResponseBody {

    @ApiModelProperty
    List<CommunityParamDto> communityList;

    @ApiModelProperty
    int size;

    public static CommunityListGetRes of(Integer statusCode, String message,List<CommunityParamDto> communityList,Integer size){
        CommunityListGetRes res = new CommunityListGetRes();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        res.setCommunityList(communityList);
        res.setSize(size);

        return res;
    }


}
