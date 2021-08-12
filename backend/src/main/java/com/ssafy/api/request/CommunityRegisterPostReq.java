package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("CommunityRegisterPostReq")
public class CommunityRegisterPostReq {
    @ApiModelProperty
    String userId;

    @ApiModelProperty
    String title;

    @ApiModelProperty
    String description;

    @ApiModelProperty
    String category;
}