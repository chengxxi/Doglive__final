package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@ApiModel("UserUpdatePutRequest")
public class UserUpdatePutReq {
    @ApiModelProperty
    String name;

    @ApiModelProperty
    String email;

    @ApiModelProperty
    String phoneNumber;

    @ApiModelProperty
    String birth;
//    @ApiModelProperty
//    String profile_image_url;
}
