package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("UserUpdatePutRequest")
public class UserUpdatePutReq {
    @ApiModelProperty
    String name;
//    @ApiModelProperty
//    String profile_image_url;
}
