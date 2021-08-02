package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel("BoardRegisterPostReq")
public class BoardRegisterPostReq {
    @ApiModelProperty
    String userId;
    @ApiModelProperty
    int boardType;
    @ApiModelProperty
    String title;

    @ApiModelProperty
    String thumbnail_url;

    @ApiModelProperty
    List<String> filePath;

    @ApiModelProperty
    int colorType;

    @ApiModelProperty
    String description;

    @ApiModelProperty
    int gender;

    @ApiModelProperty
    int hairType;

    @ApiModelProperty
    int neutralization;

    @ApiModelProperty
    int weight;

    @ApiModelProperty
    String mbti;

    @ApiModelProperty
    String address;

}
